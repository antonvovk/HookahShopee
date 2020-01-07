import {Injectable} from "@angular/core";
import {Adapter} from "./adapter";
import {Post} from "../model/post.model";

@Injectable({
  providedIn: 'root'
})
export class PostAdapter implements Adapter<Post> {

  adapt(item: any): Post {
    return new Post(
      item.name,
      item.imageName,
      item.htmlContent
    );
  }
}
