import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { Product } from '../_models/product.model';
import { ManufacturerAdapter } from './manufacturer.adapter';
import { ProductQuantityByCitiesAdapter } from './product-quantity-by-cities.adapter';
import { ProductQuantityBySellersAdapter } from './product-quantity-by-sellers.adapter';

@Injectable({
  providedIn: 'root'
})
export class ProductAdapter implements Adapter<Product> {

  constructor(private manufacturerAdapter: ManufacturerAdapter,
              private productQuantityByCitiesAdapter: ProductQuantityByCitiesAdapter,
              private productQuantityBySellersAdapter: ProductQuantityBySellersAdapter) {
  }

  adapt(item: any): Product {
    return new Product({
        uuid: item.uuid,
        name: item.name,
        price: item.price,
        discount: item.discount,
        finalPrice: item.finalPrice,
        htmlContent: item.htmlContent,
        imageName: item.imageName,
        manufacturer: this.manufacturerAdapter.adapt(item.manufacturer),
        productQuantityForCities: item.productQuantityForCities.map(p => this.productQuantityByCitiesAdapter.adapt(p)),
        productQuantityForSellers: item.productQuantityForSellers.map(p => this.productQuantityBySellersAdapter.adapt(p))
      }
    );
  }
}
