import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private apiUrl = '/api/admin';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
  }

  getAllUsers(page: number, size: number = 15): Observable<any> {
    const backendPage = page - 1;
    return this.http.get<any>(`${this.apiUrl}/all-users`, {
      headers: this.getAuthHeaders(),
      params: {
        page: backendPage.toString(),
        size: size.toString(),
      },
    });
  }

  createAdmin(user: Partial<User>): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/create`, user, {
      headers: this.getAuthHeaders(),
    });
  }

  searchUsers(column: string, value: string, page: number = 0, size: number = 15): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/search`, {
      headers: this.getAuthHeaders(),
      params: {
        column: column,
        value: value,
        page: page.toString(),
        size: size.toString(),
      },
    });
  }
}
