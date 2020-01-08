import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {Page} from "../model/page.model";
import {OrderAdapter} from "./order.adapter";
import {Order} from "../model/order.model";

@Injectable({
  providedIn: 'root'
})
export class OrderPageAdapter implements Adapter<Page<Order>> {

  constructor(private adapter: OrderAdapter) {

  }

  adapt(item: any): Page<Order> {
    return new Page<Order>(
      item.items.map(item => this.adapter.adapt(item)),
      item.totalElements
    );
  }
}
