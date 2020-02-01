import {Component, OnInit} from '@angular/core';
import { User } from '../../_models/user.model';
import { City } from '../../_models/city.model';
import { SellersService } from '../../_services/sellers.service';
import { CitiesService } from '../../_services/cities.service';
import { Role } from '../../_models/role.model.enum';

@Component({
  selector: 'app-sellers',
  templateUrl: './sellers.component.html',
  styleUrls: ['./sellers.component.scss']
})
export class SellersComponent implements OnInit {

  sellers: User[] = [];
  roles: string[] = [];
  cities: City[] = [];

  addNewOpened: boolean = false;

  constructor(private sellersService: SellersService, private citiesService: CitiesService) {
    sellersService.findAllSellers().subscribe(users => {
      this.sellers = users;
    });

    citiesService.findAll().subscribe(cities => {
      this.cities = cities;
    });

    Object.keys(Role).filter(key => !Number(key) && key !== '0').forEach(key => {
      this.roles.push(key);
    });
  }

  ngOnInit() {
  }

  addNewClicked() {
    this.addNewOpened = true;
  }
}
