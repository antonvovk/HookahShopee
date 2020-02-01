import {City} from "./city.model";

export class ProductQuantityByCities {

  quantity: number;
  city: City;

  constructor(quantity: number, city: City) {
    this.quantity = quantity;
    this.city = city;
  }
}
