import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../models/account.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = '/api/account'; // Base URL for account endpoints

  constructor(private http: HttpClient) {}

  // Helper function to get the authorization headers
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); // Get the token from local storage
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` // Attach token to request
    });
  }

  // Get all accounts of the user
  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.apiUrl, { headers: this.getAuthHeaders() });
  }

  // Create a new account (with authentication headers)
  createAccount(account: Account): Observable<Account> {
    return this.http.post<Account>(`${this.apiUrl}/create`, account, { headers: this.getAuthHeaders() });
  }
}
