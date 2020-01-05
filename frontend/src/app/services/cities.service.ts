import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiConfig} from "../config/api.config";
import {map} from "rxjs/operators";
import {CityAdapter} from "../core/adapter/city.adapter";
import {City} from "../core/model/city.model";

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  constructor(private http: HttpClient, private adapter: CityAdapter) {

  }

  findAll(): Observable<City[]> {
    return this.http
      .get<City[]>(ApiConfig.apiUrl + '/city')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }
}
