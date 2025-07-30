import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Category } from '../model/category.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Product } from '../model/products.model';
import { ActivatedRoute } from '@angular/router';

@Injectable()
export class User {
  private BASE_URL = "http://localhost:8080";

  constructor(private http: HttpClient, private route: ActivatedRoute){}
  
  // Used for fetching categories 
  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.BASE_URL}/category`);
  }
  
  // Used for fetching products
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.BASE_URL}/product`);
  }

  getProductsByName(keyword: string | null): Observable<Product[] | null> {
    if(keyword){
      const productName = new HttpParams().set("keyword", keyword);
      return this.http.get<Product[]>(`${this.BASE_URL}/product`, { params: productName });
    }
    
    return of(null);
  }
}
