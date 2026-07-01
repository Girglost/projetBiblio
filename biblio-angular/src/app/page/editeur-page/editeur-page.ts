import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Observable, startWith, switchMap } from 'rxjs';
import { Subject } from 'rxjs/internal/Subject';
import { Editeur } from '../../model/editeur';
import { EditeurService } from '../../service/editeur-service';

@Component({
  selector: 'app-editeur-page',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit {
  private editeurService: EditeurService = inject(EditeurService);
  private refresh$ = new Subject<void>();

  protected editeurs$!: Observable<Editeur[]>;

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formEditeur!: FormGroup;
  protected editingEditeurId: number | undefined = 0;
  protected formCtrlNom!: FormControl;
  protected formCtrlPays!: FormControl;

  ngOnInit(): void {
    this.editeurs$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.editeurService.findAll())
    );

    this.formCtrlNom = this.formBuilder.control('');
    this.formCtrlPays = this.formBuilder.control('');
    this.formEditeur = this.formBuilder.group({
      nom: this.formCtrlNom,
      pays: this.formCtrlPays,
    });
  }
  private reload() {
    this.refresh$.next();
  }

  public addOrUpdate() {
    const editeur: Editeur = this.formEditeur.getRawValue();

    if (this.editingEditeurId) {
      this.editeurService.update(editeur).subscribe(() => {
        this.reload();
      });
    } else {
      this.editeurService.add(editeur).subscribe(() => {
        this.reload();
      });
    }
    this.editingEditeurId = 0;
    this.formEditeur.reset();
  }

  public edit(editeur: Editeur) {
    this.editingEditeurId = editeur.id;
    this.formCtrlNom.setValue(editeur.nom);
    this.formCtrlPays.setValue(editeur.pays);
  }
}
