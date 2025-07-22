import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShopOwnerRoutingModule } from './shop-owner-routing-module';
import { Dashboard } from './dashboard/dashboard';


@NgModule({
  declarations: [
    Dashboard
  ],
  imports: [
    CommonModule,
    ShopOwnerRoutingModule
  ]
})
export class ShopOwnerModule { }
