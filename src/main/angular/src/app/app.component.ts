import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, NavigationEnd } from '@angular/router';
import { RouterModule } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterModule], // ✅ REMOVE AccountComponent
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  showNavigation: boolean = false; // Default to false

  constructor(private router: Router) {
    // Listen to route changes
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        // Show navigation only on the home page
        this.showNavigation = event.url === '/home';
      });
  }

  logout(): void {
    localStorage.removeItem('token'); // Clear the JWT token
    this.router.navigate(['/login']); // Redirect to the login page
  }
}
