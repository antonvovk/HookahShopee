import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {User} from "../model/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserAdapter implements Adapter<User> {

  adapt(item: any): User {
    return new User(
      item.phoneNumber,
      item.firstName,
      item.lastName,
      item.role,
      item.city
    );
  }
}
