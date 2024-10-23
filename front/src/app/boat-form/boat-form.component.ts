import { Component, Input } from '@angular/core';
import { BoatService } from '../boat.service';
import { Boat } from '../boat';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boat-form',
  templateUrl: './boat-form.component.html',
  styleUrl: './boat-form.component.css'
})
export class BoatFormComponent {
  @Input() boat: Boat = { id: null, name: '', description: '', imageUrl: '' };

  constructor(private boatService: BoatService, private router: Router) {}

  ngOnInit(): void {}

  addBoat(): void {
    if (!this.boat.name.trim()) { return; }
    this.boatService.addBoat(this.boat).subscribe(boat => {      
      this.boat = { id: null, name: '', description: '', imageUrl: '' };
      this.router.navigate(['/boats'])
    });
  }

  goBack(): void {
    this.router.navigate(['/boats'])
  }
}
