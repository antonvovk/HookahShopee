import {Product} from "./product.model";

export class OrderItem {

  price: number;
  quantity: number;
  product: Product;

  constructor(price: number, quantity: number, product: Product) {
    this.price = price;
    this.quantity = quantity;
    this.product = product;
  }
}
