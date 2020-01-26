import { Component, OnInit } from '@angular/core';
import { BasketService } from '../../../services/basket.service';
import { CitySaverService } from '../../../services/city-saver.service';
import { OrderItem } from '../../../core/model/order-item.model';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.scss']
})
export class BasketComponent implements OnInit {

  showConfirmation: boolean = false;

  constructor(public basketService: BasketService,
              private citySaverService: CitySaverService) {
  }

  ngOnInit() {
    console.log(this.basketService.getTotalPrice());
  }

  clearItems() {
    this.basketService.clearItems(this.citySaverService.city.name);
  }

  openConfirmation() {
    this.showConfirmation = true;
  }

  increaseQuantity(item: OrderItem) {
    this.basketService.addItem(item.product, 1);
  }

  decreaseQuantity(item: OrderItem) {
    this.basketService.removeItem(item.product, 1);
  }
}
