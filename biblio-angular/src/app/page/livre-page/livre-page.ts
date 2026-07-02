import { LivreService } from './../../service/livre-service';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, startWith, Subject, switchMap, TimeoutError } from 'rxjs';
import { Livre } from '../../model/livre';

@Component({
  selector: 'app-livre-page',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit{

  private refresh$: Subject<void> = new Subject<void>();

  private livreService : LivreService = inject(LivreService);
  protected livres$!: Observable<Livre[]>;

  private formBuilder : FormBuilder = inject(FormBuilder);
  protected formLivre! : FormGroup;

  protected formCtrlTitre!: FormControl;
  protected formCtrlResumer!: FormControl;
  protected formCtrlAnnee!: FormControl;
  protected formCtrlAuteur!: FormControl;
  protected formCtrlEditeur!: FormControl;
  protected formCtrlCollection!: FormControl;

  protected editingLivreId: number | undefined = 0;


  ngOnInit(): void {
    this.livres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.livreService.findAll())
    );

    this.formCtrlTitre = this.formBuilder.control('', [Validators.required]);
    this.formCtrlResumer= this.formBuilder.control('');
    this.formCtrlAnnee= this.formBuilder.control('', [Validators.required]);
    this.formCtrlAuteur= this.formBuilder.control('', [Validators.required]);
    this.formCtrlEditeur= this.formBuilder.control('', [Validators.required]);
    this.formCtrlCollection = this.formBuilder.control('');

    this.formLivre = this.formBuilder.group({
      titre : this.formCtrlTitre,
      resumer: this.formCtrlResumer,
      annee: this.formCtrlAnnee,
      auteur: this.formCtrlAuteur,
      editeur: this.formCtrlEditeur,
      collection: this.formCtrlCollection

    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdate() {
      const livre: Livre = this.formLivre.getRawValue();

      if (this.editingLivreId) {
        livre.id = this.editingLivreId;
        this.livreService.update(livre).subscribe(() => {
          this.reload();

        });
      } else {
        this.livreService.add(livre).subscribe(() => {
          this.reload();

        });
      }
      this.formLivre.reset();
      this.editingLivreId = 0;
    }

    public edit(livre: Livre) {
      this.editingLivreId = livre.id;
      this.formCtrlTitre.setValue(livre.titre),
      this.formCtrlResumer.setValue(livre.resumer),
      this.formCtrlAnnee.setValue(livre.annee),
      this.formCtrlAuteur.setValue(livre.auteur),
      this.formCtrlEditeur.setValue(livre.editeur),
      this.formCtrlCollection.setValue(livre.collection)
    }

    public remove(livre: Livre) {
      this.livreService.remove(livre).subscribe(() => {
        this.reload();
      });
    }

}
