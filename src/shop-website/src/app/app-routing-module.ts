import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Role } from './guard/role.guard';
import { Login } from '../login/login';
import { Register } from '../register/register';

const routes: Routes = [
  {
    path: 'login',
    component: Login
  },
  {
    path: 'register',
    component: Register
  },
  {
    path: 'user',
    loadChildren: () => import("./user/user-module").then(m => m.UserModule),
    canActivate: [Role],
    data: { expectedRole: 'ROLE_USER'}
  },
  {
    path: 'owner',
    loadChildren: () => import("./shop-owner/shop-owner-module").then(m => m.ShopOwnerModule),
    canActivate: [Role],
    data: { expectedRole: 'ROLE_OWNER' }
  },
  {
    path: 'admin',
    loadChildren: () => import("./admin/admin-module").then(m => m.AdminModule),
    canActivate: [Role],
    data: { expectedRole: 'ROLE_ADMIN' }
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
