import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoatsComponent } from './boats/boats.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './auth.guard';
import { BoatFormComponent } from './boat-form/boat-form.component';
import { UserAdminComponent } from './user-admin/user-admin.component';
import { adminGuard } from './admin.guard';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'detail/:id', component: BoatDetailComponent, canActivate: [authGuard] },  
  { path: 'boats', component: BoatsComponent, canActivate: [authGuard] },
  { path: 'boat/add', component: BoatFormComponent, canActivate: [authGuard] },
  { path: 'boat/edit/:id', component: BoatFormComponent, canActivate: [authGuard] },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'user-admin', component: UserAdminComponent, canActivate: [adminGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }