import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiConfig} from "../config/api.config";
import {map} from "rxjs/operators";
import {ManufacturerAdapter} from "../core/adapter/manufacturer.adapter";
import {Manufacturer} from "../core/model/manufacturer.model";

@Injectable({
  providedIn: 'root'
})
export class ManufacturersService {

  constructor(private http: HttpClient, private adapter: ManufacturerAdapter) {

  }

  findAll(): Observable<Manufacturer[]> {
    return this.http
      .get<Manufacturer[]>(ApiConfig.apiUrl + '/manufacturer')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  findByName(name: string): Observable<Manufacturer> {
    return this.http
      .get<Manufacturer>(ApiConfig.apiUrl + '/manufacturer/' + name)
      .pipe(map((data: any) => data.map(item => this.adapter.adapt(item))));
  }

  insert(manufacturer: Manufacturer): Observable<HttpResponse<string>> {
    return this.http.post<string>(ApiConfig.apiUrl + '/manufacturer', manufacturer, {
      observe: 'response'
    });
  }

  update(manufacturer: Manufacturer, name: string): Observable<HttpResponse<string>> {
    const params = new HttpParams().set('name', name);
    return this.http.put<string>(ApiConfig.apiUrl + '/manufacturer', manufacturer, {
      params: params,
      observe: 'response'
    });
  }

  updateImage(name: string, image: File) {
    const formData = new FormData();
    formData.append('file', image);
    return this.http.put(ApiConfig.apiUrl + '/manufacturer/' + name + '/updateImage', formData);
  }

  delete(name: string): Observable<HttpResponse<string>> {
    const params = new HttpParams().set('name', name);
    return this.http.delete<string>(ApiConfig.apiUrl + '/manufacturer', {
      params: params,
      observe: 'response'
    });
  }
}
