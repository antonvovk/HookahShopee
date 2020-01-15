import {OrderItem} from "./order-item.model";

export class Basket {

  public items: OrderItem[] = [];

  constructor(items: OrderItem[]) {
    this.items = items;
  }
}
