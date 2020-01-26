import { Component, EventEmitter, Input, OnInit, Output, QueryList, ViewChildren } from '@angular/core';
import { Product } from '../../../../../../core/model/product.model';
import { ProductQuantityBySellers } from '../../../../../../core/model/product-quantity-by-sellers.model';
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
  productQuantities: ProductQuantityBySellers[] = [];
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
      this.description.setValue(this.product.description);

      this.productsService.getQuantitiesBySellers(this.product.name).subscribe(
        productQuantities => {
          this.productQuantities = productQuantities;
        }
      );
    }
  }

  saveProduct() {
    if (this.product != null) {
      const oldName = this.product.name;
      this.product.name = this.name.value;
      this.product.price = this.price.value;
      this.product.discount = this.discount.value;
      this.product.description = this.description.value;
      this.productsService.update(oldName, this.product).subscribe(
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
      this.product.description = this.description.value;
      this.product.manufacturer = this.manufacturer;
      this.productsService.insert(this.product).subscribe(
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
    this.productsService.updateImage(this.product.name, uploadedFiles[0]).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  updateQuantity(seller: User, i: number) {
    this.productsService.updateQuantity(this.product.name, seller.phoneNumber, (this.quantities.toArray())[i].nativeElement.value).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  deleteProduct() {
    this.productsService.delete(this.product.name).subscribe(
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
