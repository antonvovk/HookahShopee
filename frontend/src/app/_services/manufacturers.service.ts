import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { MANUFACTURER_API_URL } from '../globals';
import { ManufacturerAdapter } from '../_adapters/manufacturer.adapter';
import { Manufacturer } from '../_models/manufacturer.model';

@Injectable({
  providedIn: 'root'
})
export class ManufacturersService {

  constructor(private http: HttpClient,
              private adapter: ManufacturerAdapter) {
  }

  getAll(): Observable<Manufacturer[]> {
    return this.http
      .get<Manufacturer[]>(MANUFACTURER_API_URL + '/all')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  create(manufacturer: Manufacturer): Observable<HttpResponse<string>> {
    return this.http.post<string>(
      MANUFACTURER_API_URL + '/create',
      manufacturer,
      {observe: 'response'}
    );
  }

  update(manufacturer: Manufacturer): Observable<HttpResponse<any>> {
    return this.http.put<any>(
      MANUFACTURER_API_URL + '/update',
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
      MANUFACTURER_API_URL + '/updateImage',
      formData,
      {observe: 'response', params: params}
    );
  }

  delete(uuid: string): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.delete<any>(
      MANUFACTURER_API_URL + '/delete',
      {observe: 'response', params: params}
    );
  }
}
