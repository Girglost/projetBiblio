import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, startWith, switchMap } from 'rxjs';
import { Subject } from 'rxjs/internal/Subject';
import { Auteur } from '../../model/auteur';
import { AuteurService } from '../../service/auteur-service';


@Component({
  selector: 'app-auteur-page',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})

export class AuteurPage implements OnInit {

 private auteurService: AuteurService = inject(AuteurService);
  private refresh$ = new Subject<void>();

  protected auteurs$!: Observable<Auteur[]>;

// reactive forms

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formCollection!: FormGroup;
  protected formCtrlNom!: FormControl;

  ngOnInit(): void {
    this.auteurs$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.auteurService.findAll())

    );

     this.formCtrlNom = this.formBuilder.control('', [Validators.required, Validators.minLength(3)]);
    this.formCtrlPrenom = this.formBuilder.control('', [Validators.required, Validators.minLength(3)]);
    this.formCtrlNationalite = this.formBuilder.control('', [Validators.required, Validators.minLength(3)]);
     this.formAuteur = this.formBuilder.group({
      nom: this.formCtrlNom,
      prenom: this.formCtrlPrenom,
      nationalite: this.formCtrlNationalite,
    });
  }

  private reload() {
    this.refresh$.next();
  }

   public addOrUpdate() {
    const auteur: Auteur = this.formAuteur.getRawValue();

    if (this.editingAuteurId) {
      auteur.id = this.editingAuteurId;
      this.auteurService.update(auteur).subscribe(() => {
        this.reload();
      });
    } else {
      this.auteurService.add(auteur).subscribe(() => {
        this.reload();
      });
    }
    this.editingAuteurId = 0;
    this.formAuteur.reset();
  }

  public edit(auteur: Auteur) {
    this.editingAuteurId = auteur.id;
    this.formCtrlNom.setValue(auteur.nom);
    this.formCtrlPrenom.setValue(auteur.prenom);
    this.formCtrlNationalite.setValue(auteur.nationalite);
  }

  public remove(auteur: Auteur) {
    this.auteurService.remove(auteur).subscribe(() => {
      this.reload();
    });
  }





}
