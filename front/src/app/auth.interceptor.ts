import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('Interceptor activated for URL:', req.url);

    if (this.authService.isAuthenticated()) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', this.authService.getAuthHeader())
      });      
      return next.handle(authReq);
    }
    return next.handle(req);
  }
}
