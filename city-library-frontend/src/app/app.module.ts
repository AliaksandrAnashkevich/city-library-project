import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

import {CityDetailsComponent} from './componets/city-details/city-details.component';
import {CityListPageComponent} from './componets/city-list-page/city-list-page.component';
import {UpdateCityComponent} from './componets/update-city/update-city.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {LoginComponent} from "./componets/login/login.component";

import {authInterceptorProviders} from './helper/auth.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    CityDetailsComponent,
    CityListPageComponent,
    UpdateCityComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatPaginatorModule,
    BrowserAnimationsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
