import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoatsComponent } from './boats/boats.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  { path: 'detail/:id', component: BoatDetailComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'boats', component: BoatsComponent },
  { path: 'dashboard', component: DashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }