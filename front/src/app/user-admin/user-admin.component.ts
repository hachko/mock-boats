import { Component } from '@angular/core';
import { UserService } from '../users.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-user-admin',
  templateUrl: './user-admin.component.html',
  styleUrl: './user-admin.component.css'
})
export class UserAdminComponent {
  users: User[] = [];
  newUser: User = { id: null, username: '', password: '', roles: [] };

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(users => this.users = users);
  }

  addUser(): void {
    if (!this.newUser.username.trim() || !this.newUser.password.trim()) {
      return;
    }
    this.userService.addUser(this.newUser).subscribe(user => {
      this.users.push(user);
      this.newUser = { id: 0, username: '', password: '', roles: [] };
    });
  }

  deleteUser(user: User): void {
    if(user.id !== null) {
      this.userService.deleteUser(user.id).subscribe(() => {
        this.users = this.users.filter(user => user!== user);
      });
    }    
  }

  getUserRolesInCommaSeparatedString(user: User): string {
    return user.roles.map(role => role.name).join(', ');
  }
}
