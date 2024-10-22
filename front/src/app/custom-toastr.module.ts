import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    BrowserAnimationsModule, 
    ToastrModule.forRoot({
      positionClass: 'toast-top-left',
      timeOut: 3000,
      preventDuplicates: true,
      progressBar: true,
    })
  ],
  exports: [ToastrModule] 
})
export class CustomToastrModule {}
