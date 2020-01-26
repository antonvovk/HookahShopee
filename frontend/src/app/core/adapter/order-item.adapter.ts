import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { OrderItem } from '../model/order-item.model';

@Injectable({
  providedIn: 'root'
})
export class OrderItemAdapter implements Adapter<OrderItem> {

  adapt(item: any): OrderItem {
    return new OrderItem({
        price: item.price,
        quantity: item.quantity,
        product: item.product
      }
    );
  }
}
