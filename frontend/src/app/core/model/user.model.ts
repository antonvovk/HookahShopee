import { Role } from './role.model.enum';
import { City } from './city.model';

export class User {

  uuid?: string;
  phoneNumber?: string;
  firstName?: string;
  lastName?: string;
  role?: Role;
  city?: City;

  constructor(params: User = {} as User) {
    let {
      uuid,
      phoneNumber,
      firstName,
      lastName,
      role,
      city
    } = params;

    this.uuid = uuid;
    this.phoneNumber = phoneNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.city = city;
  }
}
