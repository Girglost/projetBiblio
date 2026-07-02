import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequest } from '../model/auth-request';
import { AuthResponse } from '../model/auth-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private _token: string = sessionStorage.getItem('token') ?? "";

  public get token(): string {
    return this._token;
  }

  constructor(private http: HttpClient) { }

  public isLogged(): boolean {
    return this._token != "";
  }

  public auth(request: AuthRequest): Observable<void> {
    return new Observable<void>(observer => {
      this.http.post<AuthResponse>('/auth', request).subscribe({
        next: resp => {
          this._token = resp.token;

          // Enregistrement du jeton dans la session Storage
          sessionStorage.setItem('token', resp.token);

          observer.next();
        },

        error: () => {
          observer.error();
        }
      });
    });
  }

  public disconnect() {
    this._token = "";
    sessionStorage.removeItem('token');
  }

}
