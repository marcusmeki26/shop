import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class Auth {
  
  constructor(private http: HttpClient){}

  // Decode access token to fetch user role
  getRoleFromToken(){
    const accessToken = localStorage.getItem("access_token");

    if(accessToken){
      try{
        const decodeToken: any = jwtDecode(accessToken);
        return decodeToken.role;
      }catch(Error){ 
        console.log(Error);
      }
    }
  }

  // For login method
  login(userCreds : UserCredential){
    return this.http.post<AuthToken>("http://localhost:8080/login", userCreds, { responseType: "json" })
  }

  // For register method
  register(newUser: UserCredential) {
    return this.http.post("http://localhost:8080/register", newUser, { responseType: 'json' });
  }
}
