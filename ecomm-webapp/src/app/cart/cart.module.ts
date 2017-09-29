import {NgModule} from '@angular/core';
import {CartComponent} from './cart.component';
import {CommonModule} from '@angular/common';
import {HttpModule} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CartService} from './cart.service';

@NgModule({
  declarations: [
    CartComponent
  ],
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [],
  providers: [
    CartService
  ]
})
export class CartModule {}
