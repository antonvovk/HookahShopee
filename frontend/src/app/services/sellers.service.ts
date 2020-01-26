import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserAdapter } from '../core/adapter/user.adapter';
import { User } from '../core/model/user.model';
import { ApiConfig } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class SellersService {

  constructor(private http: HttpClient, private adapter: UserAdapter) {

  }

  findAll(): Observable<User[]> {
    return this.http
      .get<User[]>(ApiConfig.API_URL + '/user')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  findAllSellers(): Observable<User[]> {
    return this.http
      .get<User[]>(ApiConfig.API_URL + '/user/sellers')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }
}
