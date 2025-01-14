import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { PRODUCT_API_URL } from '../globals';
import { ProductPageAdapter } from '../_adapters/product-page.adapter';
import { ProductAdapter } from '../_adapters/product.adapter';
import { ProductListRequest } from '../_models/product-list-request.model';
import { Page } from '../_models/page.model';
import { Product } from '../_models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient,
              private adapter: ProductPageAdapter,
              private productAdapter: ProductAdapter) {
  }

  getAll(page: number, size: number, requestModel?: ProductListRequest, sortColumn?: string): Observable<Page<Product>> {
    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());

    if (sortColumn != null) {
      params = params.append('sortColumn', sortColumn);
    }

    if (requestModel !== null) {
      if (requestModel.manufacturers != null && requestModel.manufacturers.length !== 0) {
        params = params.append('manufacturers', requestModel.manufacturers.join(', '));
      }

      if (requestModel.startPrice != null) {
        params = params.append('startPrice', requestModel.startPrice.toString());
      }

      if (requestModel.endPrice != null) {
        params = params.append('endPrice', requestModel.endPrice.toString());
      }

      if (requestModel.cityUUID != null) {
        params = params.append('cityUUID', requestModel.cityUUID);
      }

      if (requestModel.isOnDiscount != null) {
        params = params.append('isOnDiscount', requestModel.isOnDiscount ? 'true' : 'false');
      }
    }

    return this.http
      .get<Page<Product>>(
        PRODUCT_API_URL + '/all',
        {params: params}
      ).pipe(map((data: any) => this.adapter.adapt(data)));
  }

  findByUUID(uuid: string): Observable<Product> {
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.get<Product>(
      PRODUCT_API_URL + '/uuid',
      {params: params}
    ).pipe(map((data: any) => this.productAdapter.adapt(data)));
  }

  create(product: Product): Observable<HttpResponse<any>> {
    return this.http.post<any>(
      PRODUCT_API_URL + '/create',
      product,
      {observe: 'response'}
    );
  }

  update(product: Product): Observable<HttpResponse<string>> {
    return this.http.put<string>(
      PRODUCT_API_URL + '/update',
      product,
      {observe: 'response'}
    );
  }

  updateImage(uuid: string, image: File): Observable<HttpResponse<any>> {
    const formData = new FormData();
    formData.append('file', image);
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.put<any>(
      PRODUCT_API_URL + '/updateImage',
      formData,
      {observe: 'response', params: params}
    );
  }

  updateQuantity(uuid: string, sellerUUID: string, quantity: number): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('uuid', uuid);
    params = params.append('sellerUUID', sellerUUID);
    params = params.append('quantity', quantity.toString());

    return this.http.put<any>(
      PRODUCT_API_URL + '/updateQuantity',
      null,
      {params: params, observe: 'response'}
    );
  }

  delete(uuid: string): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.delete<any>(
      PRODUCT_API_URL + '/delete',
      {observe: 'response', params: params}
    );
  }
}
