import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Manufacturer} from "../../../../../core/model/manufacturer.model";
import {ManufacturersService} from "../../../../../services/manufacturers.service";
import {FormControl} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Product} from "../../../../../core/model/product.model";
import {ProductsService} from "../../../../../services/products.service";
import {CitiesService} from "../../../../../services/cities.service";
import {ImageService} from "../../../../../services/image.service";

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
  quantities: any[] = [];
  product: Product = new Product();

  constructor(private manufacturersService: ManufacturersService,
              private productsService: ProductsService,
              private citiesService: CitiesService,
              private imageService: ImageService,
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
          this.products.forEach(product => {
            this.productsService.getQuantitiesByCities(product.name).subscribe(
              productQuantities => {
                this.quantities.push(
                  {
                    productName: product.name,
                    quantity: productQuantities
                  }
                );
              });
          });
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
    return this.quantities.find(quantity => quantity.productName === productName);
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
        this.products.forEach(product => {
          this.productsService.getQuantitiesByCities(product.name).subscribe(
            productQuantities => {
              this.quantities.push(
                {
                  productName: product.name,
                  quantity: productQuantities
                }
              );
            });
        });
      }
    );
    this.edit_addMenuOpened = false;
  }

  handleFileInput(files: any) {
    const uploadedFiles = files.target.files;
    this.manufacturersService.updateImage(this.manufacturer.name, uploadedFiles[0]).subscribe(
      // data => {
      //   this.manufacturersService.findByName(this.manufacturer.name).subscribe(
      //     manufacturer => {
      //       this.manufacturer = manufacturer;
      //     }
      //   );
      // }
    );
  }
}
