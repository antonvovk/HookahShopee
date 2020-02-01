import { Injectable } from '@angular/core';
import { Adapter } from './adapter';
import { Post } from '../_models/post.model';

@Injectable({
  providedIn: 'root'
})
export class PostAdapter implements Adapter<Post> {

  adapt(item: any): Post {
    return new Post({
      uuid: item.uuid,
      name: item.name,
      imageName: item.imageName,
      htmlContent: item.htmlContent
    });
  }
}
