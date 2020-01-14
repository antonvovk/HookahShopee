import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {Page} from "../model/page.model";
import {Product} from "../model/product.model";
import {ProductAdapter} from "./product.adapter";

@Injectable({
  providedIn: 'root'
})
export class ProductPageAdapter implements Adapter<Page<Product>> {

  constructor(private adapter: ProductAdapter) {

  }

  adapt(item: any): Page<Product> {
    return new Page<Product>(
      item.items.map(item => this.adapter.adapt(item)),
      item.totalElements
    );
  }
}
