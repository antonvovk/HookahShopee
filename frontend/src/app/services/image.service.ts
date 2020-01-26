import { Injectable } from '@angular/core';
import { ApiConfig } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor() {

  }

  getImage(name: string): string {
    return ApiConfig.API_URL + '/img/' + name;
  }
}
