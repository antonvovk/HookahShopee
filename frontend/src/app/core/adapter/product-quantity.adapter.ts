import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {ProductQuantity} from "../model/product-quantity.model";

@Injectable({
  providedIn: 'root'
})
export class ProductQuantityAdapter implements Adapter<ProductQuantity> {

  adapt(item: any): ProductQuantity {
    return new ProductQuantity(
      item.quantity,
      item.user
    );
  }
}
