import { ApplicationConfig } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { AccountService } from './services/account.service';
import { LoginComponent } from './login/login.component';
import { AccountComponent } from './account/account.component';
import { HomeComponent } from './home/home.component'; // ✅ Import HomeComponent
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent }, // ✅ Add Home route
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] }
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    AccountService
  ]
};
