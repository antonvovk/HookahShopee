import {Product} from "./product.model";

export class OrderItem {

  constructor(price: number, quantity: number, product: Product) {
    this._price = price;
    this._quantity = quantity;
    this._product = product;
  }

  private _price: number;

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
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
}
