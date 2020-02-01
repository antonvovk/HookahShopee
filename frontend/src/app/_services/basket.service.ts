import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ProductReservationService } from './product-reservation.service';
import { CitySaverService } from './city-saver.service';
import { ToastrService } from 'ngx-toastr';
import { Basket } from '../_models/basket.model';
import { OrderItem } from '../_models/order-item.model';
import { Product } from '../_models/product.model';
import { HttpResponse } from '@angular/common/http';
import { Logger } from '@wuja/logger';

@Injectable({providedIn: 'root'})
export class BasketService {

  private currentBasketSubject: BehaviorSubject<Basket>;

  constructor(private productReservationService: ProductReservationService,
              private toastrService: ToastrService,
              private logger: Logger,
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
    if (this.currentBasketSubject.value == null) {
      return 0;
    }

    let sum = 0;
    this.currentBasketSubject.value.items.forEach(
      item => {
        sum += item.quantity;
      }
    );
    return sum;
  }

  public isEmpty(): boolean {
    if (this.currentBasketSubject.value == null) {
      return true;
    }

    return this.currentBasketSubject.value.items.length === 0;
  }

  public getTotalPrice(): number {
    if (this.currentBasketSubject.value == null) {
      return 0;
    }

    let sum: number = 0;
    this.currentBasketSubject.value.items.forEach(value => {
      sum += value.price * value.quantity;
    });
    return sum;
  }

  public addItem(product: Product, quantity: number): Observable<OrderItem[]> {
    return new Observable<OrderItem[]>((observer) => {
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
          this.logger.log(JSON.stringify(res));
          observer.next(this.currentBasketSubject.value.items);
          observer.complete();
        },
        error => {
          this.logger.error(JSON.stringify(error));
          observer.error(error);
          observer.complete();
        }
      );
    });
  }

  public removeItem(product: Product, quantity: number) {
    return new Observable<OrderItem[]>((observer) => {
      this.productReservationService.removeProductReservation(product.uuid, this.citySaverService.city.uuid, quantity).subscribe(
        res => {
          const searchedItem = this.currentBasketSubject.value.items.find(i => i.productUUID === product.uuid);
          if (searchedItem == null) {
            return;
          } else {
            searchedItem.quantity -= quantity;
          }

          if (searchedItem.quantity === 0) {
            const index: number = this.getItems().indexOf(searchedItem);
            this.currentBasketSubject.value.items.splice(index, 1);
          }

          localStorage.setItem('currentBasket', JSON.stringify(this.currentBasketSubject.value));
          this.logger.log(JSON.stringify(res));
          observer.next(this.currentBasketSubject.value.items);
          observer.complete();
        },
        error => {
          this.logger.error(JSON.stringify(error));
          observer.error(error);
          observer.complete();
        }
      );
    });
  }

  public clearItems() {
    return new Observable<HttpResponse<any>>((observer) => {
      this.getItems().forEach(
        item => {
          this.productReservationService.removeProductReservation(item.productUUID, this.citySaverService.city.uuid, item.quantity).subscribe(
            res => {
              localStorage.removeItem('currentBasket');
              this.currentBasketSubject.next(null);
              this.logger.log(JSON.stringify(res));
              observer.next(res);
              observer.complete();
            },
            error => {
              this.logger.error(JSON.stringify(error));
              observer.error(error);
              observer.complete();
            }
          );
        }
      );
    });
  }
}
