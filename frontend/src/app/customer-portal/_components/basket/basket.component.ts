import { Component, OnInit } from '@angular/core';
import { BasketService } from '../../../_services/basket.service';
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
  items: OrderItem[] = [];

  constructor(public basketService: BasketService,
              private authenticationService: AuthenticationService,
              private toastrService: ToastrService) {
  }

  ngOnInit() {
    this.items = this.basketService.getItems();
  }

  openConfirmation() {
    if (!this.authenticationService.isAuthenticated()) {
      this.toastrService.info('Увійдіть для того щоб оформити замовлення', 'Інформейшин');
      return;
    }

    this.showConfirmation = true;
  }

  clearItems() {
    this.basketService.clearItems().subscribe(
      () => {
        this.toastrService.info('Корзина ощишена', 'Інфо');
      },
      () => {
        this.toastrService.error('Деталі в консолі', 'Помилка');
      }
    );
  }

  increaseQuantity(item: OrderItem) {
    this.basketService.addItem(item.product, 1).subscribe(
      () => {
        this.toastrService.info('Кількість збільшена', 'Інфо');
      },
      () => {
        this.toastrService.error('Деталі в консолі', 'Помилка');
      }
    );
  }

  decreaseQuantity(item: OrderItem) {
    if (item.quantity - 1 < 1) {
      this.toastrService.info('Досятнуто мінімальної кількості', 'Інформейшин');
      return;
    }

    this.basketService.removeItem(item.product, 1).subscribe(
      () => {
        this.toastrService.info('Кількість зменшена', 'Інфо');
      },
      () => {
        this.toastrService.error('Деталі в консолі', 'Помилка');
      }
    );
  }

  deleteItems(item: OrderItem) {
    this.basketService.removeItem(item.product, item.quantity).subscribe(
      items => {
        this.items = items;
        this.toastrService.info('Продукт видалено з корзини', 'Інфо');
      },
      () => {
        this.toastrService.error('Деталі в консолі', 'Помилка');
      }
    );
  }
}
