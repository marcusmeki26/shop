import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { map, Observable, tap } from 'rxjs';
import { Admin } from '../../service/admin';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-user-list',
  standalone: false,
  templateUrl: './user-list.html',
  styleUrl: './user-list.css'
})
export class UserList {
  users$: Observable<User[]> | null = null;
  user$: Observable<User> | null = null;
  
  constructor(private admin: Admin){
    this.user$ = null;
    this.users$ = this.admin.getUsers();
  }

  searchInput = { 
    userOrId: ''
  } 
  
  searchUser(searchForm: NgForm){
    if(searchForm.value.userOrId != ""){
      this.users$ = null;
      this.user$ = this.admin.getUsersById(searchForm.value.userOrId);
    }else{
      this.user$ = null;
      this.users$ = this.admin.getUsers();
    }
  }
} 
