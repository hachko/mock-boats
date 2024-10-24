import { Component, Input } from '@angular/core';
import { BoatService } from '../boat.service';
import { Boat } from '../boat';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-boat-form',
  templateUrl: './boat-form.component.html',
  styleUrl: './boat-form.component.css'
})
export class BoatFormComponent {
  @Input() boat: Boat = { id: null, name: '', description: '', imageUrl: '' };

  constructor(
    private boatService: BoatService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.getBoat(+id);
    }
  }

  getBoat(id: number): void {
    this.boatService.getBoat(id).subscribe(boat => (this.boat = boat));
  }

  save(): void {  
    if (this.boat.id === null) {
      this.boatService.addBoat(this.boat).subscribe(boat => {
        this.goBack();
      });
    } else {
      this.boatService.updateBoat(this.boat).subscribe(boat => {      
        this.goBack();
      });
    }
  }

  goBack(): void {
    this.location.back();
  }
}
