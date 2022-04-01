import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {CityService} from "../../services/city.service";
import {ActivatedRoute} from "@angular/router";
import {City} from "../../models/city";
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-update-city',
  templateUrl: './update-city.component.html',
  styleUrls: ['./update-city.component.css']
})
export class UpdateCityComponent implements OnInit {

  form: any = {
    id: null,
    name: null,
    photo: null
  };
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

  onSubmit(): void {
    const {id, name, photo} = this.form;

    this.cityService.update(id, name, photo)
      .subscribe(
        (data: City) => {
          this.city = data;
        })
  }

  getCity(id: number): void {
    this.cityService.get(id)
      .subscribe(data => {
        this.form = data;
      })
  }

}
