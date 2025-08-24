import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Shop } from '../model/shop.model';
import { OwnerProduct } from '../model/products.model';

@Injectable()
export class Owner {
  pipe(arg0: any) {
    throw new Error('Method not implemented.');
  }
  private BASE_URL_SHOP = "http://localhost:8080/shop";
  private BASE_URL_PRODUCT = "http://localhost:8080/product";

  constructor(private http: HttpClient){}
  
  getShop(): Observable<Shop>{
    return this.http.get<Shop>(`${this.BASE_URL_SHOP}`);
  }

  getProductPerOwner(): Observable<HttpResponse<OwnerProduct[]>>{
    return this.http.get<OwnerProduct[]>(`${this.BASE_URL_PRODUCT}/owner`, { observe: 'response' });
  }

  updateShop(shop: Shop){
    return this.http.patch(`${this.BASE_URL_SHOP}`, shop, { observe: 'response' });
  }

  getShopImage(shopImg: string): Observable<Blob> {
    return this.http.get(`${this.BASE_URL_SHOP}/image/${shopImg}`, { responseType: 'blob' });
  }
}
