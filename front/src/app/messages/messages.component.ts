import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(public messageService: MessageService) { }

  private visible?: boolean;

  ngOnInit(): void {
    this.visible = false;
  }

  isVisible():boolean {
    if(this.visible) {
      return true;
    }
    return false;
  }

  setVisibility(visibility: boolean): void {
    this.visible = visibility;    
  } 
  

}
