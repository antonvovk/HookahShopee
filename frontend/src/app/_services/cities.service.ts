import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CITY_API_URL } from '../globals';
import { CityAdapter } from '../_adapters/city.adapter';
import { City } from '../_models/city.model';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  constructor(private http: HttpClient,
              private adapter: CityAdapter) {
  }

  findAll(): Observable<City[]> {
    return this.http
      .get<City[]>(CITY_API_URL + '/all')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }
}
