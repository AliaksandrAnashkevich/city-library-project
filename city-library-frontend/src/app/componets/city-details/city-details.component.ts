import {Component, Input, OnInit} from '@angular/core';
import {CityService} from "../../services/city.service";
import {ActivatedRoute, Router} from "@angular/router";
import {City} from "../../models/city";
import {TokenStorageService} from "../../services/token-storage.service";
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-city-details',
  templateUrl: './city-details.component.html',
  styleUrls: ['./city-details.component.css']
})
export class CityDetailsComponent implements OnInit {

  id!: number;
  city!: City;
  message = '';
  showEditBoard = false;

  constructor(private tokenStorageService: TokenStorageService,
              public cityService: CityService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.message = '';
    this.getCity(this.route.snapshot.params['id']);

    const roles = this.tokenStorageService.getRoles();
    this.showEditBoard = roles.includes('ROLE_ALLOW_EDIT');
  };

  getCity(id: number): void {
    this.cityService.get(id)
      .subscribe((data: City) => {
          this.city = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}
