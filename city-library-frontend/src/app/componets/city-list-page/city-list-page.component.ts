import {Component, OnInit} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {CityService} from "../../services/city.service";
import {City} from "../../models/city";

@Component({
  selector: 'app-city-list-page',
  templateUrl: './city-list-page.component.html',
  styleUrls: ['./city-list-page.component.css']
})
export class CityListPageComponent implements OnInit {

  cities: City[] = [];
  totalElements: number = 0;

  constructor(public cityService: CityService) {
  }

  ngOnInit(): void {
    this.getCities({page: "0", size: "25"});
  }

  nextPage(event: PageEvent) {
    const request: any = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getCities(request);
  }

  getCities(request: any) {
    this.cityService.getAll(request)
      .subscribe((data: any) => {
          this.cities = data['content'];
          this.totalElements = data['totalElements'];
        }
        , error => {
          console.log(error.error.message);
        }
      );
  }


}
