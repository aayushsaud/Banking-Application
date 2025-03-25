import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'] // Fixed styleUrl to styleUrls
})
export class LoginComponent {
  userName: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    // Update the response type to include 'token' and 'role'
    this.authService.login(this.userName, this.password).subscribe(
      (response: { token: string, role: string }) => { // Correct response type
        if (response.token && response.role) {
          // Store token and role in localStorage
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role); // Store role in localStorage

          // Redirect based on the role
          if (response.role === 'ROLE_ADMIN') {
            this.router.navigate(['/admin']);
          } else {
            this.router.navigate(['/home']);
          }
        } else {
          this.errorMessage = 'Login failed. No token or role received.';
        }
      },
      (error) => {
        this.errorMessage = 'Login failed. Please check your credentials.';
      }
    );
  }
}
