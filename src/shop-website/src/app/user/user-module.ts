import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing-module';
import { ProductList } from './product-list/product-list';
import { UserLayout } from './user-layout/user-layout';


@NgModule({
  declarations: [
    ProductList,
    UserLayout
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
