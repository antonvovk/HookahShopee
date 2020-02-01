import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { City } from '../_models/city.model';
import { AuthenticationService } from './authentication.service';

@Injectable({providedIn: 'root'})
export class CitySaverService {

  private readonly LC_NAME = 'currentCity';
  private currentCitySubject: BehaviorSubject<City>;

  constructor(private authenticationService: AuthenticationService) {
    this.currentCitySubject = new BehaviorSubject<City>(JSON.parse(localStorage.getItem(this.LC_NAME)));

    if (this.currentCitySubject.value == null) {
      if (authenticationService.isAuthenticated()) {
        this.currentCitySubject = new BehaviorSubject<City>(authenticationService.currentUserValue.user.city);
      }
    }
  }

  public get city(): City {
    return this.currentCitySubject.value;
  }

  public setCity(city: City): void {
    localStorage.setItem(this.LC_NAME, JSON.stringify(city));
    this.currentCitySubject.next(city);
  }
}
