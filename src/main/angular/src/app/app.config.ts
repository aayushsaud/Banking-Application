import { ApplicationConfig } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { AccountService } from './services/account.service'; // Import the AccountService
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component'; // Import the HomeComponent
import { AdminComponent } from './admin/admin.component'; // Import the AdminComponent
import { AuthGuard } from './auth.guard'; // Import the AuthGuard

// Define the application routes
export const routes: Routes = [
  { path: 'login', component: LoginComponent }, // Public route for login
  { path: 'signup', component: SignupComponent }, // Public route for signup
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard], // Protected route for authenticated users
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard], // Protected route for admins only
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Default redirect to login page
];

// Configure the application with the providers
export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), // Provide the router configuration
    provideHttpClient(), // Provide HTTP client for API calls
    AccountService, // Provide AccountService for managing accounts (if needed)
  ],
};
