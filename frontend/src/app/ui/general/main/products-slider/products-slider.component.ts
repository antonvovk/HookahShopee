import { Component, Input, OnInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { Page } from '../../../../core/model/page.model';
import { Product } from '../../../../core/model/product.model';
import { ImageService } from '../../../../services/image.service';

@Component({
  selector: 'app-products-slider',
  templateUrl: './products-slider.component.html',
  styleUrls: ['./products-slider.component.scss']
})
export class ProductsSliderComponent implements OnInit {

  @Input()
  products: Page<Product> = new Page<Product>();

  @Input()
  header: string = '';

  @Input()
  cityUUID: string = '';

  customOptions: OwlOptions = {
    loop: true,
    margin: 50,
    nav: false,
    autoHeight: true,
    startPosition: 0,
    dots: false,
    responsive: {
      0: {
        items: 1,
        center: true
      },
      600: {
        items: 2,
      },
      1000: {
        items: 3,
      }
    }
  };

  constructor(public imageService: ImageService) {

  }

  ngOnInit() {

  }

  isInStock(product: Product): boolean {
    if (product.productQuantityForCities.find(x => x.city.uuid === this.cityUUID) == null) {
      return false;
    } else {
      return product.productQuantityForCities.find(x => x.city.uuid === this.cityUUID).quantity > 0;
    }
  }
}
