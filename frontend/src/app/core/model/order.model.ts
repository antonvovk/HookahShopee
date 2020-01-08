import {OrderStatus} from "./order-status.model.enum";
import {User} from "./user.model";
import {OrderItem} from "./order-item.model";

export class Order {

  uuid: string;
  startDate: Date;
  endDate: Date;
  status: OrderStatus;
  price: number;
  seller: User;
  client: User;
  orderItems: OrderItem[];

  constructor(uuid: string, startDate: Date, endDate: Date, status: OrderStatus, price: number, seller: User, client: User, orderItems: OrderItem[]) {
    this.uuid = uuid;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    this.price = price;
    this.seller = seller;
    this.client = client;
    this.orderItems = orderItems;
  }
}
