import { Component, OnInit } from '@angular/core';
import { BasketService } from '../../../services/basket.service';
import { CitySaverService } from '../../../services/city-saver.service';
import { OrderItem } from '../../../core/model/order-item.model';
import { AuthenticationService } from '../../../services/authentication.service';
import { ToastrService } from 'ngx-toastr';

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
    this.basketService.clearItems(this.citySaverService.city.name);
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
    this.basketService.removeItem(item.product, 1);
  }
}
