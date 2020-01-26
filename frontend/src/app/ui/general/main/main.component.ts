import { Component, OnInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { City } from '../../../core/model/city.model';
import { CitiesService } from '../../../services/cities.service';
import { CitySaverService } from '../../../services/city-saver.service';
import { Page } from '../../../core/model/page.model';
import { Product } from '../../../core/model/product.model';
import { ProductsService } from '../../../services/products.service';
import { Post } from '../../../core/model/post.model';
import { PostService } from '../../../services/post.service';
import { ImageService } from '../../../services/image.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

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
              private imageService: ImageService,
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

        this.productsService.findAll(0, 9, null, null, null, this.city.name, null, 'discount').subscribe(
          products => {
            this.bestValueProducts = products;
          }
        );

        this.productsService.findAll(0, 9, null, null, null, this.city.name, null, 'numberOfSales').subscribe(
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

    this.productsService.findAll(0, 9, null, null, null, this.city.name, null, 'discount').subscribe(
      products => {
        this.bestValueProducts = products;
      }
    );

    this.productsService.findAll(0, 9, null, null, null, this.city.name, null, 'numberOfSales').subscribe(
      products => {
        this.hitSaleProducts = products;
      }
    );
  }

  compareObjects(o1: any, o2: any): boolean {
    return o1.uuid === o2.uuid;
  }
}
