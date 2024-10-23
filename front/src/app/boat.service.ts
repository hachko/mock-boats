import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Boat } from './boat'
import { MessageService } from './message.service';
import { MessageType } from './message-type-enum';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  constructor (private http: HttpClient, private messageService: MessageService) { }

  private boatsUrl = 'http://localhost:8080/boats';

  private httpOptions = {
    headers: new HttpHeaders({ 
      'Content-Type': 'application/json'
    })
  };

  getBoats(): Observable<Boat[]> {    
    console.log('calling request getBoats');
    return this.http.get<Boat[]>(this.boatsUrl + '/read/all', this.httpOptions).pipe(
      tap(_=> this.log('Boats fetched', MessageType.SUCCESS)),
      catchError(this.handleError<Boat[]>('getBoats', []))
    );

  } 

  getBoat(id: number): Observable<Boat> {
    return this.http.get<Boat>(this.boatsUrl + '/read/' + id, this.httpOptions).pipe(
      tap(_=>this.log(`fetched boat id : ${id}`, MessageType.SUCCESS)),
      catchError(this.handleError<Boat>('getBoat(${id})'))
    )
  }

  updateBoat(boat:Boat): Observable<any> {
    return this.http.put(this.boatsUrl + '/update', boat, this.httpOptions).pipe(
      tap(_=> this.log('updated boat id : ' + boat.id, MessageType.SUCCESS)),
      catchError(this.handleError<any>('UpdateBoat'))
    )
  }

  /** POST: add a new boat to the server */
  addBoat(boat: Boat): Observable<Boat> {
    return this.http.post<Boat>(this.boatsUrl + '/create', boat, this.httpOptions).pipe(
      tap((newBoat: Boat) => this.log('added boat id= '  + newBoat.id, MessageType.SUCCESS)),
      catchError(this.handleError<Boat>('addBoat'))
    );
  }

  deleteBoat(id:number): Observable<Boat> {
    return this.http.delete<Boat>(this.boatsUrl + '/delete/' + id, this.httpOptions).pipe(
      tap(_=> this.log('Deleted boat id : ' + id, MessageType.SUCCESS)),
      catchError(this.handleError<Boat>('deleteBoat'))
    )
  }

  private log(message: string, type: MessageType) {
    this.messageService.add('boatService : ' + message, type);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {    
      this.log(`${operation} failed: ${error.message}`, MessageType.ERROR);        
      return of(result as T);
    };
  }
}