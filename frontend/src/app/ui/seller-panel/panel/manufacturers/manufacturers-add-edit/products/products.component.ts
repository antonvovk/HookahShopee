import { Component, EventEmitter, Input, OnInit, Output, QueryList, ViewChildren } from '@angular/core';
import { Product } from '../../../../../../core/model/product.model';
import { ProductsService } from '../../../../../../services/products.service';
import { FormControl } from '@angular/forms';
import { User } from '../../../../../../core/model/user.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ImageService } from '../../../../../../services/image.service';
import { Manufacturer } from '../../../../../../core/model/manufacturer.model';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  @Input()
  product: Product = null;
  @Input()
  manufacturer: Manufacturer = null;
  @Output()
  returned = new EventEmitter<boolean>();
  name = new FormControl('');
  price = new FormControl('');
  discount = new FormControl('');
  description = new FormControl('');
  @ViewChildren('quantities') quantities: QueryList<any>;

  constructor(private productsService: ProductsService,
              private snackBar: MatSnackBar,
              public imageService: ImageService) {

  }

  ngOnInit() {
    if (this.product != null) {
      this.name.setValue(this.product.name);
      this.price.setValue(this.product.price);
      this.discount.setValue(this.product.discount);
      this.description.setValue(this.product.htmlContent);
    }
  }

  saveProduct() {
    if (this.product != null) {
      this.product.name = this.name.value;
      this.product.price = this.price.value;
      this.product.discount = this.discount.value;
      this.product.htmlContent = this.description.value;
      this.product.manufacturerUUID = this.manufacturer.uuid;
      this.productsService.update(this.product).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
        }
      );
    } else {
      this.product = new Product();
      this.product.name = this.name.value;
      this.product.price = this.price.value;
      this.product.discount = this.discount.value;
      this.product.htmlContent = this.description.value;
      this.product.manufacturerUUID = this.manufacturer.uuid;
      this.productsService.create(this.product).subscribe(
        response => {
          this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        },
        error => {
          this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
          this.product = null;
        }
      );
    }
  }

  updateImage(files: any) {
    const uploadedFiles = files.target.files;
    this.productsService.updateImage(this.product.uuid, uploadedFiles[0]).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  updateQuantity(seller: User, i: number) {
    this.productsService.updateQuantity(this.product.uuid, seller.uuid, (this.quantities.toArray())[i].nativeElement.value).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  deleteProduct() {
    this.productsService.delete(this.product.uuid).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        this.returned.emit(true);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }
}
