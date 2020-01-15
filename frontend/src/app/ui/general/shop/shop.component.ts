import {Component, OnInit, ViewChild} from '@angular/core';
import {CitiesService} from "../../../services/cities.service";
import {City} from "../../../core/model/city.model";
import {Page} from "../../../core/model/page.model";
import {Product} from "../../../core/model/product.model";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {ProductsService} from "../../../services/products.service";
import {FormControl} from "@angular/forms";
import {ManufacturersService} from "../../../services/manufacturers.service";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {

  cities: City[] = [];
  products: Page<Product> = new Page<Product>();
  manufacturers: any[] = [];
  city = new FormControl('');
  priceFrom = new FormControl(0);
  priceTo = new FormControl(10000);
  @ViewChild("productsPaginator", {static: true}) productsPaginator: MatPaginator;
  inStockChecked = new FormControl(false);
  onPromotionChecked = new FormControl(false);

  constructor(private citiesService: CitiesService,
              private productsService: ProductsService,
              private manufacturersService: ManufacturersService) {

  }

  ngOnInit() {
    this.citiesService.findAll().subscribe(
      cities => {
        this.cities = cities;
        this.city.setValue(this.cities[0].name);
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
            this.manufacturers.push({manufacturer: manufacturer, checked: false});
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
    return product.productQuantity.find(x => x.city.name == this.city.value) != null;
  }
}
