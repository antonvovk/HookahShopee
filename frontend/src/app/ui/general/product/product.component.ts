import { Component, OnInit } from '@angular/core';
import { Product } from '../../../core/model/product.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ImageService } from '../../../services/image.service';
import { ToastrService } from 'ngx-toastr';
import { ProductsService } from '../../../services/products.service';
import { CitySaverService } from '../../../services/city-saver.service';
import { BasketService } from '../../../services/basket.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  product: Product;
  quantity: number = 1;

  constructor(private activatedRoute: ActivatedRoute,
              private productService: ProductsService,
              public imageService: ImageService,
              private toastrService: ToastrService,
              private basketService: BasketService,
              private citySaverService: CitySaverService,
              private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
        this.productService.findByUUID(params.get('uuid')).subscribe(
          product => {
            this.product = product;
          },
          error => {
            this.toastrService.error('Не вийшло завантажити продукт' + error, 'Помилка');
            this.router.navigate(['']);
          }
        );
      }
    );
  }

  isInStock(product: Product): boolean {
    if (product.productQuantityForCities.find(x => x.city.uuid === this.citySaverService.city.uuid) == null) {
      return false;
    } else {
      return product.productQuantityForCities.find(x => x.city.uuid === this.citySaverService.city.uuid).quantity > 0;
    }
  }

  getQuantity(): number {
    return this.product.productQuantityForCities.find(x => x.city.uuid === this.citySaverService.city.uuid).quantity;
  }

  increaseQuantity() {
    if (this.quantity + 1 <= this.getQuantity()) {
      this.quantity += 1;
    } else {
      this.toastrService.info('Більше немає', 'Інформейшн');
    }
  }

  decreaseQuantity() {
    if (this.quantity - 1 >= 1) {
      this.quantity -= 1;
    } else {
      this.toastrService.info('Менше не можна', 'Інформейшн');
    }
  }

  addToBasket() {
    this.basketService.addItem(this.product, this.quantity);
  }
}
