import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { RoleName } from './model/role-name-enum.model';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  if(authService.isAuthenticated() && authService.hasRole(RoleName.USER)) {
    return true;
  } else {
    if(authService.hasRole(RoleName.ADMIN)) {
      router.navigate(['/user-admin']);
      return true;
    } else {
      router.navigate(['/']);
      return false;
    }    
  }  
};
