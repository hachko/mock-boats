import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

import { Boat } from '../boat';
import { BoatService } from '../boat.service';



@Component({
  selector: 'app-boat-detail',
  templateUrl: './boat-detail.component.html',
  styleUrls: ['./boat-detail.component.css']
})
export class BoatDetailComponent implements OnInit {
  
  constructor(
    private location: Location, 
    private route: ActivatedRoute, 
    private boatService: BoatService,
    private router: Router ) { }
  
  @Input() boat?: Boat;

  ngOnInit(): void {
    this.getBoat();
  }

  getBoat(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.boatService.getBoat(id).subscribe(boat => this.boat = boat);
  }
  goBack() {
    this.location.back();
  }

  modifyBoat(): void {
    this.router.navigate(['boat/edit', this.boat?.id]);
  }
}
