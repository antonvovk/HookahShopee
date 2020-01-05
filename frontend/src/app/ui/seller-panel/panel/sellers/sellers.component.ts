import {Component, OnInit} from '@angular/core';
import {User} from "../../../../core/model/user.model";
import {SellersService} from "../../../../services/sellers.service";
import {Role} from "../../../../core/model/role.model.enum";
import {City} from "../../../../core/model/city.model";
import {CitiesService} from "../../../../services/cities.service";

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
