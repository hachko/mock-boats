import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of, tap } from 'rxjs';
import { User } from './model/user.model';
import { MessageType } from './message-type-enum';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient, private messageService: MessageService) {}

  private httpOptions = {
    headers: new HttpHeaders({ 
      'Content-Type': 'application/json'
    })
  };

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl + '/read/all', this.httpOptions).pipe(
        tap(_=> this.log('Users fetched', MessageType.SUCCESS)),
        catchError(this.handleError<User[]>('getUsers', []))
      );
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.usersUrl + '/create', user, this.httpOptions).pipe(
        tap((newUser: User) => this.log('added user id= '  + newUser.id, MessageType.SUCCESS)),
        catchError(this.handleError<User>('addUser'))
      );
  }

  deleteUser(id: number): Observable<User> {
    return this.http.delete<User>(`${this.usersUrl}/delete/${id}`, this.httpOptions).pipe(
        tap(_=> this.log('Deleted user id : ' + id, MessageType.SUCCESS)),
        catchError(this.handleError<User>('deleteUser'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {    
      this.log(`${operation} failed: ${error.message}`, MessageType.ERROR);        
      return of(result as T);
    };
  }
  
  private log(message: string, type: MessageType) {
    this.messageService.add('User-admin service : ' + message, type);
  }
}
