import {Injectable} from "@angular/core";
import {HttpClient, HttpResponse} from "@angular/common/http";
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
    return this.http
      .post<string>(ApiConfig.apiUrl + '/manufacturer', manufacturer, {observe: 'response'});
  }

  update(manufacturer: Manufacturer, name: string): Observable<HttpResponse<string>> {
    return this.http
      .put<string>(ApiConfig.apiUrl + '/manufacturer/' + name, manufacturer, {observe: 'response'});
  }

  updateImage(name: string, image: File): Observable<HttpResponse<string>> {
    const formData = new FormData();
    formData.append('file', image);
    return this.http
      .put<string>(ApiConfig.apiUrl + '/manufacturer/' + name + '/updateImage', formData, {observe: 'response'});
  }

  delete(name: string): Observable<HttpResponse<string>> {
    return this.http
      .delete<string>(ApiConfig.apiUrl + '/manufacturer/' + name, {observe: 'response'});
  }
}
