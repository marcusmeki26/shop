import { Component, inject, OnInit } from '@angular/core';
import { Store } from '@ngxs/store';
import { Owner } from '../../service/owner';
import { take } from 'rxjs';
import { GetProducts, SetLoading } from '../../action/ShopProducts.action';
import { ShopProductsState } from '../../state/ShopProducts.state';
import { CommonModule } from '@angular/common';
import { ComponentsModule } from '../../shared/components/components-module';

@Component({
  selector: 'app-shop-products',
  standalone: true,
  imports: [
    CommonModule,
    ComponentsModule
  ],
  templateUrl: './shop-products.html',
  styleUrls: ['./shop-products.css']
})
export class ShopProducts implements OnInit{
  store = inject(Store);
  ownerService = inject(Owner);

  products$ = this.store.select(ShopProductsState.getProducts);
  isLoading$ = this.store.select(ShopProductsState.getLoading);
  
  ngOnInit(): void {
    this.ownerService.getProductPerOwner().pipe(take(1))
    .subscribe({
      next: (res) => {
        if(res.status === 200 || res.status === 204){
          this.store.dispatch(new GetProducts(res.body ?? []));
          this.store.dispatch(new SetLoading(true));
        }
      }
    })
  }
}
