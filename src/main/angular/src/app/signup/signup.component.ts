import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})

export class SignupComponent {
  userDto: any = {
    userName: '',
    password: ''
  };
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.signup(this.userDto).subscribe(
      (response) => {
        console.log('Signup successful:', response);
        this.router.navigate(['/login']);
      },
      (error) => {
        this.errorMessage = 'Signup failed. Please try again.';
        console.error('Signup error:', error);
      }
    );
  }
}
