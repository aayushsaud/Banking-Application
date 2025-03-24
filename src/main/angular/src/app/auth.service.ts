import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/public'; // Base URL for public endpoints

  constructor(private http: HttpClient) {}

  // Login
  login(userName: string, password: string): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/log-in`, { userName, password });
  }

  // Signup
  signup(userDto: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/sign-up`, userDto);
  }
}
