import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {Product} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductAdapter implements Adapter<Product> {

  adapt(item: any): Product {
    return new Product(
      item.name,
      item.price,
      item.discount,
      item.finalPrice,
      item.description,
      item.imageName,
      item.manufacturer,
      item.productQuantity
    );
  }
}
