import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AuthResponse } from './model/auth-response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080';
  private isLoggedIn = false;
  private base64Credentials = ''

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<AuthResponse> {    
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password)
    });
    return this.http.get<AuthResponse>(`${this.baseUrl}/authenticate`, { headers })
    .pipe(
      tap(response => {
        if(response.isValid) {
          this.isLoggedIn = true;
          this.base64Credentials = btoa(username + ':' + password);
        }
      })
    );
  }

  isAuthenticated(): boolean {
    return this.isLoggedIn
  }

  getAuthHeader(): string {
    return 'Basic:' + this.base64Credentials;
  }
  
}
