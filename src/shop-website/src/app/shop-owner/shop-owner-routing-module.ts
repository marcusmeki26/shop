import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard';
import { OwnerLayout } from './owner-layout/owner-layout';

const routes: Routes = [
  {
    path: '',
    component: OwnerLayout,
    children: [
      {
        path: 'dashboard',
        component: Dashboard
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
