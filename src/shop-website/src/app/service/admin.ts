import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class Admin {
  private BASE_URL = "http://localhost:8080/user";

  constructor(private http: HttpClient){}

  getUsers(): Observable<User[]>{
    return this.http.post<User[]>(this.BASE_URL, { role: "USER" });
  }

  getUserByIdOrUsername(input: string): Observable<User>{
    return this.http.post<User>(`${this.BASE_URL}/${input}`, { role: "USER" });
  }

  getOwners(): Observable<User[]>{
    return this.http.post<User[]>(this.BASE_URL, { role: "OWNER" });
  }

  getOwnerByIdOrUsername(input: string): Observable<User>{
    return this.http.post<User>(`${this.BASE_URL}/${input}`, { role: "OWNER" });
  }
}
