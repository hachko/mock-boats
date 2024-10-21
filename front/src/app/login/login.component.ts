import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';
  
  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: response => {
        if(response.isValid) {
          this.errorMessage = '';
          this.router.navigate(['/dashboard'])
        }
      },
      error: err => {
        if(err.status === 401 || err.status === 403) {
          this.errorMessage = 'Login failed, check your credentials';
        }
      }
    })
  }
}
