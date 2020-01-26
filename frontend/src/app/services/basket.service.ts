import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Basket } from '../core/model/basket.model';
import { OrderItem } from '../core/model/order-item.model';
import { ProductReservationService } from './product-reservation.service';
import { CitySaverService } from './city-saver.service';
import { Product } from '../core/model/product.model';

@Injectable({providedIn: 'root'})
export class BasketService {

  private currentBasketSubject: BehaviorSubject<Basket>;

  constructor(private productReservationService: ProductReservationService,
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
    return this.currentBasketSubject.value.items.length == 0;
  }

  public getTotalPrice(): number {
    let sum: number = 0;
    this.currentBasketSubject.value.items.forEach(value => {
      sum += value.price;
    });
    return sum;
  }

  public addItem(product: Product, quantity: number) {
    console.log(this.citySaverService.city);
    this.productReservationService.addProductReservation(product.uuid, this.citySaverService.city.uuid, quantity).subscribe(
      res => {
        let searchedItem = this.currentBasketSubject.value.items.find(i => i.productUUID === product.uuid);
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
      },
      error => {
        console.log(error);
      }
    );
  }

  public removeItem(product: Product, quantity: number) {
    this.productReservationService.removeProductReservation(product.uuid, this.citySaverService.city.uuid, quantity).subscribe(
      res => {
        let searchedItem = this.currentBasketSubject.value.items.find(i => i.productUUID === product.uuid);
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
        console.log(error);
      }
    );
  }

  public clearItems(cityName: string): void {
    this.currentBasketSubject.value.items.forEach(item => {
      this.productReservationService.clearProductReservation(item.product.name, cityName).subscribe(
        res => {
          localStorage.removeItem('currentBasket');
          this.currentBasketSubject.next(null);
        },
        error => {
          console.log(error);
        }
      );
    });
  }
}
