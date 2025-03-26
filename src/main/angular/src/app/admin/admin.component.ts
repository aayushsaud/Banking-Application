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
  imports: [CommonModule, FormsModule]
})
export class AdminComponent implements OnInit {
  users: User[] = [];
  newAdmin: Partial<User> = { userName: '', password: '', role: 'ADMIN' };
  errorMessage: string = '';

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.adminService.getAllUsers().subscribe(
      (users) => (this.users = users),
      (error) => console.error('Failed to load users:', error)
    );
  }

  createAdmin(): void {
    if (!this.newAdmin.userName || !this.newAdmin.password) {
      this.errorMessage = 'Username and Password are required!';
      return;
    }

    this.adminService.createAdmin(this.newAdmin).subscribe(
      (admin) => {
        this.users.push(admin);
        this.newAdmin = { userName: '', password: '', role: 'ADMIN' };
        this.errorMessage = '';
      },
      (error) => {
        console.error('Failed to create admin:', error);
        this.errorMessage = 'Error creating admin';
      }
    );
  }

  // Logout method
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.router.navigate(['/login']);
  }
}
