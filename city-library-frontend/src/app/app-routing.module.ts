import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CityListPageComponent} from "./componets/city-list-page/city-list-page.component";
import {CityDetailsComponent} from "./componets/city-details/city-details.component";
import {UpdateCityComponent} from "./componets/update-city/update-city.component";
import {LoginComponent} from "./componets/login/login.component";

const routes: Routes = [
  { path: '', redirectTo: 'city', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'city', component: CityListPageComponent },
  { path: 'city/:id', component: CityDetailsComponent },
  { path: 'update/:id', component: UpdateCityComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
