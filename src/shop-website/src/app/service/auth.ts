import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { map, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Auth {
  constructor(private http: HttpClient, private router: Router){}

  // get the access token from local storage
  getAccessToken() {
    return localStorage.getItem("access_token");
  }

  // logout if refresh token fails
  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }


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

  // Decode access token to fetch username
  getUsername(){
    const accessToken = localStorage.getItem("access_token");

    if(accessToken){
      try{
        const decodeToken: any = jwtDecode(accessToken);
        return decodeToken.sub;
      }catch(Error){ 
        console.log(Error);
      }
    }
  }

  // fetch new access token
  refreshToken() {
    const refreshToken = localStorage.getItem("refresh_token");
    console.log(refreshToken);
    return this.http.post<{accessToken: string}>('http://localhost:8080/refresh', { refreshToken }).pipe(
      tap(response => {
        localStorage.setItem("access_token", response.accessToken)
      }),
      map(response => {
        return response.accessToken;
      })
    )
  }

  // For login method
  login(userCreds : UserLogin){
    return this.http.post<AuthToken>("http://localhost:8080/login", userCreds)
  }

  // For register method
  register(newUser: UserLogin) {
    return this.http.post("http://localhost:8080/register", newUser);
  }
}
