import {Injectable} from "@angular/core";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {PostAdapter} from "../core/adapter/post.adapter";
import {Observable} from "rxjs";
import {Post} from "../core/model/post.model";
import {ApiConfig} from "../config/api.config";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient, private adapter: PostAdapter) {

  }

  findAll(): Observable<Post[]> {
    return this.http
      .get<Post[]>(ApiConfig.apiUrl + '/post')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  findByName(name: string): Observable<Post> {
    return this.http
      .get<Post>(ApiConfig.apiUrl + '/post/' + name)
      .pipe(map((data: any) => data.map(item => this.adapter.adapt(item))));
  }

  insert(post: Post): Observable<HttpResponse<string>> {
    return this.http
      .post<string>(ApiConfig.apiUrl + '/post', post, {observe: 'response'});
  }

  update(name: string, post: Post): Observable<HttpResponse<string>> {
    return this.http
      .put<string>(ApiConfig.apiUrl + '/post/' + name, post, {observe: 'response'});
  }

  updateImage(name: string, image: File): Observable<HttpResponse<string>> {
    const formData = new FormData();
    formData.append('file', image);
    return this.http
      .put<string>(ApiConfig.apiUrl + '/post/' + name + '/updateImage', formData, {observe: 'response'});
  }

  delete(name: string): Observable<HttpResponse<string>> {
    return this.http
      .delete<string>(ApiConfig.apiUrl + '/post/' + name, {observe: 'response'});
  }
}
