import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { RoleName } from './model/role-name-enum.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'The Skipper\'s CRUD';
  logMessagesVisible = false;
  RoleName = RoleName;
  constructor(private authService: AuthService, private router: Router) {}
  
  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  authenticated():boolean {
    return this.authService.isAuthenticated();
  }

  hasCurrentRole(roleName: RoleName): boolean {
    return this.authService.hasRole(roleName);
  }

  toggleLogMessagesSection() {
    this.logMessagesVisible = !this.logMessagesVisible;
  }
}
