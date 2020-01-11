import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../../services/authentication.service";
import {User} from "../../../../core/model/user.model";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentSeller: User;

  constructor(private authService: AuthenticationService) {
    this.currentSeller = authService.currentUserValue.user;
  }

  ngOnInit(): void {
  }

  logOut() {
    this.authService.logout();
  }
}
