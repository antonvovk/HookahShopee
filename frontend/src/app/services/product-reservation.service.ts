import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiConfig} from "../config/api.config";

@Injectable({
  providedIn: 'root'
})
export class ProductReservationService {

  constructor(private http: HttpClient) {

  }

  addProductReservation(productName: string, cityName: string, quantity: number): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('productName', productName);
    params = params.append('cityName', cityName);
    params = params.append('quantity', quantity.toString());

    return this.http.put<any>(
      ApiConfig.apiUrl + '/reservation/add',
      null,
      {params: params, observe: 'response'}
    );
  }

  removeProductReservation(productName: string, cityName: string, quantity: number): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('productName', productName);
    params = params.append('cityName', cityName);
    params = params.append('quantity', quantity.toString());

    return this.http.put<any>(
      ApiConfig.apiUrl + '/reservation/remove',
      null,
      {params: params, observe: 'response'}
    );
  }

  clearProductReservation(productName: string, cityName: string): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('productName', productName);
    params = params.append('cityName', cityName);

    return this.http.put<any>(
      ApiConfig.apiUrl + '/reservation/clear',
      null,
      {params: params, observe: 'response'}
    );
  }
}
