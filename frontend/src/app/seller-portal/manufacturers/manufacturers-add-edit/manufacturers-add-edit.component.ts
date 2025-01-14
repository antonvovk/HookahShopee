import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Manufacturer } from '../../../_models/manufacturer.model';
import { Page } from '../../../_models/page.model';
import { Product } from '../../../_models/product.model';
import { ManufacturersService } from '../../../_services/manufacturers.service';
import { ProductsService } from '../../../_services/products.service';
import { CitiesService } from '../../../_services/cities.service';
import { ImageService } from '../../../_services/image.service';

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
  products: Page<Product> = new Page<Product>();
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
      this.productsService.getAll(0, 5, {manufacturers: [this.manufacturer.uuid]}).subscribe(
        products => {
          this.products = products;
        }
      );
    }
  }

  onSave() {
    if (this.manufacturer == null) {
      this.manufacturersService.create(new Manufacturer({name: this.name.value})).subscribe(
        response => {
          this.manufacturer = new Manufacturer(this.name.value);
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000, });
        },
        error => {
          this.manufacturer = null;
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000, });
        }
      );
    } else {
      this.manufacturer.name = this.name.value;
      this.manufacturersService.update(this.manufacturer).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000, });
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000, });
        }
      );
    }
  }

  onDelete() {
    this.manufacturersService.delete(this.manufacturer.uuid).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000, });
        this.returned.emit(true);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000, });
      }
    );
  }

  menuOpened(product: Product) {
    this.product = product;
    this.edit_addMenuOpened = true;
  }

  onReturn($event: boolean) {
    this.productsService.getAll(0, 5, {manufacturers: [this.manufacturer.uuid]}).subscribe(
      products => {
        this.products = products;
      }
    );
    this.edit_addMenuOpened = false;
  }

  handleFileInput(files: any) {
    const uploadedFiles = files.target.files;
    this.manufacturersService.updateImage(this.manufacturer.uuid, uploadedFiles[0]).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000, });
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000, });
      }
    );
  }
}
