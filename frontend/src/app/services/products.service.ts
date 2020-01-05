import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiConfig} from "../config/api.config";
import {map} from "rxjs/operators";
import {ProductAdapter} from "../core/adapter/product.adapter";
import {Product} from "../core/model/product.model";
import {ProductQuantity} from "../core/model/product-quantity.model";
import {ProductQuantityAdapter} from "../core/adapter/product-quantity.adapter";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient,
              private adapter: ProductAdapter,
              private productQuantityAdapter: ProductQuantityAdapter) {

  }

  findAllByManufacturer(manufacturerName: string): Observable<Product[]> {
    return this.http
      .get<Product[]>(ApiConfig.apiUrl + '/product/byManufacturer/' + manufacturerName)
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  getQuantities(name: string): Observable<ProductQuantity[]> {
    return this.http
      .get<ProductQuantity[]>(ApiConfig.apiUrl + '/product/' + name + '/quantity')
      .pipe(map((data: any[]) => data.map(item => this.productQuantityAdapter.adapt(item))));
  }

  getQuantityByCity(name: string, cityName: string): Observable<number> {
    return this.http
      .get<number>(ApiConfig.apiUrl + '/product/' + name + '/quantity/byCity/' + cityName);
  }
}
