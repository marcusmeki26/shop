import { Location } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  private selectedFile: File | null = null;

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

  onFileInput($event: Event) {
    const inputElement = $event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      const file = inputElement.files[0]; // Get the first selected file
      this.selectedFile = file;
    }
  }
    
  registerUser(){
    this.isSubmitted = true; 
    if(!this.registerForm.invalid){
      // console.log(this.registerForm.value);
      const formData = new FormData(); 
      for(const registerFormControls in this.registerForm.controls){
        if(registerFormControls != 'ownerDetails'){
          const control = this.registerForm.controls[registerFormControls];
          formData.append(registerFormControls, control.value);
        }else{
          const ownerDetails = (this.registerForm.controls['ownerDetails'] as FormGroup).controls;
          for(const ownerDetailsControls in ownerDetails){
            const control = (this.registerForm.controls['ownerDetails'] as FormGroup).controls[ownerDetailsControls];
            if(ownerDetailsControls == "shopImg"){
              if(this.selectedFile instanceof File){
                formData.append('file', this.selectedFile, this.selectedFile.name);
              }
            }else{
              formData.append(ownerDetailsControls, control.value); 
            }
          }
        }
      }
      
      if(formData.get("role") == "user"){
        console.log(formData);
        // this.authSevice.registerUser(formData.values).subscribe({
        //   next: res => {
        //     window.alert("Successfully Created\n" + JSON.stringify(res));
        //     this.router.navigate(['/login']);
        //   },
        //   error: err => {
        //     window.alert("Unsuccessful\n" + JSON.stringify(err.error.fieldErrors)); 
        //   }
        // });
      }else{
        this.authSevice.registerOwner(formData).subscribe({
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
          shopImg: new FormControl('', [Validators.required]),
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

