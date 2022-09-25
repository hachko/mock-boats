import { Component, OnInit } from '@angular/core';
import { Boat } from '../boat';
import { BoatService } from '../boat.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private boatService: BoatService) { }

  boats?: Boat[];

  ngOnInit(): void {
    this.getBoats();
  }

  getBoats(): void {
    this.boatService.getBoats().subscribe(
      boats => this.boats = boats.slice(1,5)
    );
  }

}
