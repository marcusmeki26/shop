import { ChangeDetectorRef, Component } from '@angular/core';
import { catchError, Observable, of} from 'rxjs';
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
  user$: Observable<User | null> | null = null;
  columnName: string[] = ["id", "username", "password"];
  
  constructor(private admin: Admin, private cdr: ChangeDetectorRef){
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
