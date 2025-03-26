import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../models/account.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
    private apiUrl = '/api/account';

    constructor(private http: HttpClient) {}

    // Helper function to get the authorization headers
    private getAuthHeaders(): HttpHeaders {
      const token = localStorage.getItem('token');
      if (!token) {
        console.error('No authentication token found.');
        return new HttpHeaders({ 'Content-Type': 'application/json' });
      }
      return new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      });
    }

    // Get all accounts of the user
    getAccounts(): Observable<Account[]> {
      return this.http.get<Account[]>(this.apiUrl, { headers: this.getAuthHeaders() });
    }

    // Create a new account
    createAccount(account: Account): Observable<Account> {
      return this.http.post<Account>(`${this.apiUrl}/create`, account, { headers: this.getAuthHeaders() });
    }

    // Deposit balance
    depositBalance(accountId: number, amount: number): Observable<void> {
      return this.http.put<void>(
        `${this.apiUrl}/deposit/${accountId}`,
        { amount },
        { headers: this.getAuthHeaders() }
      );
    }

    // Withdraw balance
    withdrawBalance(accountId: number, amount: number): Observable<void> {
      return this.http.put<void>(
        `${this.apiUrl}/withdraw/${accountId}`,
        { amount },
        { headers: this.getAuthHeaders() }
      );
    }

    // Delete account
    deleteAccount(accountId: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/delete/${accountId}`, { headers: this.getAuthHeaders() });
    }
}
