import {Manufacturer} from "./manufacturer.model";
import {ProductQuantityByCities} from "./product-quantity-by-cities.model";

export class Product {

  name: string;
  price: number;
  discount: number;
  finalPrice: number;
  description: string;
  imageName: string;
  manufacturer: Manufacturer;
  productQuantity: ProductQuantityByCities[];

  constructor(name?: string, price?: number, discount?: number, finalPrice?: number, description?: string, imageName?: string, manufacturer?: Manufacturer, productQuantity?: ProductQuantityByCities[]) {
    this.name = name;
    this.price = price;
    this.discount = discount;
    this.finalPrice = finalPrice;
    this.description = description;
    this.manufacturer = manufacturer;
    this.imageName = imageName;
    this.productQuantity = productQuantity;
  }
}
