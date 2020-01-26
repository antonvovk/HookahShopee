import { Manufacturer } from './manufacturer.model';
import { ProductQuantityByCities } from './product-quantity-by-cities.model';
import { ProductQuantityBySellers } from './product-quantity-by-sellers.model';

export class Product {

  uuid?: string;
  name?: string;
  price?: number;
  discount?: number;
  finalPrice?: number;
  htmlContent?: string;
  imageName?: string;
  manufacturer?: Manufacturer;
  productQuantityForCities?: ProductQuantityByCities[];
  productQuantityForSellers?: ProductQuantityBySellers[];

  constructor(params: Product = {} as Product) {
    const {
      uuid,
      name,
      price,
      discount,
      finalPrice,
      htmlContent,
      imageName,
      manufacturer,
      productQuantityForCities,
      productQuantityForSellers
    } = params;

    this.uuid = uuid;
    this.name = name;
    this.price = price;
    this.discount = discount;
    this.finalPrice = finalPrice;
    this.htmlContent = htmlContent;
    this.imageName = imageName;
    this.manufacturer = manufacturer;
    this.productQuantityForCities = productQuantityForCities;
    this.productQuantityForSellers = productQuantityForSellers;
  }
}
