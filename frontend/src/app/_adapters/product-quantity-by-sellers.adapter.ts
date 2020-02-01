import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {ProductQuantityBySellers} from "../_models/product-quantity-by-sellers.model";

@Injectable({
  providedIn: 'root'
})
export class ProductQuantityBySellersAdapter implements Adapter<ProductQuantityBySellers> {

  adapt(item: any): ProductQuantityBySellers {
    return new ProductQuantityBySellers(
      item.quantity,
      item.user
    );
  }
}
