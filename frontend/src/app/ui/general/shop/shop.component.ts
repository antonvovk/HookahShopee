import { Component, OnInit, ViewChild } from '@angular/core';
import { CitiesService } from '../../../services/cities.service';
import { City } from '../../../core/model/city.model';
import { Page } from '../../../core/model/page.model';
import { Product } from '../../../core/model/product.model';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { ProductsService } from '../../../services/products.service';
import { FormControl } from '@angular/forms';
import { ManufacturersService } from '../../../services/manufacturers.service';
import { BasketService } from '../../../services/basket.service';
import { CitySaverService } from '../../../services/city-saver.service';
import { ToastrService } from 'ngx-toastr';
import { ImageService } from '../../../services/image.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {

  cities: City[] = [];
  products: Page<Product> = new Page<Product>();
  manufacturers: any[] = [];
  city: City = null;
  priceFrom = new FormControl(0);
  priceTo = new FormControl(10000);
  @ViewChild('productsPaginator', {static: true}) productsPaginator: MatPaginator;
  inStockChecked = new FormControl(false);
  onPromotionChecked = new FormControl(false);

  constructor(private citiesService: CitiesService,
              private productsService: ProductsService,
              private manufacturersService: ManufacturersService,
              private basketService: BasketService,
              private toastrService: ToastrService,
              private imageService: ImageService,
              private citySaverService: CitySaverService) {

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
      }
    );

    this.productsService.getAll(0, 9, null).subscribe(
      products => {
        this.products = products;
      }
    );

    this.manufacturersService.getAll().subscribe(
      manufacturers => {
        manufacturers.forEach(
          manufacturer => {
            this.manufacturers.push({manufacturer: manufacturer, checked: new FormControl(false)});
          }
        );
      }
    );
  }

  productsPaginatorChanged($event: PageEvent) {
    this.updateFilters();
  }

  isInStock(product: Product): boolean {
    if (product.productQuantityForCities.find(x => x.city.uuid === this.city.uuid) == null) {
      return false;
    } else {
      return product.productQuantityForCities.find(x => x.city.uuid === this.city.uuid).quantity > 0;
    }
  }

  updateFilters() {
    let cityUUID = null;
    let onPromotion = null;

    if (this.inStockChecked.value === true) {
      cityUUID = this.city.uuid;
    }

    if (this.onPromotionChecked.value === true) {
      onPromotion = true;
    }

    this.productsService.getAll(
      this.productsPaginator.pageIndex,
      this.productsPaginator.pageSize,
      {
        manufacturers: this.manufacturers.filter(x => x.checked.value === true).map(x => x.manufacturer.uuid),
        startPrice: this.priceFrom.value,
        endPrice: this.priceTo.value,
        cityUUID: cityUUID,
        isOnDiscount: onPromotion
      }
    ).subscribe(
      products => {
        this.products = products;
        if (products.totalElements === 0) {
          this.toastrService.info('Нічого не знайшли', 'Інформейшин');
        }
      }
    );
  }

  addToBasket(product: Product) {
    this.basketService.addItem(product, 1);
  }

  updateCity() {
    this.citySaverService.setCity(this.city);
  }

  compareObjects(o1: any, o2: any): boolean {
    return o1.uuid === o2.uuid;
  }
}
