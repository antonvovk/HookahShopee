import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiConfig} from "../config/api.config";
import {map} from "rxjs/operators";
import {ProductAdapter} from "../core/adapter/product.adapter";
import {Product} from "../core/model/product.model";
import {ProductQuantityBySellers} from "../core/model/product-quantity-by-sellers.model";
import {ProductQuantityBySellersAdapter} from "../core/adapter/product-quantity-by-sellers.adapter";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient,
              private adapter: ProductAdapter,
              private productQuantityBySellersAdapter: ProductQuantityBySellersAdapter) {

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

  insert(product: Product): Observable<HttpResponse<any>> {
    return this.http
      .post<any>(ApiConfig.apiUrl + '/product', product, {observe: 'response'});
  }

  update(name: string, product: Product): Observable<HttpResponse<string>> {
    return this.http.put<string>(ApiConfig.apiUrl + '/product/' + name, product, {
      observe: 'response'
    });
  }

  updateImage(name: string, image: File): Observable<HttpResponse<any>> {
    const formData = new FormData();
    formData.append('file', image);
    return this.http
      .put<any>(ApiConfig.apiUrl + '/product/' + name + '/updateImage', formData, {observe: 'response'});
  }

  updateQuantity(name: string, seller: string, quantity: number): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('seller', seller);
    params = params.set('quantity', quantity.toString());

    return this.http
      .put<any>(ApiConfig.apiUrl + '/product/' + name + '/updateQuantity', null, {params: params, observe: 'response'});
  }

  delete(name: string): Observable<HttpResponse<any>> {
    return this.http
      .delete<any>(ApiConfig.apiUrl + '/product/' + name, {observe: 'response'});
  }
}
