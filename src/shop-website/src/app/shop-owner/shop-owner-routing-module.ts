import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard';
import { OwnerLayout } from './owner-layout/owner-layout';
import { ShopDetails } from './shop-details/shop-details';
import { ShopProducts } from './shop-products/shop-products';

const routes: Routes = [
  {
    path: '',
    component: OwnerLayout,
    children: [
      {
        path: 'dashboard',
        component: Dashboard
      },
      {
        path: 'products',
        component: ShopProducts
      },
      {
        path: ':shopName',
        component: ShopDetails
      },  
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopOwnerRoutingModule { }
