import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {Basket} from "../core/model/basket.model";
import {OrderItem} from "../core/model/order-item.model";
import {ProductReservationService} from "./product-reservation.service";

@Injectable({providedIn: 'root'})
export class BasketService {

  private currentBasketSubject: BehaviorSubject<Basket>;

  constructor(private productReservationService: ProductReservationService) {
    this.currentBasketSubject = new BehaviorSubject<Basket>(JSON.parse(localStorage.getItem('currentBasket')));

    if (this.currentBasketSubject.value == null) {
      this.currentBasketSubject = new BehaviorSubject<Basket>(new Basket([]));
    }
  }

  public getItems(): OrderItem[] {
    return this.currentBasketSubject.value.items;
  }

  public getTotalPrice(): number {
    let sum: number = 0;
    this.currentBasketSubject.value.items.forEach(value => {
      sum += value.price;
    });
    return sum;
  }

  public addItem(item: OrderItem, cityName: string) {
    this.productReservationService.addProductReservation(item.product.name, cityName, item.quantity).subscribe(
      res => {
        this.currentBasketSubject.value.items.push(item);
        localStorage.setItem('currentBasket', JSON.stringify(this.currentBasketSubject.value));
      },
      error => {
        console.log(error);
      }
    );
  }

  public removeItem(item: OrderItem, cityName: string) {
    this.productReservationService.removeProductReservation(item.product.name, cityName, item.quantity).subscribe(
      res => {
        this.currentBasketSubject.value.items.splice(this.currentBasketSubject.value.items.indexOf(item), 1);
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
