import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {Basket} from "../core/model/basket.model";
import {OrderItem} from "../core/model/order-item.model";

@Injectable({providedIn: 'root'})
export class BasketService {

  private currentBasketSubject: BehaviorSubject<Basket>;

  constructor() {
    this.currentBasketSubject = new BehaviorSubject<Basket>(JSON.parse(localStorage.getItem('currentBasket')));

    if (this.currentBasketSubject.value == null) {
      this.currentBasketSubject = new BehaviorSubject<Basket>(new Basket([]));
    }
  }

  public addItem(item: OrderItem) {
    this.currentBasketSubject.value.items.push(item);
    localStorage.setItem('currentBasket', JSON.stringify(this.currentBasketSubject.value));
  }

  public getItems(): OrderItem[] {
    return this.currentBasketSubject.value.items;
  }

  public clearItems(): void {
    localStorage.removeItem('currentBasket');
    this.currentBasketSubject.next(null);
  }
}
