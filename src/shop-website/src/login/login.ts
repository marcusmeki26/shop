import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Auth } from '../app/service/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  constructor(private authService : Auth, private router: Router){}

  userCred: UserLogin = {
    username: '',
    password: '',
  }

  // Method when login is clicked
  checkCred(loginForm: NgForm){
    this.authService.login(loginForm.value).subscribe({
      next: (res : AuthToken) => { 
        localStorage.setItem("access_token", res.access_token);
        localStorage.setItem("refresh_token", res.refresh_token);

        let role = this.authService.getRoleFromToken(); // extract the role from the token

        // After extracting, it will define where the logged user should go.
        if (role.includes('ROLE_USER')) this.router.navigate(['/user']);  
        else if (role.includes('ROLE_ADMIN')) this.router.navigate(['/admin']);
        else if (role.includes('ROLE_OWNER')) this.router.navigate(['/owner']);
        // else this.router.navigate(['/unauthorized']);
      }, // used if the request is success
      error: err => {
        if(err.status == 400) window.alert("Invalid Credentials\n" + JSON.stringify(err.error.fieldErrors));
        if(err.status == 401) window.alert("Wrong username or password");
      } // used if there is an error
    })
  }
  
  register() {
    this.router.navigate(['/register']);
  }
}
