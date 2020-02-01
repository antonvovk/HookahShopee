import { Component, OnInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { CitiesService } from '../../../_services/cities.service';
import { CitySaverService } from '../../../_services/city-saver.service';
import { ProductsService } from '../../../_services/products.service';
import { PostService } from '../../../_services/post.service';
import { ImageService } from '../../../_services/image.service';
import { Router } from '@angular/router';
import { Product } from '../../../_models/product.model';
import { Page } from '../../../_models/page.model';
import { Post } from '../../../_models/post.model';
import { City } from '../../../_models/city.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  customOptions: OwlOptions = {
    loop: true,
    margin: 10,
    nav: false,
    autoHeight: true,
    startPosition: 0,
    autoplay: true,
    autoplaySpeed: 2000,
    responsive: {
      0: {
        items: 1,
        stagePadding: 10
      },
      600: {
        items: 1,
        stagePadding: 100
      },
      1000: {
        items: 1,
        stagePadding: 210,
      }
    }
  };

  bestValueProducts: Page<Product> = new Page<Product>();
  hitSaleProducts: Page<Product> = new Page<Product>();
  posts: Post[] = [];
  cities: City[] = [];
  city: City = null;

  constructor(private citiesService: CitiesService,
              private citySaverService: CitySaverService,
              private productsService: ProductsService,
              private postService: PostService,
              public imageService: ImageService,
              private router: Router) {

  }

  ngOnInit() {
    this.citiesService.findAll().subscribe(
      cities => {
        this.cities = cities;

        if (this.citySaverService.city != null) {
          this.city = this.citySaverService.city;
        } else {
          this.city = this.cities[0];
          this.citySaverService.setCity(this.cities[0]);
        }

        this.productsService.getAll(0, 9, {cityUUID: this.city.uuid}, 'discount').subscribe(
          products => {
            this.bestValueProducts = products;
          }
        );

        this.productsService.getAll(0, 9, {cityUUID: this.city.uuid}, 'numberOfSales').subscribe(
          products => {
            this.hitSaleProducts = products;
          }
        );
      }
    );

    this.postService.getAllLight().subscribe(
      posts => {
        this.posts = posts;
      }
    );
  }

  updateCity() {
    this.citySaverService.setCity(this.city);

    this.productsService.getAll(0, 9, {cityUUID: this.city.uuid}, 'discount').subscribe(
      products => {
        this.bestValueProducts = products;
      }
    );

    this.productsService.getAll(0, 9, {cityUUID: this.city.uuid}, 'numberOfSales').subscribe(
      products => {
        this.hitSaleProducts = products;
      }
    );
  }

  compareObjects(o1: any, o2: any): boolean {
    return o1.uuid === o2.uuid;
  }
}
