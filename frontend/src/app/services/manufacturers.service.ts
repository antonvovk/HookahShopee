import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiConfig } from '../config/api.config';
import { map } from 'rxjs/operators';
import { ManufacturerAdapter } from '../core/adapter/manufacturer.adapter';
import { Manufacturer } from '../core/model/manufacturer.model';

@Injectable({
  providedIn: 'root'
})
export class ManufacturersService {

  constructor(private http: HttpClient,
              private adapter: ManufacturerAdapter) {
  }

  getAll(): Observable<Manufacturer[]> {
    return this.http
      .get<Manufacturer[]>(ApiConfig.MANUFACTURER_API_URL + '/all')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  create(manufacturer: Manufacturer): Observable<HttpResponse<string>> {
    return this.http.post<string>(
      ApiConfig.MANUFACTURER_API_URL + '/create',
      manufacturer,
      {observe: 'response'}
    );
  }

  update(manufacturer: Manufacturer): Observable<HttpResponse<any>> {
    return this.http.put<any>(
      ApiConfig.MANUFACTURER_API_URL + '/update',
      manufacturer,
      {observe: 'response'}
    );
  }

  updateImage(uuid: string, image: File): Observable<HttpResponse<any>> {
    const formData = new FormData();
    formData.append('file', image);
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.put<any>(
      ApiConfig.MANUFACTURER_API_URL + '/updateImage',
      formData,
      {observe: 'response', params: params}
    );
  }

  delete(uuid: string): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.delete<any>(
      ApiConfig.MANUFACTURER_API_URL + '/delete',
      {observe: 'response', params: params}
    );
  }
}
