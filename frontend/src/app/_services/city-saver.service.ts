import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { City } from '../_models/city.model';

@Injectable({providedIn: 'root'})
export class CitySaverService {

  private readonly LC_NAME = 'currentCity';
  private currentCitySubject: BehaviorSubject<City>;

  constructor() {
    this.currentCitySubject = new BehaviorSubject<City>(JSON.parse(localStorage.getItem(this.LC_NAME)));
  }

  public get city(): City {
    return this.currentCitySubject.value;
  }

  public setCity(city: City): void {
    localStorage.setItem(this.LC_NAME, JSON.stringify(city));
    this.currentCitySubject.next(city);
  }
}
