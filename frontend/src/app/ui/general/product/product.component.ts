import { Component, OnInit } from '@angular/core';
import { Product } from '../../../core/model/product.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ImageService } from '../../../services/image.service';
import { ToastrService } from 'ngx-toastr';
import { ProductsService } from '../../../services/products.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  product: Product;

  constructor(private activatedRoute: ActivatedRoute,
              private productService: ProductsService,
              private imageService: ImageService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit() {
  }
}
