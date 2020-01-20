import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {BehaviorSubject, Observable} from "rxjs";
import {JwtUser} from "../core/model/jwt-user.model";
import {ApiConfig} from "../config/api.config";
import {Router} from "@angular/router";
import {Role} from "../core/model/role.model.enum";

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
    return this.currentUserSubject.value != undefined || this.currentUserSubject.value != null;
  }

  login(username: string, password: string) {
    return this.http.post<JwtUser>(ApiConfig.apiUrl + `/user/login`, {username, password})
      .subscribe(jwtUser => {
        localStorage.setItem('currentUser', JSON.stringify(jwtUser));
        this.currentUserSubject.next(jwtUser);
        console.log(jwtUser);

        if (jwtUser.user.role === Role.ADMIN || jwtUser.user.role === Role.SELLER) {
          this.router.navigate(['/control-panel']);
        }
        if (jwtUser.user.role === Role.CLIENT) {
          this.router.navigate(['']);
        }
      });
  }

  register(phoneNumber: string, password: string, firstName: string, lastName: string, city: string): Observable<HttpResponse<any>> {
    let user = {
      phoneNumber: phoneNumber,
      firstName: firstName,
      lastName: lastName,
      password: password,
      cityName: city
    };

    return this.http.post<any>(
      ApiConfig.apiUrl + '/user/register/client',
      user,
      {observe: 'response'}
    );
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login-panel']);
  }
}
