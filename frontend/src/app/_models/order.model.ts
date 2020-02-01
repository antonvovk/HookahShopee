import { OrderStatus } from './order-status.enum';
import { User } from './user.model';
import { OrderItem } from './order-item.model';
import { DeliveryType } from './delivery-type.enum';
import { PaymentType } from './payment-type.enum';

export class Order {

  uuid?: string;
  startDate?: Date;
  endDate?: Date;
  status?: OrderStatus;
  price?: number;
  seller?: User;
  client?: User;
  clientUUID?: string;
  orderItems?: OrderItem[];
  firstName?: string;
  lastName?: string;
  phoneNumber?: string;
  email?: string;
  deliveryType?: DeliveryType;
  deliveryCity?: string;
  deliveryDepartment?: string;
  paymentType?: PaymentType;

  constructor(params: Order = {} as Order) {
    let {
      uuid = '',
      startDate = new Date(),
      endDate = new Date(),
      status = OrderStatus.NEW,
      price = 0,
      seller = new User(),
      client = new User(),
      clientUUID,
      orderItems,
      firstName,
      lastName,
      phoneNumber,
      email,
      deliveryType,
      deliveryCity,
      deliveryDepartment,
      paymentType
    } = params;

    this.uuid = uuid;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    this.price = price;
    this.seller = seller;
    this.client = client;
    this.clientUUID = clientUUID;
    this.orderItems = orderItems;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.deliveryType = deliveryType;
    this.deliveryCity = deliveryCity;
    this.deliveryDepartment = deliveryDepartment;
    this.paymentType = paymentType;
  }
}
