import {Product} from "./product.model";
import {User} from "./user.model";

export class ProductItem {

  constructor(quantity: number, product: Product, seller: User) {
    this._quantity = quantity;
    this._product = product;
    this._seller = seller;
  }

  private _quantity: number;

  get quantity(): number {
    return this._quantity;
  }

  set quantity(value: number) {
    this._quantity = value;
  }

  private _product: Product;

  get product(): Product {
    return this._product;
  }

  set product(value: Product) {
    this._product = value;
  }

  private _seller: User;

  get seller(): User {
    return this._seller;
  }

  set seller(value: User) {
    this._seller = value;
  }
}
