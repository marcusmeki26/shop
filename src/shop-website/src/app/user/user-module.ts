import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing-module';
import { ProductList } from './product-list/product-list';


@NgModule({
  declarations: [
    ProductList
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
