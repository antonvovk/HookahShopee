import { Component, OnInit } from '@angular/core';
import { BasketService } from '../../../_services/basket.service';
import { CitySaverService } from '../../../_services/city-saver.service';
import { AuthenticationService } from '../../../_services/authentication.service';
import { ToastrService } from 'ngx-toastr';
import { OrderItem } from '../../../_models/order-item.model';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.scss']
})
export class BasketComponent implements OnInit {

  showConfirmation: boolean = false;

  constructor(public basketService: BasketService,
              private authenticationService: AuthenticationService,
              private toastrService: ToastrService,
              private citySaverService: CitySaverService) {
  }

  ngOnInit() {

  }

  clearItems() {
    this.basketService.clearItems();
  }

  openConfirmation() {
    if (!this.authenticationService.isAuthenticated()) {
      this.toastrService.info('Увійдіть для того щоб оформити замовлення', 'Інформейшин');
      return;
    }

    this.showConfirmation = true;
  }

  increaseQuantity(item: OrderItem) {
    this.basketService.addItem(item.product, 1);
  }

  decreaseQuantity(item: OrderItem) {
    if (item.quantity - 1 < 0) {
      this.toastrService.info('Менше не ножна лол', 'Інформейшин');
      return;
    }

    this.basketService.removeItem(item.product, 1);
  }
}
