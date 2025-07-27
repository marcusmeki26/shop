import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../model/category.model';
import { HttpClient } from '@angular/common/http';
import { Product } from '../model/products.model';

@Injectable()
export class User {
  constructor(private http: HttpClient){}

  // Used for fetching categories 
  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>("http://localhost:8080/category");
  }

  // Used for fetching products
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>("http://localhost:8080/product");
  }
}
