import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-main-component',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.scss']
})
export class General implements OnInit {

  currentMenuIndex: number = 3;

  constructor(private changeDetector: ChangeDetectorRef) {

  }

  ngOnInit() {
  }

  changeIndex(index: number) {
    this.currentMenuIndex = -1;
    this.changeDetector.detectChanges();
    this.currentMenuIndex = index;
  }
}
