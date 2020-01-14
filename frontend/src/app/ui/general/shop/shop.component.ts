import {Component, OnInit, ViewChild} from '@angular/core';
import {CitiesService} from "../../../services/cities.service";
import {City} from "../../../core/model/city.model";
import {Page} from "../../../core/model/page.model";
import {Product} from "../../../core/model/product.model";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {ProductsService} from "../../../services/products.service";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {

  cities: City[] = [];
  products: Page<Product> = new Page<Product>();
  @ViewChild("productsPaginator", {static: true}) productsPaginator: MatPaginator;

  constructor(private citiesService: CitiesService,
              private productsService: ProductsService) {

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
  }

  productsPaginatorChanged($event: PageEvent) {
    this.productsService.findAll($event.pageIndex, $event.pageSize, null).subscribe(
      products => {
        this.products = products;
      }
    );
  }
}
