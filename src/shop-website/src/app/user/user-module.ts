import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing-module';
import { ProductList } from './product-list/product-list';
import { UserLayout } from './user-layout/user-layout';
import { UserLandingPage } from './user-landing-page/user-landing-page';
import { HttpClientModule } from '@angular/common/http';
import { User } from '../service/user';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ProductList,
    UserLayout,
    UserLandingPage
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    HttpClientModule,
    FormsModule,
    MatIconModule,
    MatButtonModule
  ],
  providers: [
    User
  ]
})
export class UserModule { }
