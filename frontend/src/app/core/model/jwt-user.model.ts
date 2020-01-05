import {User} from "./user.model";

export class JwtUser {

  constructor(token: string, user: User) {
    this._token = token;
    this._user = user;
  }

  private _token: string;

  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
  }

  private _user: User;

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }
}
