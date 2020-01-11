import {Component, OnInit, ViewChild} from '@angular/core';
import {Order} from "../../../../core/model/order.model";
import {OrderService} from "../../../../services/order.service";
import {OrderStatus} from "../../../../core/model/order-status.model.enum";
import {User} from "../../../../core/model/user.model";
import {AuthenticationService} from "../../../../services/authentication.service";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Page} from "../../../../core/model/page.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  currentSeller: User;
  availableOrders: Page<Order> = new Page<Order>();
  activeOrders: Page<Order> = new Page<Order>();
  completedOrders: Page<Order> = new Page<Order>();

  @ViewChild("availableOrdersPaginator", {static: true}) availableOrdersPaginator: MatPaginator;
  @ViewChild("activeOrdersPaginator", {static: true}) activeOrdersPaginator: MatPaginator;
  @ViewChild("completedOrdersPaginator", {static: true}) completedOrdersPaginator: MatPaginator;

  constructor(private orderService: OrderService,
              private authService: AuthenticationService,
              private snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this.currentSeller = this.authService.currentUserValue.user;
    this.orderService.findAllByStatus(OrderStatus.NEW, 0, 1).subscribe(
      orders => {
        this.availableOrders = orders;
      }
    );
    this.orderService.findAllBySellerAndStatus(this.currentSeller.phoneNumber, OrderStatus.IN_PROGRESS, 0, 1).subscribe(
      orders => {
        this.activeOrders = orders;
      }
    );
    this.orderService.findAllBySellerAndStatus(this.currentSeller.phoneNumber, OrderStatus.COMPLETED, 0, 1).subscribe(
      orders => {
        this.completedOrders = orders;
      }
    );
  }

  availableOrdersPaginatorChanged($event: PageEvent) {
    this.orderService.findAllByStatus(OrderStatus.NEW, $event.pageIndex, $event.pageSize).subscribe(
      orders => {
        this.availableOrders = orders;
      }
    );
  }

  activeOrdersPaginatorChanged($event: PageEvent) {
    this.orderService.findAllBySellerAndStatus(this.currentSeller.phoneNumber, OrderStatus.IN_PROGRESS, $event.pageIndex, $event.pageSize).subscribe(
      orders => {
        this.activeOrders = orders;
      }
    );
  }

  completedOrdersPaginatorChanged($event: PageEvent) {
    this.orderService.findAllBySellerAndStatus(this.currentSeller.phoneNumber, OrderStatus.COMPLETED, $event.pageIndex, $event.pageSize).subscribe(
      orders => {
        this.completedOrders = orders;
      }
    );
  }

  assignToSeller(uuid: string) {
    this.orderService.assignToSeller(uuid, this.currentSeller.phoneNumber).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        this.availableOrdersPaginator._changePageSize(this.availableOrdersPaginator.pageSize);
        this.activeOrdersPaginator._changePageSize(this.activeOrdersPaginator.pageSize);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  cancelOrder(uuid: string) {
    this.orderService.cancelOrder(uuid).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        this.availableOrdersPaginator._changePageSize(this.availableOrdersPaginator.pageSize);
        this.activeOrdersPaginator._changePageSize(this.activeOrdersPaginator.pageSize);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }

  completeOrder(uuid: string) {
    this.orderService.completeOrder(uuid).subscribe(
      response => {
        this.snackBar.open(response.statusText, 'Відхилити', {duration: 2000,});
        this.activeOrdersPaginator._changePageSize(this.activeOrdersPaginator.pageSize);
        this.completedOrdersPaginator._changePageSize(this.completedOrdersPaginator.pageSize);
      },
      error => {
        this.snackBar.open(error.apierror.message, 'Відхилити', {duration: 2000,});
      }
    );
  }
}