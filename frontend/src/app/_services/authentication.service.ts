import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { API_URL } from '../globals';
import { JwtUser } from '../_models/jwt-user.model';
import { Role } from '../_models/role.model.enum';

@Injectable({providedIn: 'root'})
export class AuthenticationService {

  public currentUser: Observable<JwtUser>;
  private currentUserSubject: BehaviorSubject<JwtUser>;

  constructor(private http: HttpClient, private router: Router) {
    this.currentUserSubject = new BehaviorSubject<JwtUser>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): JwtUser {
    return this.currentUserSubject.value;
  }

  public isAuthenticated(): boolean {
    return this.currentUserSubject.value !== undefined || true;
  }

  login(username: string, password: string) {
    return this.http.post<JwtUser>(API_URL + `/user/login`, {username, password})
      .subscribe(jwtUser => {
        localStorage.setItem('currentUser', JSON.stringify(jwtUser));
        this.currentUserSubject.next(jwtUser);
        console.log(jwtUser);

        if (jwtUser.user.role === Role.ADMIN || jwtUser.user.role === Role.SELLER) {
          this.router.navigate(['control-panel']);
        }
        if (jwtUser.user.role === Role.CLIENT) {
          this.router.navigate(['']);
        }
      });
  }

  register(phoneNumber: string, password: string, firstName: string, lastName: string, cityUUID: string): Observable<HttpResponse<any>> {
    const user = {
      phoneNumber: phoneNumber,
      firstName: firstName,
      lastName: lastName,
      password: password,
      cityUUID: cityUUID
    };

    return this.http.post<any>(
      API_URL + '/user/register/client',
      user,
      {observe: 'response'}
    );
  }

  isSellerOrAdmin(): boolean {
    if (!this.isAuthenticated()) {
      return false;
    }

    return this.currentUserValue.user.role === Role.SELLER ||
      this.currentUserValue.user.role === Role.ADMIN;
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login-panel']);
  }
}
