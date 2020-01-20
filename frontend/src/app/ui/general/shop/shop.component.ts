import {Component, OnInit, ViewChild} from '@angular/core';
import {CitiesService} from "../../../services/cities.service";
import {City} from "../../../core/model/city.model";
import {Page} from "../../../core/model/page.model";
import {Product} from "../../../core/model/product.model";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {ProductsService} from "../../../services/products.service";
import {FormControl} from "@angular/forms";
import {ManufacturersService} from "../../../services/manufacturers.service";
import {BasketService} from "../../../services/basket.service";
import {OrderItem} from "../../../core/model/order-item.model";
import {CitySaverService} from "../../../services/city-saver.service";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {

  cities: City[] = [];
  products: Page<Product> = new Page<Product>();
  manufacturers: any[] = [];
  city = new FormControl('Львів');
  priceFrom = new FormControl(0);
  priceTo = new FormControl(10000);
  @ViewChild("productsPaginator", {static: true}) productsPaginator: MatPaginator;
  inStockChecked = new FormControl(false);
  onPromotionChecked = new FormControl(false);

  constructor(private citiesService: CitiesService,
              private productsService: ProductsService,
              private manufacturersService: ManufacturersService,
              private basketService: BasketService,
              private citySaverService: CitySaverService) {

  }

  ngOnInit() {
    this.citiesService.findAll().subscribe(
      cities => {
        this.cities = cities;
      }
    );

    this.productsService.findAll(0, 9, null).subscribe(
      products => {
        this.products = products;
      }
    );

    this.manufacturersService.findAll().subscribe(
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
    this.productsService.findAll($event.pageIndex, $event.pageSize, null).subscribe(
      products => {
        this.products = products;
      }
    );
  }

  isInStock(product: Product): boolean {
    if (product.productQuantity.find(x => x.city.name === this.city.value) == null) {
      return false;
    } else {
      return product.productQuantity.find(x => x.city.name === this.city.value).quantity > 0;
    }
  }

  updateFilters() {
    let cityName = null;
    let onPromotion = null;

    if (this.inStockChecked.value === true) {
      cityName = this.city.value;
    }

    if (this.onPromotionChecked.value == true) {
      onPromotion = true;
    }

    this.productsService.findAll(
      this.productsPaginator.pageIndex,
      this.productsPaginator.pageSize,
      this.manufacturers.filter(x => x.checked.value === true).map(x => x.manufacturer.name),
      this.priceFrom.value,
      this.priceTo.value,
      cityName,
      onPromotion
    ).subscribe(
      products => {
        this.products = products;
      }
    );
  }

  addToBasket(product: Product) {
    this.basketService.addItem(new OrderItem(product.price, 1, product), this.city.value);
  }

  updateCity() {
    this.citySaverService.setCity(this.city.value);
  }
}
