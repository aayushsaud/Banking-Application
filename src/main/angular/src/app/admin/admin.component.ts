import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-admin',
  standalone: true,
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  imports: [CommonModule, FormsModule],
})
export class AdminComponent implements OnInit {
  users: User[] = [];
  newAdmin: Partial<User> = { userName: '', password: '', role: 'ADMIN' };
  errorMessage: string = '';
  selectedColumn: string = 'userName';
  searchValue: string = '';
  currentPage: number = 1;  // Frontend is 1-based
  totalPages: number = 1;
  isSearchActive: boolean = false;

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.loadUsers(1); // Start at page 1
  }

  loadUsers(page: number): void {
    this.adminService.getAllUsers(page).subscribe({
      next: (response: any) => {
        this.users = response.users;
        this.currentPage = response.currentPage + 1; // Convert 0-based to 1-based
        this.totalPages = response.totalPages;
      },
      error: (error) => {
        console.error('Failed to load users:', error);
      }
    });
  }

  searchUsers(page: number = 1): void {
    if (!this.searchValue) {
      this.errorMessage = 'Please enter a search value!';
      return;
    }

    this.isSearchActive = true;
    const backendPage = page - 1; // Convert 1-based to 0-based for backend

    this.adminService.searchUsers(this.selectedColumn, this.searchValue, backendPage)
      .subscribe({
        next: (response: any) => {
          this.users = response.users;
          this.currentPage = response.currentPage + 1; // Convert 0-based to 1-based
          this.totalPages = response.totalPages;
          this.errorMessage = '';
        },
        error: (error) => {
          console.error('Search failed:', error);
          this.errorMessage = 'Search failed. Please try again!';
        }
      });
  }

  changePage(page: number): void {
    if (page < 1 || page > this.totalPages) return;

    if (this.isSearchActive) {
      this.searchUsers(page);
    } else {
      this.loadUsers(page);
    }
  }

  // Remove the changeSearchPage method as it's redundant with the updated changePage method

  removeSearchFilter(): void {
    this.searchValue = '';
    this.isSearchActive = false;
    this.loadUsers(1);
    this.errorMessage = '';
  }

  createAdmin(): void {
    if (!this.newAdmin.userName || !this.newAdmin.password) {
      this.errorMessage = 'Username and Password are required!';
      return;
    }

    this.adminService.createAdmin(this.newAdmin).subscribe({
      next: (admin) => {
        this.users.push(admin);
        this.newAdmin = { userName: '', password: '', role: 'ADMIN' };
        this.errorMessage = '';
      },
      error: (error) => {
        console.error('Failed to create admin:', error);
        this.errorMessage = 'Error creating admin';
      }
    });
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.router.navigate(['/login']);
  }
}
