import {Role} from "./role.model.enum";
import {City} from "./city.model";

export class User {

  constructor(phoneNumber: string, firstName: string, lastName: string, role: Role, city: City) {
    this._phoneNumber = phoneNumber;
    this._firstName = firstName;
    this._lastName = lastName;
    this._role = role;
    this._city = city;
  }

  private _phoneNumber: string;

  get phoneNumber(): string {
    return this._phoneNumber;
  }

  set phoneNumber(value: string) {
    this._phoneNumber = value;
  }

  private _firstName: string;

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  private _lastName: string;

  get lastName(): string {
    return this._lastName;
  }

  set lastName(value: string) {
    this._lastName = value;
  }

  private _role: Role;

  get role(): Role {
    return this._role;
  }

  set role(value: Role) {
    this._role = value;
  }

  private _city: City;

  get city(): City {
    return this._city;
  }

  set city(value: City) {
    this._city = value;
  }
}
