import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { AdminComponent } from './app/admin/admin.component';
import { LoginComponent } from './app/login/login.component';
import { HomeComponent } from './app/home/home.component';
import { AuthGuard } from './app/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Redirect '/' to login
  { path: 'login', component: LoginComponent }, // Login page
  { path: 'home', component: HomeComponent }, // Home page after login
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] }, // Admin page with guard
  { path: '**', redirectTo: 'login' } // Redirect unknown routes to login
];

bootstrapApplication(AppComponent, {
  providers: [provideRouter(routes), ...appConfig.providers]
}).catch((err) => console.error(err));
