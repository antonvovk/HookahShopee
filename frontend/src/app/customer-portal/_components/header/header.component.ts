import { Component, OnInit } from '@angular/core';
import { BasketService } from '../../../_services/basket.service';
import { CUSTOMER_PORTAL_ROUTING } from '../../../globals';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  CUSTOMER_PORTAL_ROUTING = CUSTOMER_PORTAL_ROUTING;
  isOpen: boolean = false;

  constructor(public basketService: BasketService) {
  }

  triggerMenu() {
    this.isOpen = !this.isOpen;
  }

  ngOnInit() {
  }
}
