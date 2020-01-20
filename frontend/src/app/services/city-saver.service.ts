import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {ProductReservationService} from "./product-reservation.service";

@Injectable({providedIn: 'root'})
export class CitySaverService {

  private currentCitySubject: BehaviorSubject<string>;

  constructor(private productReservationService: ProductReservationService) {
    this.currentCitySubject = new BehaviorSubject<string>(JSON.parse(localStorage.getItem('currentCity')));

    if (this.currentCitySubject.value == null) {
      this.currentCitySubject = new BehaviorSubject<string>(null);
    }
  }

  public getCityName(): string {
    return this.currentCitySubject.value;
  }

  public setCity(cityName: string) {
    localStorage.setItem('currentCity', JSON.stringify(cityName));
    this.currentCitySubject.next(cityName);
  }

  public deleteCity(): void {
    localStorage.removeItem('currentCity');
    this.currentCitySubject.next(null);
  }
}
