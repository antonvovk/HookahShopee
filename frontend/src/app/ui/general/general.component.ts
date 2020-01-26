import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { BasketService } from '../../services/basket.service';

@Component({
  selector: 'app-main-component',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.scss']
})
export class General implements OnInit {

  currentMenuIndex: number = 3;

  constructor(private changeDetector: ChangeDetectorRef,
              private basketService: BasketService) {

  }

  ngOnInit() {
  }

  changeIndex(index: number) {
    this.currentMenuIndex = -1;
    this.changeDetector.detectChanges();
    this.currentMenuIndex = index;
  }
}
