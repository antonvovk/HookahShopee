import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { City } from '../../../../_models/city.model';
import { AuthenticationService } from '../../../../_services/authentication.service';
import { CitiesService } from '../../../../_services/cities.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  cities: City[] = [];
  @Output()
  returned = new EventEmitter<boolean>();

  phoneNumberFormControl = new FormControl('', [Validators.required, Validators.pattern('([0-9]{3})\\s([0-9]{2})\\s([0-9]{3})\\s([0-9]{2})\\s([0-9]{2})')]);
  smsCodeFormControl = new FormControl('', [Validators.required]);
  passwordFormControl = new FormControl('', [Validators.required]);
  reenteredPasswordFormControl = new FormControl('', [Validators.required]);
  firstAndLastNamesFormControl = new FormControl('', [Validators.required]);
  cityFormControl = new FormControl('', [Validators.required]);
  disabled = true;

  constructor(private citiesService: CitiesService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.citiesService.findAll().subscribe(
      cities => {
        this.cities = cities;
        this.cityFormControl.setValue(this.cities[0].name);
      }
    );
  }

  autocompleteNumber($event: KeyboardEvent) {
    let phoneNumber = String('380 ' + String(this.phoneNumberFormControl.value).substring(4));

    if (phoneNumber.length === 6) {
      phoneNumber = phoneNumber.substring(0, 6) + ' ' + phoneNumber.substring(6);
    }

    if (phoneNumber.length === 10) {
      phoneNumber = phoneNumber.substring(0, 10) + ' ' + phoneNumber.substring(10);
    }

    if (phoneNumber.length === 13) {
      phoneNumber = phoneNumber.substring(0, 13) + ' ' + phoneNumber.substring(13);
    }

    this.phoneNumberFormControl.setValue(phoneNumber);
  }

  validateNumber() {
    return this.phoneNumberFormControl.valid;
  }

  validatePassword() {
    return this.passwordFormControl.valid &&
      this.reenteredPasswordFormControl.valid &&
      this.passwordFormControl.value === this.reenteredPasswordFormControl.value;
  }

  validateFirstAndLastName() {
    return this.firstAndLastNamesFormControl.valid;
  }

  getValueSmsCode() {
    if (this.validateNumber()) {
      return '12345';
    } else {
      return 'Код з смс';
    }
  }

  register() {
    this.authenticationService.register(
      String(this.phoneNumberFormControl.value).replace(/\s/g, ''),
      this.passwordFormControl.value,
      String(this.firstAndLastNamesFormControl.value).split(' ')[0],
      String(this.firstAndLastNamesFormControl.value).split(' ')[1],
      this.cityFormControl.value).subscribe(
      res => {
        this.returned.emit(true);
      },
      err => {

      }
    );
  }
}


