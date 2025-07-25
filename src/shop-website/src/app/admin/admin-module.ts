import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing-module';
import { Dashboard } from './dashboard/dashboard';
import { AdminLayout } from './admin-layout/admin-layout';
import { UserList } from './user-list/user-list';
import { OwnerList } from './owner-list/owner-list';
import { FormsModule } from '@angular/forms';
import { Admin } from '../service/admin';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    Dashboard,
    AdminLayout,
    UserList,
    OwnerList
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    Admin
  ]
})
export class AdminModule { }
