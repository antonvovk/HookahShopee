import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { Order } from '../model/order.model';
import { OrderItemAdapter } from './order-item.adapter';

@Injectable({
  providedIn: 'root'
})
export class OrderAdapter implements Adapter<Order> {

  constructor(private adapter: OrderItemAdapter) {

  }

  adapt(item: any): Order {
    return new Order({
        uuid: item.uuid,
        startDate: item.startDate,
        endDate: item.endDate,
        status: item.status,
        price: item.price,
        seller: item.seller,
        client: item.client,
        orderItems: item.orderItems,
        firstName: item.firstName,
        lastName: item.lastName,
        phoneNumber: item.phoneNumber,
        email: item.email,
        deliveryType: item.deliveryType,
        deliveryCity: item.deliveryCity,
        deliveryDepartment: item.deliveryDepartment,
        paymentType: item.paymentType
      }
    );
  }
}
