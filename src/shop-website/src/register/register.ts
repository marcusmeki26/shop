import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../app/service/auth';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {

  constructor(private router: Router, 
    private location: Location,
    private authSevice: Auth){}

  userCred: RegisterUser = {
    username: '',
    password: '',
    role: '',
  }  

  registerUser(newUser: NgForm){
    this.authSevice.register(newUser.value).subscribe({
      next: res => {
        window.alert("Successfully Created\n" + JSON.stringify(res));
        this.router.navigate(['/login']);
      },
      error: err => {
        window.alert("Unsuccessful\n" + JSON.stringify(err.error.fieldErrors)); 
      }
    });
  }

  goBack() {
    this.location.back();
  }
}
function next(value: Object): void {
  throw new Error('Function not implemented.');
}

