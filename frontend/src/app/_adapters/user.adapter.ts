import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { User } from '../_models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserAdapter implements Adapter<User> {

  adapt(item: any): User {
    return new User({
        uuid: item.uuid,
        phoneNumber: item.phoneNumber,
        firstName: item.firstName,
        lastName: item.lastName,
        role: item.role,
        city: item.city
      }
    );
  }
}
