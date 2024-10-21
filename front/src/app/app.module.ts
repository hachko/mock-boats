import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BoatsComponent } from './boats/boats.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-roputing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthInterceptor } from './auth.interceptor';

@NgModule({ declarations: [
        AppComponent,
        BoatsComponent,
        BoatDetailComponent,
        MessagesComponent,
        DashboardComponent
    ],
    bootstrap: [AppComponent], 
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,        
    ], 
    providers: [
        provideHttpClient(withInterceptorsFromDi()),
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    ]})
export class AppModule { }
