import { State, Action, StateContext, Selector } from '@ngxs/store';
import { GetProducts, SetError, SetLoading, } from '../action/ShopProducts.action';
import { Injectable } from '@angular/core';
import { OwnerProduct } from "../model/products.model";

export interface ShopProductState{
  products: OwnerProduct[],
  isLoading: boolean,
  error: string | null
}

@State<ShopProductState>({
  name: 'shopProducts',
  defaults: {
    products: [],
    isLoading: false,
    error: null
  }
})

@Injectable()
export class ShopProductsState{
  @Selector()
  static getLoading(state: ShopProductState): boolean{
    return state.isLoading;
  }

  @Selector()
  static getProducts(state: ShopProductState): OwnerProduct[]{
    return state.products;
  }
  

  @Action(GetProducts)
  getProducts(ctx: StateContext<ShopProductState>, action: GetProducts){
    const payload = action.payload;
    ctx.patchState({
      products: payload
    });
  }

  @Action(SetLoading)
  setLoading(ctx: StateContext<ShopProductState>, action: SetLoading){
    const payload = action.payload;
    ctx.patchState({
      isLoading: payload
    });
  }

  @Action(SetError)
  setError(ctx: StateContext<ShopProductState>, action: SetError){
    const payload = action.payload;
    ctx.patchState({
      error: payload
    });
  }
}