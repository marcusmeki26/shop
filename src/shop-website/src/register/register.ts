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
        console.log(res);
        window.alert("Successfully Created");
        this.router.navigate(['/login']);
      },
      error: err => {
        if(err.status) window.alert("Unsuccessful") 
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

