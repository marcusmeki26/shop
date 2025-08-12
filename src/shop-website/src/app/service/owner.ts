import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Shop } from '../model/shop.model';

@Injectable()
export class Owner {
  private BASE_URL = "http://localhost:8080/shop";

  constructor(private http: HttpClient){}
  
  getShop(): Observable<Shop>{
    return this.http.get<Shop>(`${this.BASE_URL}`);
  }

  updateShop(shop: Shop){
    return this.http.patch(`${this.BASE_URL}`, shop, { observe: 'response' });
  }

  getShopImage(shopImg: string): Observable<Blob> {
    return this.http.get(`${this.BASE_URL}/image/${shopImg}`, { responseType: 'blob' });
  }
}
