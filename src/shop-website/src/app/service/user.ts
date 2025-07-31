import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Category } from '../model/category.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Product } from '../model/products.model';
import { ActivatedRoute } from '@angular/router';

@Injectable()
export class User {
  
  private BASE_URL = "http://localhost:8080";

  constructor(private http: HttpClient){}
  
  // Used for fetching categories 
  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.BASE_URL}/category`);
  }
  
  // Used for fetching products
  getProductsOrByName(keyword?: string | null): Observable<Product[]> {
    if(keyword){
      const productName = new HttpParams().set("keyword", keyword);
      return this.http.get<Product[]>(`${this.BASE_URL}/product`, { params: productName });
    }else{
      return this.http.get<Product[]>(`${this.BASE_URL}/product`);
    }
  }

  // Used for fetching products by category
  getProductsByCategory(categoryName: string | undefined): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.BASE_URL}/product/${categoryName}`);
  }
}


