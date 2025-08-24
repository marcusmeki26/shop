import { OwnerProduct } from "../model/products.model";

export class SetLoading{
  static readonly type = '[PRODUCT] Set Loading';

  constructor(public payload: boolean){}
}

export class SetError{
  static readonly type = '[PRODUCT] Set Error';

  constructor(public payload: string){}
}

export class GetProducts{
  static readonly type = '[PRODUCT] Get Products';

  constructor(public payload: OwnerProduct[]){}
}