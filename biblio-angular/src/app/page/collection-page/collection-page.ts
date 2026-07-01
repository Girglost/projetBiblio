import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Collection } from '../../model/collection';
import { CollectionService } from '../../service/collection-service';

@Component({
  selector: 'app-collection-page',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './collection-page.html',
  styleUrl: './collection-page.css',
})
export class CollectionPage implements OnInit {

  private refresh$: Subject<void> = new Subject<void>();

  private collectionService: CollectionService = inject(CollectionService);

  protected collections$!: Observable<Collection[]>

  // REACTIVE FORM
  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formCollection!: FormGroup;
  protected formCtrlNom!: FormControl;
  protected editingCollectionId: number | undefined = 0;


  ngOnInit(): void {
    this.collections$ = this.refresh$.pipe(
      startWith(0), // Initialisation => forcer le chargement une première fois
      switchMap(() => this.collectionService.findAll()) // Transformer au moment du next()
    );

    // Reactive Form
    this.formCtrlNom = this.formBuilder.control('', [Validators.required, Validators.minLength(6)]);

    this.formCollection = this.formBuilder.group({
      // Ajout des différents contrôles == input, select, etc.
      // libelle: this.formBuilder.control('', Validators.required)
      libelle: this.formCtrlNom
    });

  }

  private reload() {
    this.refresh$.next();
  }

  public addOrUpdate() {
    const collection: Collection = this.formCollection.getRawValue();

    if (this.editingCollectionId) {
      collection.id = this.editingCollectionId;
      this.collectionService.update(collection).subscribe(() => {
        this.reload();

      });
    } else {
      this.collectionService.add(collection).subscribe(() => {
        this.reload();

      });
    }
    this.formCollection.reset();
    this.editingCollectionId = 0;
  }

  public edit(collection: Collection) {
    this.editingCollectionId = collection.id;
    this.formCtrlNom.setValue(collection.nom);
  }

  public remove(collection: Collection) {
    this.collectionService.remove(collection).subscribe(() => {
      this.reload();
    });
  }
}
