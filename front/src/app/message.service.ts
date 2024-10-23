import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MessageType } from './message-type-enum';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  messages: string[] = [];

  constructor(private toastr: ToastrService) {}

  add(message: string, type: MessageType) {
    this.messages.push(message);
    this.showToast(message, type);
  }  

  clear() {
    this.messages = [];
  }

  private showToast(message: string, type: MessageType) {
    if(type === MessageType.SUCCESS) {
      this.toastr.success(message, 'Success');
    }
    if(type === MessageType.WARNING) {
      this.toastr.warning(message, 'Warning');
    }
    if((type === MessageType.ERROR)) {
      this.toastr.error(message, 'Error');
    }
  }
}