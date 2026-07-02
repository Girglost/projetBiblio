import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Utilisateur } from '../model/utilisateur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  private http : HttpClient = inject(HttpClient);
  private apiUrl : string = "/utilisateur";


  public findAll(): Observable<Utilisateur[]>{
    return this.http.get<Utilisateur[]>(this.apiUrl);
  }

  public add(utilisateur : Utilisateur) : Observable<Utilisateur>{
    return this.http.post<Utilisateur>(this.apiUrl, utilisateur);
  }

  public update(utilisateur : Utilisateur) : Observable<Utilisateur>{
    return this.http.put<Utilisateur>(`${ this.apiUrl }/${ utilisateur.id }`, utilisateur);
  }

  public remove(utilisateur : Utilisateur) : Observable<void>{
    return this.http.delete<void>(`${ this.apiUrl }/${ utilisateur.id }`);
  }
}
