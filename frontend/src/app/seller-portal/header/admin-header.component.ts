import {Component, OnInit} from '@angular/core';
import { User } from '../../_models/user.model';
import { AuthenticationService } from '../../_services/authentication.service';
@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.scss']
})
export class AdminHeaderComponent implements OnInit {

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
