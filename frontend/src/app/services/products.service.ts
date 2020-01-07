import {Injectable} from "@angular/core";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiConfig} from "../config/api.config";
import {map} from "rxjs/operators";
import {ProductAdapter} from "../core/adapter/product.adapter";
import {Product} from "../core/model/product.model";
import {ProductQuantityBySellers} from "../core/model/product-quantity-by-sellers.model";
import {ProductQuantityBySellersAdapter} from "../core/adapter/product-quantity-by-sellers.adapter";
import {ProductQuantityByCities} from "../core/model/product-quantity-by-cities.model";
import {ProductQuantityByCitiesAdapter} from "../core/adapter/product-quantity-by-cities.adapter";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient,
              private adapter: ProductAdapter,
              private productQuantityBySellersAdapter: ProductQuantityBySellersAdapter,
              private productQuantityByCitiesAdapter: ProductQuantityByCitiesAdapter) {

  }

  findAllByManufacturer(manufacturerName: string): Observable<Product[]> {
    return this.http
      .get<Product[]>(ApiConfig.apiUrl + '/product/byManufacturer/' + manufacturerName)
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  getQuantitiesBySellers(name: string): Observable<ProductQuantityBySellers[]> {
    return this.http
      .get<ProductQuantityBySellers[]>(ApiConfig.apiUrl + '/product/' + name + '/quantity/bySellers')
      .pipe(map((data: any[]) => data.map(item => this.productQuantityBySellersAdapter.adapt(item))));
  }

  getQuantitiesByCities(name: string): Observable<ProductQuantityByCities[]> {
    return this.http
      .get<ProductQuantityByCities[]>(ApiConfig.apiUrl + '/product/' + name + '/quantity/byCities')
      .pipe(map((data: any[]) => data.map(item => this.productQuantityByCitiesAdapter.adapt(item))));
  }

  getQuantityByCity(name: string, cityName: string): Observable<number> {
    return this.http
      .get<number>(ApiConfig.apiUrl + '/product/' + name + '/quantity/byCity/' + cityName);
  }

  update(name: string, product: Product): Observable<HttpResponse<string>> {
    return this.http.put<string>(ApiConfig.apiUrl + '/product/' + name, product, {
      observe: 'response'
    });
  }
}
