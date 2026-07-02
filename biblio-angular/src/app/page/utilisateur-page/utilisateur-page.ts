import { UtilisateurService } from './../../service/utilisateur-service';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Utilisateur } from '../../model/utilisateur';

@Component({
  selector: 'app-utilisateur-page',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './utilisateur-page.html',
  styleUrl: './utilisateur-page.css',
})
export class UtilisateurPage implements OnInit{

  private refresh$: Subject<void> = new Subject<void>();

  private utilisateurService : UtilisateurService = inject(UtilisateurService);
  protected utilisateurs$!: Observable<Utilisateur[]>;

  private formBuilder : FormBuilder = inject(FormBuilder);
  protected formUtilisateur! : FormGroup;
  protected formCtrlLogin!: FormControl;
  protected formCtrlPassword!: FormControl;
  protected editingUtilisateurId: number | undefined = 0;


  ngOnInit(): void {
    this.utilisateurs$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.utilisateurService.findAll())
    );

    this.formCtrlLogin = this.formBuilder.control('', [Validators.required]);
    this.formCtrlPassword = this.formBuilder.control('', [Validators.required]);

    this.formUtilisateur = this.formBuilder.group({
      login: this.formCtrlLogin,
      password: this.formCtrlPassword
    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdate() {
      const utilisateur: Utilisateur = this.formUtilisateur.getRawValue();

      if (this.editingUtilisateurId) {
        utilisateur.id = this.editingUtilisateurId;
        this.utilisateurService.update(utilisateur).subscribe(() => {
          this.reload();

        });
      } else {
        this.utilisateurService.add(utilisateur).subscribe(() => {
          this.reload();

        });
      }
      this.formUtilisateur.reset();
      this.editingUtilisateurId = 0;
    }

    public edit(utilisateur: Utilisateur) {
      this.editingUtilisateurId = utilisateur.id;
      this.formCtrlLogin.setValue(utilisateur.login);
      this.formCtrlPassword.setValue(utilisateur.password)
    }

    public remove(utilisateur: Utilisateur) {
      this.utilisateurService.remove(utilisateur).subscribe(() => {
        this.reload();
      });
    }

}
