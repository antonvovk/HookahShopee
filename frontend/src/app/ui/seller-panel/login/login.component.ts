import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../services/authentication.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login-panel',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl('380963587506', Validators.required),
    password: new FormControl('password', Validators.required),
  });

  constructor(private router: Router, private authenticationService: AuthenticationService) {

  }

  ngOnInit() {

  }

  onSubmit() {
    this.authenticationService.login(this.loginForm.get('username').value, this.loginForm.get('password').value);
  }
}
