import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private _http: HttpClient) { }

  login(login: any): Observable<any> {
    return this._http.post<JwtMessage>('auth-service/login', login).pipe(
      tap(response => {
        if (response && response.response) {
          localStorage.setItem('token', response.response);
          this.loggedIn.next(true);
        }
      })
    );
  }

  isLoggedIn(): boolean {
    return this.loggedIn.value;
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedIn.next(false);
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }
}

interface JwtMessage {
  response: string;
  date: string;
}
