import {CommonModule} from '@angular/common';
import {HttpModule} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {ProductCatalogComponent} from './product.catalog.component';
import {ProductCatalogService} from './product.catalog.service';
import {CartComponent} from '../cart/cart.component';
import {CartService} from '../cart/cart.service';
const cartRoutes: Routes = [
    { path: 'cart', component:  CartComponent },
  ]

 @NgModule({
    declarations: [
      ProductCatalogComponent
    ],
    imports: [
        CommonModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forChild (cartRoutes)
    ],
    exports: [],
   providers: [
     ProductCatalogService,
     CartService
   ]
 })
 export class ProductCatalogModule {}
