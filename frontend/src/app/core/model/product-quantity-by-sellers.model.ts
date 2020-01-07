import {User} from "./user.model";

export class ProductQuantityBySellers {

  quantity: number;
  user: User;

  constructor(quantity: number, user: User) {
    this.quantity = quantity;
    this.user = user;
  }
}
