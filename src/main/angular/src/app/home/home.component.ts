import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../services/account.service';
import { Account } from '../models/account.model';  // Import the Account model

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],  // CommonModule and FormsModule are correctly imported
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  accounts: Account[] = [];
  newAccount: Account = { bankName: '', balance: 0 };  // Initialize newAccount with default values

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    this.loadAccounts();  // Load the accounts on component initialization
  }

  // Fetch all accounts
  loadAccounts(): void {
    this.accountService.getAccounts().subscribe(
      (response: Account[]) => {
        this.accounts = response;
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
        this.loadAccounts();  // Refresh the account list after creating a new account
        this.newAccount = { bankName: '', balance: 0 };  // Reset the new account form
      },
      (error: any) => {
        console.error('Failed to create account:', error);
      }
    );
  }
}
