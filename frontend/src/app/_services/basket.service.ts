import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ProductReservationService } from './product-reservation.service';
import { CitySaverService } from './city-saver.service';
import { ToastrService } from 'ngx-toastr';
import { Basket } from '../_models/basket.model';
import { OrderItem } from '../_models/order-item.model';
import { Product } from '../_models/product.model';

@Injectable({providedIn: 'root'})
export class BasketService {

  private currentBasketSubject: BehaviorSubject<Basket>;

  constructor(private productReservationService: ProductReservationService,
              private toastrService: ToastrService,
              private citySaverService: CitySaverService) {
    this.currentBasketSubject = new BehaviorSubject<Basket>(JSON.parse(localStorage.getItem('currentBasket')));

    if (this.currentBasketSubject.value == null) {
      this.currentBasketSubject = new BehaviorSubject<Basket>(new Basket([]));
    }
  }

  public getItems(): OrderItem[] {
    return this.currentBasketSubject.value.items;
  }

  public getCount(): number {
    let sum = 0;
    this.currentBasketSubject.value.items.forEach(
      item => {
        sum += item.quantity;
      }
    );
    return sum;
  }

  public isEmpty(): boolean {
    return this.currentBasketSubject.value.items.length === 0;
  }

  public getTotalPrice(): number {
    let sum: number = 0;
    this.currentBasketSubject.value.items.forEach(value => {
      sum += value.price * value.quantity;
    });
    return sum;
  }

  public addItem(product: Product, quantity: number): Promise<boolean> {
    return new Promise(resolve => {
      this.productReservationService.addProductReservation(product.uuid, this.citySaverService.city.uuid, quantity).subscribe(
        res => {
          const searchedItem = this.currentBasketSubject.value.items.find(i => i.productUUID === product.uuid);
          if (searchedItem == null) {
            this.currentBasketSubject.value.items.push(new OrderItem({
              price: product.price,
              quantity: quantity,
              product: product,
              productUUID: product.uuid
            }));
          } else {
            searchedItem.quantity += quantity;
          }

          localStorage.setItem('currentBasket', JSON.stringify(this.currentBasketSubject.value));
          resolve(true);
        },
        error => {
          this.toastrService.warning('Продукт закінчився', 'Альорт');
          resolve(false);
        }
      );
    });
  }

  public removeItem(product: Product, quantity: number) {
    this.productReservationService.removeProductReservation(product.uuid, this.citySaverService.city.uuid, quantity).subscribe(
      res => {
        const searchedItem = this.currentBasketSubject.value.items.find(i => i.productUUID === product.uuid);
        if (searchedItem == null) {
          this.currentBasketSubject.value.items.push(new OrderItem({
            price: product.price,
            quantity: quantity,
            product: product,
            productUUID: product.uuid
          }));
        } else {
          searchedItem.quantity -= quantity;
        }

        localStorage.setItem('currentBasket', JSON.stringify(this.currentBasketSubject.value));
      },
      error => {

      }
    );
  }

  public clearItems(): void {

    localStorage.removeItem('currentBasket');
    this.currentBasketSubject.next(null);
  }
}
