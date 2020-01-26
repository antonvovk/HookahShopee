import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { Manufacturer } from '../model/manufacturer.model';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerAdapter implements Adapter<Manufacturer> {

  adapt(item: any): Manufacturer {
    return new Manufacturer({
      uuid: item.uuid,
      name: item.name,
      imageName: item.imageName
    });
  }
}
