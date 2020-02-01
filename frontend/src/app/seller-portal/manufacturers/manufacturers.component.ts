import { Component, OnInit } from '@angular/core';
import { ManufacturersService } from '../../_services/manufacturers.service';
import { ImageService } from '../../_services/image.service';
import { Manufacturer } from '../../_models/manufacturer.model';

@Component({
  selector: 'app-manufacturers',
  templateUrl: './manufacturers.component.html',
  styleUrls: ['./manufacturers.component.scss']
})
export class ManufacturersComponent implements OnInit {

  manufacturers: Manufacturer[] = [];
  selectedManufacturer: Manufacturer = null;
  edit_addComponentOpened: boolean = false;

  constructor(private manufacturersService: ManufacturersService,
              public imageService: ImageService) {

  }

  ngOnInit() {
    this.manufacturersService.getAll().subscribe(manufacturers => {
      this.manufacturers = manufacturers;
    });
  }

  edit_addComponentOpen(manufacturer: Manufacturer) {
    this.selectedManufacturer = manufacturer;
    this.edit_addComponentOpened = true;
  }

  onReturn($event: boolean) {
    this.manufacturersService.getAll().subscribe(manufacturers => {
      this.manufacturers = manufacturers;
      this.edit_addComponentOpened = false;
    });
  }
}
