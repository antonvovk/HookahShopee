import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login-panel',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  username = new FormControl('380963587506');
  password = new FormControl('password');
  @Output()
  returned = new EventEmitter<boolean>();

  constructor(private router: Router, private authenticationService: AuthenticationService) {

  }

  ngOnInit() {

  }

  onSubmit() {
    this.authenticationService.login(this.username.value, this.password.value);
  }
}