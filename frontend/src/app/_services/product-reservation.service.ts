import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RESERVATION_API_URL } from '../globals';

@Injectable({
  providedIn: 'root'
})
export class ProductReservationService {

  constructor(private http: HttpClient) {
  }

  addProductReservation(productUUID: string, cityUUID: string, quantity: number): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('productUUID', productUUID);
    params = params.append('cityUUID', cityUUID);
    params = params.append('quantity', quantity.toString());

    return this.http.put<any>(
      RESERVATION_API_URL + '/add',
      null,
      {params: params, observe: 'response'}
    );
  }

  removeProductReservation(productUUID: string, cityUUID: string, quantity: number): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('productUUID', productUUID);
    params = params.append('cityUUID', cityUUID);
    params = params.append('quantity', quantity.toString());

    return this.http.put<any>(
      RESERVATION_API_URL + '/remove',
      null,
      {params: params, observe: 'response'}
    );
  }
}
