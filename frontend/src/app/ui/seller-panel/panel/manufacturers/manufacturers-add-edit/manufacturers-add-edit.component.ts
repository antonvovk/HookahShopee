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
  product: Product = new Product();

  constructor(private manufacturersService: ManufacturersService,
              private productsService: ProductsService,
              private citiesService: CitiesService,
              public imageService: ImageService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    if (this.manufacturer != null) {
      this.name.setValue(this.manufacturer.name);
      this.productsService.findAllByManufacturer(this.manufacturer.name).subscribe(
        products => {
          this.products = products;
        }
      );
    }
  }

  onSave() {
    if (this.manufacturer == null) {
      this.manufacturer = new Manufacturer(this.name.value);
      this.manufacturersService.insert(this.manufacturer).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
        }
      );
    } else {
      const oldName = this.manufacturer.name;
      this.manufacturer.name = this.name.value;
      this.manufacturersService.update(this.manufacturer, oldName).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
        }
      );
    }
  }

  onDelete() {
    this.manufacturersService.delete(this.manufacturer.name).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        this.returned.emit(true);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  menuOpened(product: Product) {
    this.product = product;
    this.edit_addMenuOpened = true;
  }

  onReturn($event: boolean) {
    this.productsService.findAllByManufacturer(this.manufacturer.name).subscribe(
      products => {
        this.products = products;
      }
    );
    this.edit_addMenuOpened = false;
  }

  handleFileInput(files: any) {
    const uploadedFiles = files.target.files;
    this.manufacturersService.updateImage(this.manufacturer.name, uploadedFiles[0]).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }
}
