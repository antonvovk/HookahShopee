import {Component, OnInit} from '@angular/core';
import {ManufacturersService} from "../../../../services/manufacturers.service";
import {Manufacturer} from "../../../../core/model/manufacturer.model";

@Component({
  selector: 'app-manufacturers',
  templateUrl: './manufacturers.component.html',
  styleUrls: ['./manufacturers.component.scss']
})
export class ManufacturersComponent implements OnInit {

  manufacturers: Manufacturer[] = [];
  manufacturer: Manufacturer = null;
  edit_addMenuOpened: boolean = false;

  constructor(private manufacturersService: ManufacturersService) {
    this.manufacturersService.findAll().subscribe(manufacturers => {
      this.manufacturers = manufacturers;
    });
  }

  ngOnInit() {
  }

  menuOpened(manufacturer: Manufacturer) {
    this.manufacturer = manufacturer;
    this.edit_addMenuOpened = true;
  }

  onReturn($event: boolean) {
    this.manufacturersService.findAll().subscribe(manufacturers => {
      this.manufacturers = manufacturers;
    });
    this.edit_addMenuOpened = false;
  }

  getImage(imageName: string) {
    return 'http://localhost:8080/downloadFile/' + imageName;
  }
}
