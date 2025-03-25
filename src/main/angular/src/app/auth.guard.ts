import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    const token = localStorage.getItem('token');  // Get the authentication token from localStorage
    const role = localStorage.getItem('role');    // Get the user role from localStorage

    // Check if the token exists and the role is 'ROLE_ADMIN'
    if (token && role === 'ROLE_ADMIN') {
      return true;  // If authenticated and an admin, allow access
    } else {
      this.router.navigate(['/login']);  // Redirect to login page if not authorized
      return false;  // Block access to the route
    }
  }
}
