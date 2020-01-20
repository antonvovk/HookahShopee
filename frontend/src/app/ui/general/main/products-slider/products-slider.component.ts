import {Component, Input, OnInit} from '@angular/core';
import {OwlOptions} from "ngx-owl-carousel-o";
import {Page} from "../../../../core/model/page.model";
import {Product} from "../../../../core/model/product.model";

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
  city: string = '';

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

  constructor() {

  }

  ngOnInit() {

  }

  isInStock(product: Product): boolean {
    if (product.productQuantity.find(x => x.city.name === this.city) == null) {
      return false;
    } else {
      return product.productQuantity.find(x => x.city.name === this.city).quantity > 0;
    }
  }
}
