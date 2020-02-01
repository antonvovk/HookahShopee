import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { POST_API_URL } from '../globals';
import { PostAdapter } from '../_adapters/post.adapter';
import { Post } from '../_models/post.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient,
              private adapter: PostAdapter) {
  }

  getAll(): Observable<Post[]> {
    return this.http
      .get<Post[]>(POST_API_URL + '/all')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  getAllLight(): Observable<Post[]> {
    return this.http
      .get<Post[]>(POST_API_URL + '/all/light')
      .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
  }

  findByUUID(uuid: string): Observable<Post> {
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.get<Post>(
      POST_API_URL + '/uuid',
      {params: params}
    ).pipe(map((data: any) => this.adapter.adapt(data)));
  }

  create(post: Post): Observable<HttpResponse<string>> {
    return this.http.post<string>(
      POST_API_URL + '/create',
      post,
      {observe: 'response'}
    );
  }

  update(post: Post): Observable<HttpResponse<any>> {
    return this.http.put<any>(
      POST_API_URL + '/update',
      post,
      {observe: 'response'}
    );
  }

  updateImage(uuid: string, image: File): Observable<HttpResponse<any>> {
    const formData = new FormData();
    formData.append('file', image);
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.put<any>(
      POST_API_URL + '/updateImage',
      formData,
      {observe: 'response', params: params}
    );
  }

  delete(uuid: string): Observable<HttpResponse<any>> {
    let params = new HttpParams();
    params = params.append('uuid', uuid);

    return this.http.delete<any>(
      POST_API_URL + '/delete',
      {observe: 'response', params: params}
    );
  }
}
