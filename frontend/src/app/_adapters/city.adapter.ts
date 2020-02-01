import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { City } from '../_models/city.model';

@Injectable({
  providedIn: 'root'
})
export class CityAdapter implements Adapter<City> {

  adapt(item: any): City {
    return new City({
        uuid: item.uuid,
        name: item.name
      }
    );
  }
}
