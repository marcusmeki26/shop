import { Component } from '@angular/core';
import { Auth } from '../../service/auth';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-layout',
  standalone: false,
  templateUrl: './user-layout.html',
  styleUrl: './user-layout.css'
})
export class UserLayout {
  username: string = "";

  constructor(private router: Router, private authService: Auth){
    this.username = this.authService.getUsername();
  }

  searchForm = {
    searchItem: ''
  }

  searchProduct(searchForm: NgForm) {
    throw new Error('Method not implemented.');
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
