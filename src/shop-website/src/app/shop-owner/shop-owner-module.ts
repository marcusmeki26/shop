import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShopOwnerRoutingModule } from './shop-owner-routing-module';
import { Dashboard } from './dashboard/dashboard';
import { OwnerLayout } from './owner-layout/owner-layout';


@NgModule({
  declarations: [
    Dashboard,
    OwnerLayout
  ],
  imports: [
    CommonModule,
    ShopOwnerRoutingModule
  ]
})
export class ShopOwnerModule { }
