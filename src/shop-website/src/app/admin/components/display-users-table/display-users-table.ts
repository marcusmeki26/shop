import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../model/user.model';
import { NgForm } from '@angular/forms';
import { Admin } from '../../../service/admin';

@Component({
  selector: 'app-display-users-table',
  standalone: false,
  templateUrl: './display-users-table.html',
  styleUrl: './display-users-table.css'
})
export class DisplayUsersTable{

  constructor(private adminService: Admin){}

  isArray = Array.isArray;

  @Input() users$: Observable<User[]> | null = null;
  @Input() user$: Observable<User| null> | null = null;
  @Input() columnName: string[] = [];
  @Output() searchInputOutput = new EventEmitter<string>(); 

  searchInput = { 
    userOrId: ''
  } 
  
  // If there is a value returns a 
  searchUser(searchForm: NgForm){
    this.searchInputOutput.emit(searchForm.value.userOrId);
  }
}
