import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { BasketService } from '../../services/basket.service';
import { AuthenticationService } from '../../services/authentication.service';
import { Role } from '../../core/model/role.model.enum';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-component',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.scss']
})
export class General implements OnInit {

  currentMenuIndex: number = 3;

  constructor(private changeDetector: ChangeDetectorRef,
              private authenticationService: AuthenticationService,
              private router: Router,
              public basketService: BasketService) {

  }

  ngOnInit() {
  }

  changeIndex(index: number) {
    this.currentMenuIndex = -1;
    this.changeDetector.detectChanges();
    this.currentMenuIndex = index;
  }

  check() {
    if (this.authenticationService.isAuthenticated()) {
      return (this.authenticationService.currentUserValue.user.role === Role.ADMIN ||
        this.authenticationService.currentUserValue.user.role === Role.SELLER) &&
        this.router.url === '/control-panel';
    }

    return false;
  }
}
