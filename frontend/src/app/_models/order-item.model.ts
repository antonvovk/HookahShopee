import { Product } from './product.model';

export class OrderItem {

  price?: number;
  quantity?: number;
  product?: Product;
  productUUID?: string;

  constructor(params: OrderItem = {} as OrderItem) {
    let {
      price,
      quantity,
      product,
      productUUID
    } = params;

    this.price = price;
    this.quantity = quantity;
    this.product = product;
    this.productUUID = productUUID;
  }
}
