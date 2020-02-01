import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { API_URL } from '../globals';
import { OrderPageAdapter } from '../_adapters/order-page.adapter';
import { OrderStatus } from '../_models/order-status.enum';
import { Observable } from 'rxjs';
import { Page } from '../_models/page.model';
import { Order } from '../_models/order.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient,
              private adapter: OrderPageAdapter) {

  }

  findAllByStatus(status: OrderStatus, page: number, size: number): Observable<Page<Order>> {
    let params = new HttpParams();
    params = params.append('p', page.toString());
    params = params.set('s', size.toString());

    return this.http
      .get<Page<Order>>(API_URL + '/order/status/' + status, {params: params})
      .pipe(map((data: any) => this.adapter.adapt(data)));
  }

  findAllBySellerAndStatus(sellerUsername: string, status: OrderStatus, page: number, size: number): Observable<Page<Order>> {
    let params = new HttpParams();
    params = params.append('p', page.toString());
    params = params.set('s', size.toString());

    return this.http
      .get<Page<Order>>(API_URL + '/order/seller/' + sellerUsername + '/status/' + status, {params: params})
      .pipe(map((data: any) => this.adapter.adapt(data)));
  }

  assignToSeller(uuid: string, sellerUsername: string): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('seller', sellerUsername);

    return this.http.put<any>(
      API_URL + '/order/' + uuid + '/assign',
      null,
      {params: params, observe: 'response'}
    );
  }

  createOrder(order: Order): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(
      API_URL + '/order',
      order,
      {observe: 'response'}
    );
  }

  cancelOrder(uuid: string): Observable<HttpResponse<any>> {
    return this.http.put<any>(
      API_URL + '/order/' + uuid + '/cancel',
      null,
      {observe: 'response'}
    );
  }

  completeOrder(uuid: string): Observable<HttpResponse<any>> {
    return this.http.put<any>(
      API_URL + '/order/' + uuid + '/complete',
      null,
      {observe: 'response'}
    );
  }
}
