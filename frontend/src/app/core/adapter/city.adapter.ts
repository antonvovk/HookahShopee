import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {City} from "../model/city.model";

@Injectable({
  providedIn: 'root'
})
export class CityAdapter implements Adapter<City> {

  adapt(item: any): City {
    return new City(
      item.name
    );
  }
}
