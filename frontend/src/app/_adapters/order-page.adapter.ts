import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { Page } from '../_models/page.model';
import { OrderAdapter } from './order.adapter';
import { Order } from '../_models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderPageAdapter implements Adapter<Page<Order>> {

  constructor(private adapter: OrderAdapter) {

  }

  adapt(item: any): Page<Order> {
    return new Page<Order>({
      items: item.items.map(i => this.adapter.adapt(i)),
      totalElements: item.totalElements
    });
  }
}
