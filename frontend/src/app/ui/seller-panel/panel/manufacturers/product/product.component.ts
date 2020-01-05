import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from "../../../../../core/model/product.model";
import {ProductQuantity} from "../../../../../core/model/product-quantity.model";
import {ProductsService} from "../../../../../services/products.service";

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

  productQuantities: ProductQuantity[] = [];

  constructor(private productsService: ProductsService) {
  }

  ngOnInit() {
    if (this.product != null) {
      this.productsService.getQuantities(this.product.name).subscribe(
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
}
