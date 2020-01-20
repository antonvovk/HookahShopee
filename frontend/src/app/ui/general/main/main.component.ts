import {Component, OnInit} from '@angular/core';
import {OwlOptions} from "ngx-owl-carousel-o";
import {City} from "../../../core/model/city.model";
import {FormControl} from "@angular/forms";
import {CitiesService} from "../../../services/cities.service";
import {CitySaverService} from "../../../services/city-saver.service";
import {Page} from "../../../core/model/page.model";
import {Product} from "../../../core/model/product.model";
import {ProductsService} from "../../../services/products.service";

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
  cities: City[] = [];
  city = new FormControl('');

  constructor(private citiesService: CitiesService,
              private citySaverService: CitySaverService,
              private productsService: ProductsService) {

  }

  ngOnInit() {
    this.citiesService.findAll().subscribe(
      cities => {
        this.cities = cities;
        if (this.citySaverService.getCityName() != null) {
          this.city.setValue(this.citySaverService.getCityName());
        } else {
          this.city.setValue(this.cities[0].name);
        }

        this.productsService.findAll(0, 9, null, null, null, this.city.value, null, "discount").subscribe(
          products => {
            this.bestValueProducts = products;
          }
        );

        this.productsService.findAll(0, 9, null, null, null, this.city.value, null, "numberOfSales").subscribe(
          products => {
            this.hitSaleProducts = products;
          }
        );
      }
    );
  }

  updateCity() {
    this.citySaverService.setCity(this.city.value);

    this.productsService.findAll(0, 9, null, null, null, this.city.value, null, "discount").subscribe(
      products => {
        this.bestValueProducts = products;
      }
    );

    this.productsService.findAll(0, 9, null, null, null, this.city.value, null, "numberOfSales").subscribe(
      products => {
        this.hitSaleProducts = products;
      }
    );
  }
}
