import { inject, Injectable } from '@angular/core';
import { Livre } from '../model/livre';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Title } from '@angular/platform-browser';
import { Avis } from '../model/avis';
import { Subject } from 'rxjs/internal/Subject';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class AvisService {


  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "/livres";

  public findAll(): Observable<Livre[]> {
    return this.http.get<Livre[]>(this.apiUrl);
  }

    public add(avis: Avis): Observable<Avis> {
    return this.http.post<Avis>(this.apiUrl, avis);
  }

  public update(avis: Avis): Observable<Avis> {
    return this.http.put<Avis>(`${ this.apiUrl }/${ avis.id }`, avis);
  }

  public remove(avis: Avis): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ avis.id }`);
  }
}

