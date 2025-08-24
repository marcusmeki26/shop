import { State, Action, StateContext, Selector } from '@ngxs/store';
import { AddShop, UpdateShop } from '../action/ShopDetails.action';
import { Injectable } from '@angular/core';
import { Shop } from '../model/shop.model';

@State<Shop>({
  name: 'shopDetails',
  defaults: {
    shopName: '',
    description: '',
    phoneNumber: '',
    shopImg: '',
  }
})
@Injectable()
export class ShopDetailsState{

  @Selector()
  static getShop(state: Shop){  
    return state;
  }

  @Action(AddShop)
  addShop(ctx: StateContext<Shop>, action: AddShop){
    const payload = action.payload;
    ctx.setState({ 
      shopName: payload.shopName,
      description: payload.description,
      phoneNumber: payload.phoneNumber,
      shopImg: payload.shopImg,
    });
  }

  @Action(UpdateShop)
  updateShop(ctx: StateContext<Shop>, action: UpdateShop){
    const payload = action.payload;
    ctx.patchState(payload);
  }
}