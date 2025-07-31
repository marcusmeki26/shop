import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { map, Observable, tap, throwError } from 'rxjs';

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
  getRoleFromToken(): string | undefined{
    const accessToken = localStorage.getItem("access_token");
    
    if(!accessToken){
      return undefined;
    }
    try{
      const decodeToken: any = jwtDecode(accessToken);
      return decodeToken.role;
    }catch(Error){ 
      return undefined;
    }
  }

  // Decode access token to fetch username
  getUsername(): string | undefined {
    const accessToken = localStorage.getItem("access_token");

    if(!accessToken){
      return undefined;
    }

    try{
      const decodeToken: any = jwtDecode(accessToken);
      return decodeToken.sub;
    }catch(Error){ 
      return undefined;
    }
  }

  // fetch new access token
  refreshToken(): Observable<string>{
    const refreshToken = localStorage.getItem("refresh_token");
    if(!refreshToken){
      return throwError(() => new Error("No Refresh Token found"));
    }

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
