import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Manufacturer} from "../../../../../core/model/manufacturer.model";
import {ManufacturersService} from "../../../../../services/manufacturers.service";
import {FormControl} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Product} from "../../../../../core/model/product.model";
import {ProductsService} from "../../../../../services/products.service";
import {City} from "../../../../../core/model/city.model";
import {CitiesService} from "../../../../../services/cities.service";

@Component({
  selector: 'app-manufacturers-add-edit',
  templateUrl: './manufacturers-add-edit.component.html',
  styleUrls: ['./manufacturers-add-edit.component.scss']
})
export class ManufacturersAddEditComponent implements OnInit {

  @Input()
  manufacturer: Manufacturer;

  @Output()
  returned = new EventEmitter<boolean>();

  edit_addMenuOpened: boolean = false;
  name = new FormControl('');
  products: Product[] = [];
  cities: City[] = [];
  quantities: any[] = [];
  product: Product = null;

  constructor(private manufacturersService: ManufacturersService,
              private productsService: ProductsService,
              private citiesService: CitiesService,
              private snackBar: MatSnackBar) {
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  ngOnInit() {
    if (this.manufacturer != null) {
      this.name.setValue(this.manufacturer.name);
      this.productsService.findAllByManufacturer(this.manufacturer.name).subscribe(
        products => {
          this.products = products;

          this.citiesService.findAll().subscribe(
            cities => {
              this.cities = cities;

              for (let i = 0; i < this.products.length; ++i) {
                for (let j = 0; j < this.cities.length; ++j) {
                  this.productsService.getQuantityByCity(this.products[i].name, this.cities[j].name).subscribe(
                    quantity => {
                      this.quantities.push({
                        productName: this.products[i].name,
                        cityName: this.cities[j].name,
                        quantity: quantity
                      });
                    }
                  );
                }
              }
            }
          );
        }
      );
    }
  }

  onSave() {
    if (this.manufacturer == null) {
      this.manufacturer = new Manufacturer(this.name.value);
      this.manufacturersService.insert(this.manufacturer).subscribe(res => {
        this.openSnackBar("Успішно додано виробника: " + this.manufacturer.name, "Закрити");
      });
    } else {
      const oldName = this.manufacturer.name;
      this.manufacturer.name = this.name.value;
      this.manufacturersService.update(this.manufacturer, oldName).subscribe(res => {
        this.openSnackBar("Успішно оновлено виробника: " + this.manufacturer.name, "Закрити");
      });
    }
  }

  onDelete() {
    this.manufacturersService.delete(this.manufacturer.name).subscribe(res => {
        this.openSnackBar("Успішно видалено виробника: " + this.manufacturer.name, "Закрити");
        this.returned.emit(true);
      },
      err => {
        this.openSnackBar(err, "Закрити");
      });
  }

  getByProductName(productName: string) {
    return this.quantities.filter(quantity => quantity.productName === productName);
  }

  menuOpened(product: Product) {
    this.product = product;
    this.edit_addMenuOpened = true;
  }

  onReturn($event: boolean) {
    this.quantities = [];
    this.productsService.findAllByManufacturer(this.manufacturer.name).subscribe(
      products => {
        this.products = products;

        this.citiesService.findAll().subscribe(
          cities => {
            this.cities = cities;

            for (let i = 0; i < this.products.length; ++i) {
              for (let j = 0; j < this.cities.length; ++j) {
                this.productsService.getQuantityByCity(this.products[i].name, this.cities[j].name).subscribe(
                  quantity => {
                    this.quantities.push({
                      productName: this.products[i].name,
                      cityName: this.cities[j].name,
                      quantity: quantity
                    });
                  }
                );
              }
            }
          }
        );
      }
    );
    this.edit_addMenuOpened = false;
  }
}
