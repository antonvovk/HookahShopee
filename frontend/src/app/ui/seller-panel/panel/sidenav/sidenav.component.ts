import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  currentMenuItemIndex = 0;

  constructor(private changeDetector: ChangeDetectorRef) {
  }

  ngOnInit() {
  }

  sellersClicked() {
    this.currentMenuItemIndex = 0;
  }

  clientsClicked() {
    this.currentMenuItemIndex = 1;
  }

  categoriesClicked() {
    this.currentMenuItemIndex = -1;
    this.changeDetector.detectChanges();
    this.currentMenuItemIndex = 2;
  }

  ordersClicked() {
    this.currentMenuItemIndex = 3;
  }

  postsClicked() {
    this.currentMenuItemIndex = -1;
    this.changeDetector.detectChanges();
    this.currentMenuItemIndex = 4;
  }
}
