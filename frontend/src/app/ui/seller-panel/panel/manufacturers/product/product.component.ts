import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from "../../../../../core/model/product.model";
import {ProductQuantityBySellers} from "../../../../../core/model/product-quantity-by-sellers.model";
import {ProductsService} from "../../../../../services/products.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  @Input()
  product: Product = null;

  @Output()
  returned = new EventEmitter<boolean>();

  productQuantities: ProductQuantityBySellers[] = [];

  name = new FormControl('');
  price = new FormControl('');
  discount = new FormControl('');
  description = new FormControl('');

  editorStyle = {
    height: '300px'
  };

  constructor(private productsService: ProductsService) {
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

  goBack() {
    this.returned.emit(true);
  }

  onSave() {

  }

  onDelete() {

  }

  onUpdateDescription() {
    this.product.description = this.description.value;
    this.productsService.update(this.product.name, this.product).subscribe(
      response => {

      }
    );
  }
}
