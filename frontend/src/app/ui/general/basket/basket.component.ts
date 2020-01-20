import {Component, OnInit} from '@angular/core';
import {BasketService} from "../../../services/basket.service";
import {CitySaverService} from "../../../services/city-saver.service";

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.scss']
})
export class BasketComponent implements OnInit {

  constructor(private basketService: BasketService,
              private citySaverService: CitySaverService) {
  }

  ngOnInit() {
    console.log(this.basketService.getTotalPrice());
  }

  clearItems() {
    this.basketService.clearItems(this.citySaverService.getCityName());
  }
}

