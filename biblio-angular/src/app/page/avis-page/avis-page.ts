import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, Subject, switchMap, startWith } from 'rxjs';
import { Avis } from '../../model/avis';
import { Livre } from '../../model/livre';
import { AvisService } from '../../service/avis-service';
import { LivreService } from '../../service/livre-service';

@Component({
  selector: 'app-avis-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './avis-page.html',
  styleUrl: './avis-page.css',
})
export class AvisPage implements OnInit {
  private titleService: Title = inject(Title);
  private avisService: AvisService = inject(AvisService);
  private livreService: LivreService = inject(LivreService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  private refresh$: Subject<void> = new Subject<void>();

  protected livres$!: Observable<Livre[]>;
  protected avis$!: Observable<Avis[]>;

  protected notesDisponibles: number[] = [1, 2, 3, 4, 5];
  protected avisForm: FormGroup = this.formBuilder.group({
    livre: ['', Validators.required],
    note: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
    date: ['', Validators.required],
    commentaire: [''],
  });

  protected editingAvisId: number | undefined = undefined;

  ngOnInit(): void {
    this.livres$ = this.livreService.findAll();
    this.avis$ = this.refresh$
      .pipe(
        startWith(undefined),
        switchMap(() => this.avisService.findAll()),
      );

  }

protected addOrUpdate() {
  if (this.avisForm.invalid) {
    return;
  }

  const formValue = this.avisForm.getRawValue();
  const avis: Avis = {
    ...formValue,
    livre: { id: formValue.livre } as Livre,
  };

  if (this.editingAvisId) {
    avis.id = this.editingAvisId;
    this.avisService.update(avis).subscribe(() => this.reload());
  } else {
    console.log(avis);
    this.avisService.add(avis).subscribe(() => this.reload());
  }

  this.avisForm.reset();
  this.editingAvisId = undefined;
}

  protected edit(avis: Avis) {
    this.editingAvisId = avis.id;
    this.avisForm.patchValue({
      livre: avis.livre?.id,
      note: avis.note,
      date: avis.date,
      commentaire: avis.commentaire,
    });
  }

  protected remove(avis: Avis) {
    this.avisService.remove(avis).subscribe(() => this.reload());
  }

  private reload() {
    this.refresh$.next();
  }
}
