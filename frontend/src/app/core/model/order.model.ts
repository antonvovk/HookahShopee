import {OrderStatus} from "./order-status.model.enum";
import {User} from "./user.model";
import {OrderItem} from "./order-item.model";

export class Order {

  startDate: Date;
  endDate: Date;
  status: OrderStatus;
  price: number;
  seller: User;
  client: User;
  orderItems: OrderItem[];
}
