import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../services/account.service';
import { Account } from '../models/account.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  accounts: Account[] = [];
  newAccount: Account = { bankName: '', balance: 0 };
  amount: number = 0;

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    this.loadAccounts();
  }

  // Fetch all accounts
    loadAccounts(): void {
      this.accountService.getAccounts().subscribe(
        (response: Account[]) => {
          this.accounts = response.map(account => ({ ...account, amount: 0 }));
        },
        (error: any) => {
          console.error('Failed to fetch accounts:', error);
        }
      );
    }

  // Create a new account
  createAccount(): void {
    this.accountService.createAccount(this.newAccount).subscribe(
      (response: Account) => {
        console.log('Account created successfully:', response);
        this.loadAccounts();
        this.newAccount = { bankName: '', balance: 0 };
      },
      (error: any) => {
        console.error('Failed to create account:', error);
      }
    );
  }

  depositBalance(accountId: number | undefined, amount: number | undefined): void {
    if (accountId === undefined || amount === undefined || amount <= 0) {
      console.error('Invalid deposit amount or account ID');
      return;
    }
    this.accountService.depositBalance(accountId, amount).subscribe(
      () => {
        console.log('Balance deposited successfully');
        this.loadAccounts();
      },
      (error) => console.error('Failed to deposit balance:', error)
    );
  }

  withdrawBalance(accountId: number | undefined, amount: number | undefined): void {
    if (accountId === undefined || amount === undefined || amount <= 0) {
      console.error('Invalid withdrawal amount or account ID');
      return;
    }
    this.accountService.withdrawBalance(accountId, amount).subscribe(
      () => {
        console.log('Balance withdrawn successfully');
        this.loadAccounts();
      },
      (error) => console.error('Failed to withdraw balance:', error)
    );
  }

  deleteAccount(accountId: number | undefined): void {
    if (accountId === undefined) {
      console.error('Invalid account ID for deletion');
      return;
    }
    if (confirm('Are you sure you want to delete this account?')) {
      this.accountService.deleteAccount(accountId).subscribe(
        () => {
          console.log('Account deleted successfully');
          this.loadAccounts();
        },
        (error) => console.error('Failed to delete account:', error)
      );
    }
  }
}
