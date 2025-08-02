import { Location } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../app/service/auth';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register implements OnInit, OnDestroy{

  private subscription?: Subscription;
  private numberPattern = "^09[0-9]{9}";

  isOwner: boolean = false;
  isSubmitted: boolean = false;

  constructor(private router: Router, 
    private location: Location,
    private fb: FormBuilder,
    private authSevice: Auth){}

    registerForm = new FormGroup<any>({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
    });
    

  registerUser(){
    this.isSubmitted = true; 

    if(!this.registerForm.invalid){
      this.authSevice.register(this.registerForm.value).subscribe({
        next: res => {
          window.alert("Successfully Created\n" + JSON.stringify(res));
          this.router.navigate(['/login']);
        },
        error: err => {
          window.alert("Unsuccessful\n" + JSON.stringify(err.error.fieldErrors)); 
        }
      });
    }
  }

  goBack() {
    this.location.back();
  }

  ngOnInit(): void {

    this.subscription = this.registerForm.get('role')?.valueChanges.subscribe(selectedRole => {
      if(selectedRole === 'owner'){
        this.isOwner = true;
        this.registerForm.addControl('ownerDetails', new FormGroup({
          shopName: new FormControl('', [Validators.required]),
          description: new FormControl('', [Validators.required]),
          phoneNumber: new FormControl('', [Validators.required, Validators.pattern(this.numberPattern)]),
          shopImg: new FormControl(null, [Validators.required]),
        }));
      }else{
        this.isOwner = false;
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}

