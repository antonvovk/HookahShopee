import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { API_URL } from '../globals';
import { User } from '../_models/user.model';
import { UserAdapter } from '../_adapters/user.adapter';

@Injectable({
  providedIn: 'root'
})
export class SellersService {

  constructor(private http: HttpClient, private adapter: UserAdapter) {

  }

  findAll(): Observable<User[]> {
    return this.http
      .get<User[]>(API_URL + '/user')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  findAllSellers(): Observable<User[]> {
    return this.http
      .get<User[]>(API_URL + '/user/sellers')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }
}
