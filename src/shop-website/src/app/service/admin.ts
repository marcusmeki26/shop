import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from './user';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Injectable()
export class Admin {
  private BASE_URL = "http://localhost:8080/user";

  constructor(private http: HttpClient, private route: ActivatedRoute){}

  getUsers(): Observable<User[] > | null{
    // Gets the query paramater
    const role = this.route.snapshot.queryParamMap.get("role"); // gets the query parameter using queryParamMap
    if(role){
      const roleParams = new HttpParams().set("role", role); // creates an object and use the object to pass along with the get request
      return this.http.get<User[]>(this.BASE_URL, { params: roleParams });
    }

    return null;
  }

  getUserByIdOrUsername(input: string): Observable<User | null>{
    // Gets the query paramater
    const role = this.route.snapshot.queryParamMap.get("role"); // gets the query parameter using queryParamMap

    if(role){
      const roleParams = new HttpParams().set("role", role); // creates an object and use the object to pass along with the get request
      return this.http.get<User>(`${this.BASE_URL}/${input}`, { params: roleParams });
    }
    
    return of(null);
  }
}
