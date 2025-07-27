import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing-module';
import { ProductList } from './product-list/product-list';
import { UserLayout } from './user-layout/user-layout';
import { UserLandingPage } from './user-landing-page/user-landing-page';
import { HttpClientModule } from '@angular/common/http';
import { User } from '../service/user';


@NgModule({
  declarations: [
    ProductList,
    UserLayout,
    UserLandingPage
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    HttpClientModule
  ],
  providers: [
    User
  ]
})
export class UserModule { }
