import { Component } from '@angular/core';
import { AccountService } from '../services/account.service'; // Adjust the path if needed
import { CommonModule } from '@angular/common'; // Import CommonModule for ngIf, ngFor
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel

@Component({
  selector: 'app-account',
  standalone: true,  // Make this a standalone component
  imports: [CommonModule, FormsModule], // Import required modules for this standalone component
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent {
  accountId: number = 1; // Replace with dynamic account ID if needed
  amount: number = 0; // Stores the user-entered amount

  constructor(private accountService: AccountService) {}

  // Deposit funds
  deposit() {
    if (this.amount <= 0) {
      alert("Enter a valid amount to deposit.");
      return;
    }

    this.accountService.depositBalance(this.accountId, this.amount).subscribe(
      () => {
        console.log('Deposit successful!');
        alert('Deposit successful!');
      },
      (error) => {
        console.error('Error depositing funds:', error);
        alert('Deposit failed!');
      }
    );
  }

  // Withdraw funds
  withdraw() {
    if (this.amount <= 0) {
      alert("Enter a valid amount to withdraw.");
      return;
    }

    this.accountService.withdrawBalance(this.accountId, this.amount).subscribe(
      () => {
        console.log('Withdrawal successful!');
        alert('Withdrawal successful!');
      },
      (error) => {
        console.error('Error withdrawing funds:', error);
        alert('Withdrawal failed!');
      }
    );
  }
}
