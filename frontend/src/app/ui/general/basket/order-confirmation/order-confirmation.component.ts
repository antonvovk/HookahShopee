import { AfterViewInit, Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NovaposhtaService } from '../../../../services/novaposhta.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReplaySubject, Subject } from 'rxjs';
import { MatSelect } from '@angular/material/select';
import { takeUntil } from 'rxjs/operators';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Order } from '../../../../core/model/order.model';
import { OrderItem } from '../../../../core/model/order-item.model';
import { OrderService } from '../../../../services/order.service';
import { DeliveryType } from '../../../../core/model/delivery-type.enum';
import { PaymentType } from '../../../../core/model/payment-type.enum';

@Component({
  selector: 'app-order-confirmation',
  templateUrl: './order-confirmation.component.html',
  styleUrls: ['./order-confirmation.component.scss']
})
export class OrderConfirmationComponent implements OnInit, AfterViewInit, OnDestroy {

  @Input()
  orderItems: OrderItem[] = [];

  public filteredBanks: ReplaySubject<any[]> = new ReplaySubject<any[]>(1);
  public warehouses: ReplaySubject<any[]> = new ReplaySubject<any[]>(1);
  @ViewChild('singleSelect', {static: true}) singleSelect: MatSelect;
  personalDataFormGroup: FormGroup;
  contactDataFormGroup: FormGroup;
  deliveryDataFormGroup: FormGroup;
  deliveryTypeFormGroup: FormGroup;
  paymentDataFormGroup: FormGroup;
  DeliveryType = DeliveryType;
  PaymentType = PaymentType;
  protected _onDestroy = new Subject<void>();

  constructor(private novaposhtaService: NovaposhtaService,
              private authenticationService: AuthenticationService,
              private _formBuilder: FormBuilder,
              private orderService: OrderService) {

  }

  ngOnInit() {
    this.filteredBanks.next([]);
    this.warehouses.next([]);

    this.personalDataFormGroup = this._formBuilder.group({
      firstNameFormControl: ['', Validators.required],
      lastNameControl: ['', Validators.required]
    });

    this.contactDataFormGroup = this._formBuilder.group({
      phoneNumberFormControl: ['', Validators.required],
      emailFormControl: ['']
    });

    this.deliveryTypeFormGroup = this._formBuilder.group({
      deliveryTypeFormControl: ['', Validators.required],
    });

    this.deliveryDataFormGroup = this._formBuilder.group({
      cityFormControl: ['', Validators.required],
      choosedWarehouse: ['', Validators.required],
      cityFilterFormControl: ['']
    });

    this.paymentDataFormGroup = this._formBuilder.group({
      paymentFormControl: ['', Validators.required],
    });

    if (this.authenticationService.isAuthenticated()) {
      const user = this.authenticationService.currentUserValue.user;
      this.personalDataFormGroup.controls.firstNameFormControl.setValue(user.firstName);
      this.personalDataFormGroup.controls.lastNameControl.setValue(user.lastName);
      this.contactDataFormGroup.controls.phoneNumberFormControl.setValue(user.phoneNumber);
    }

    this.deliveryDataFormGroup.controls.cityFilterFormControl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.filterBanks();
      });
  }

  ngAfterViewInit() {
    // this.setInitialValue();
  }

  ngOnDestroy() {
    this._onDestroy.next();
    this._onDestroy.complete();
  }

  onCitySelected() {
    this.novaposhtaService.findWarehouseByName(this.deliveryDataFormGroup.controls.cityFormControl.value.Description).subscribe(
      next => {
        this.warehouses.next(next);
        console.log(this.deliveryDataFormGroup.controls.cityFormControl.value.Description);
        console.log(this.warehouses);
      },
      error => {
        console.log(error);
      }
    );
  }

  createOrder() {
    this.orderItems.forEach(orderItem => {
      orderItem.product = null;
    });

    const order = new Order({
        clientUUID: this.authenticationService.currentUserValue.user.uuid,
        orderItems: this.orderItems,
        firstName: this.personalDataFormGroup.controls.firstNameFormControl.value,
        lastName: this.personalDataFormGroup.controls.lastNameControl.value,
        phoneNumber: this.contactDataFormGroup.controls.phoneNumberFormControl.value,
        email: this.contactDataFormGroup.controls.emailFormControl.value,
        deliveryType: this.deliveryTypeFormGroup.controls.deliveryTypeFormControl.value,
        deliveryCity: this.deliveryDataFormGroup.controls.cityFormControl.value,
        deliveryDepartment: this.deliveryDataFormGroup.controls.choosedWarehouse.value,
        paymentType: this.paymentDataFormGroup.controls.paymentFormControl.value
      }
    );

    console.log(order);
    this.orderService.createOrder(order).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log(err);
      }
    );
  }

  // protected setInitialValue() {
  //   this.filteredBanks
  //     .pipe(take(1), takeUntil(this._onDestroy))
  //     .subscribe(() => {
  //       this.singleSelect.compareWith = (a: any, b: any) => a && b && a.Description === b.Description;
  //     });
  // }

  protected filterBanks() {
    let search = this.deliveryDataFormGroup.controls.cityFilterFormControl.value;

    this.novaposhtaService.findCityByName(search).subscribe(
      next => {
        this.filteredBanks.next(next);
      },
      error => {
        console.log(error);
      }
    );
  }
}
