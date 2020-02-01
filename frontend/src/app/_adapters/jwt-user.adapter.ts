import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {JwtUser} from "../_models/jwt-user.model";

@Injectable({
  providedIn: 'root'
})
export class JwtUserAdapter implements Adapter<JwtUser> {

  adapt(item: any): JwtUser {
    return new JwtUser(
      item.token,
      item.user
    );
  }
}
