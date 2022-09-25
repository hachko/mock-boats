import { Component, OnInit } from '@angular/core';
import { Boat } from '../boat';
import { BoatService } from '../boat.service';


@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  constructor(private boatService: BoatService) { }

  boats: Boat[] = [];

  ngOnInit(): void {
    this.getBoats();
  } 

  getBoats(): void {
    this.boatService.getBoats().subscribe(
      boats => this.boats = boats
    );
  }

  add(name: string, description: string): void {
    name = name.trim();
    if (!name) { return; }
    this.boatService.addBoat({ name, description } as Boat)
      .subscribe(boat => {
        this.boats.push(boat);
      });
  }

  delete(boat: Boat): void {
    this.boats = this.boats.filter(b => b !== boat);
    this.boatService.deleteBoat(boat.id).subscribe();
  }

}