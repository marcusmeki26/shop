import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';
import { ShopOwnerRoutingModule } from './shop-owner-routing-module';
import { Dashboard } from './dashboard/dashboard';
import { OwnerLayout } from './owner-layout/owner-layout';
import { Owner } from '../service/owner';
import { ShopDetails } from './shop-details/shop-details';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ComponentsModule } from '../shared/components/components-module';
import { ShopProducts } from './shop-products/shop-products';


@NgModule({
  declarations: [
    Dashboard,
    OwnerLayout,
    ShopDetails,
    ShopProducts
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ShopOwnerRoutingModule,
    MatExpansionModule
  ],
  providers: [
    Owner
  ]
})
export class ShopOwnerModule { }
