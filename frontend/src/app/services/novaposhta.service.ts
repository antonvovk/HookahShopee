import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ApiConfig } from '../config/api.config';
import { map } from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class NovaposhtaService {

  constructor(private http: HttpClient) {

  }

  findCityByName(name?: string) {
    let params = new HttpParams();
    if (name != null) {
      params = params.append('name', name);
    }

    return this.http.get<any[]>(ApiConfig.API_URL + '/novaposhta/cities', {params: params})
      .pipe(map((res: any) => res.data));
  }

  findWarehouseByName(name?: string) {
    let params = new HttpParams();
    if (name != null) {
      params = params.append('name', name);
    }

    return this.http.get<any[]>(ApiConfig.API_URL + '/novaposhta/warehouses', {params: params})
      .pipe(map((res: any) => res.data));
  }
}

