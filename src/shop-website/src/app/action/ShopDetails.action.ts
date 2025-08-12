import { Shop } from "../model/shop.model";

export class AddShop{
  static readonly type = '[SHOP] Add Shop';

  constructor(public payload: Shop){}
}

export class UpdateShop{
  static readonly type = '[SHOP] Update Shop';

  constructor(public payload: Shop){}
}