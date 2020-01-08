import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {Order} from "../model/order.model";
import {OrderItemAdapter} from "./order-item.adapter";

@Injectable({
  providedIn: 'root'
})
export class OrderAdapter implements Adapter<Order> {

  constructor(private adapter: OrderItemAdapter) {

  }

  adapt(item: any): Order {
    return new Order(
      item.uuid,
      item.startDate,
      item.endDate,
      item.status,
      item.price,
      item.seller,
      item.client,
      item.orderItems.map(item => this.adapter.adapt(item))
    );
  }
}
