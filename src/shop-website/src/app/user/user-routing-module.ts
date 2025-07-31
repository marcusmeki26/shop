import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLayout } from './user-layout/user-layout';
import { UserLandingPage } from './user-landing-page/user-landing-page';
import { ProductList } from './product-list/product-list';

const routes: Routes = [
  {
    path: '',
    component: UserLayout,
    children: [
      {
        path: '',
        component: UserLandingPage
      },
      {
        path: 'products',
        component: ProductList
      },
      {
        path: 'category/:categoryname',
        component: ProductList
      }
    ]
  },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
