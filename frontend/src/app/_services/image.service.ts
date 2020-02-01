import { Injectable } from '@angular/core';
import { API_URL } from '../globals';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor() {

  }

  getImage(name: string): string {
    return API_URL + '/img/' + name;
  }
}
