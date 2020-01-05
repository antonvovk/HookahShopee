import {Manufacturer} from "./manufacturer.model";

export class Product {

  name: string;
  price: number;
  discount: number;
  finalPrice: number;
  description: string;
  manufacturer: Manufacturer;

  constructor(name: string, price: number, discount: number, finalPrice: number, description: string, manufacturer: Manufacturer) {
    this.name = name;
    this.price = price;
    this.discount = discount;
    this.finalPrice = finalPrice;
    this.description = description;
    this.manufacturer = manufacturer;
  }
}
