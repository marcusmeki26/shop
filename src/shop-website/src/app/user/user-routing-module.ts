import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductList } from './product-list/product-list';

const routes: Routes = [
  {
    path: 'products',
    component: ProductList
  },
  { path: '', redirectTo: 'products', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
