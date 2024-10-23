import { Component, OnInit } from '@angular/core';
import { Boat } from '../boat';
import { BoatService } from '../boat.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  constructor(private boatService: BoatService, private router: Router) { }

  boats: Boat[] = [];

  ngOnInit(): void {
    this.getBoats();
  } 

  getBoats(): void {
    this.boatService.getBoats().subscribe(
      boats => this.boats = boats
    );
  }  

  delete(boat: Boat): void {
    if(boat.id !== null) {
      this.boats = this.boats.filter(b => b !== boat);
      this.boatService.deleteBoat(boat.id).subscribe();
    }    
  }

  goToNewBoatForm(): void {
    this.router.navigate(['/boat/add']);
  }

}