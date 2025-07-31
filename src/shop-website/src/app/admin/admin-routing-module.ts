import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard';
import { AdminLayout } from './admin-layout/admin-layout';
import { UserList } from './user-list/user-list';
import { OwnerList } from './owner-list/owner-list';

const routes: Routes = [
  {
    path: '',
    component: AdminLayout,
    children: [
      {
        path: 'dashboard', 
        component: Dashboard
      },
      {
        path: 'user',
        component: UserList
      },
      {
        path: 'owner',
        component: OwnerList
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
    ]
  },
  // { path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
