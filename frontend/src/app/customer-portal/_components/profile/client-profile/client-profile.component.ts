import { Component, OnInit } from '@angular/core';
import { CitiesService } from '../../../../_services/cities.service';
import { City } from '../../../../_models/city.model';
import { AuthenticationService } from '../../../../_services/authentication.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.scss']
})
export class ClientProfileComponent implements OnInit {

  cities: City[] = [];
  city = new FormControl('');

  constructor(private citiesService: CitiesService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.city.setValue(this.authenticationService.currentUserValue.user.city);
    console.log(this.city);
    this.citiesService.findAll().subscribe(
      cities => {
        this.cities = cities;
      }
    );
  }
}
