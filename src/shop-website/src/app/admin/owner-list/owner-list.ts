import { Component } from '@angular/core';
import { Admin } from '../../service/admin';
import { catchError, Observable, of } from 'rxjs';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-owner-list',
  standalone: false,
  templateUrl: './owner-list.html',
  styleUrl: './owner-list.css'
})
export class OwnerList {
  users$: Observable<User[]> | null = null;
  user$: Observable<User | null> | null = null;
  columnName: string[] = ["id", "username", "password"];
  
  constructor(private admin: Admin){
    this.user$ = null;
    this.users$ = this.admin.getUsers();
  }
  
  searchUser(searchInput: string){
    if(searchInput != ""){  
      this.users$ = null; 
      this.user$ = this.admin.getUserByIdOrUsername(searchInput).pipe(
        catchError(err => {
          if(err.error.status == 404){
            window.alert("No username or id found");
          }
          return of(null);
        })
      );
    }else{
      this.users$ = this.admin.getUsers();
      this.user$ = null;
    }
  }
}
