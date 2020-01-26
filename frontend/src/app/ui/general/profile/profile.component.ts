import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentMenuIndex: number = 0;

  constructor(public authenticationService: AuthenticationService) {

  }


  ngOnInit() {
  }

  onReturned() {
    this.currentMenuIndex = 0;
  }

  onReturned2() {
    this.currentMenuIndex = 1;
  }
}
