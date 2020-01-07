import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {ProductQuantityByCities} from "../model/product-quantity-by-cities.model";

@Injectable({
  providedIn: 'root'
})
export class ProductQuantityByCitiesAdapter implements Adapter<ProductQuantityByCities> {

  adapt(item: any): ProductQuantityByCities {
    return new ProductQuantityByCities(
      item.quantity,
      item.city
    );
  }
}
