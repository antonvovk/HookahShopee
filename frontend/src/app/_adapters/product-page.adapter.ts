import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { Page } from '../_models/page.model';
import { Product } from '../_models/product.model';
import { ProductAdapter } from './product.adapter';

@Injectable({
  providedIn: 'root'
})
export class ProductPageAdapter implements Adapter<Page<Product>> {

  constructor(private adapter: ProductAdapter) {
  }

  adapt(item: any): Page<Product> {
    return new Page<Product>(
      item.items.map(product => this.adapter.adapt(product)),
      item.totalElements
    );
  }
}
