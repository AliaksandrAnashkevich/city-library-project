import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from "rxjs";
import {City} from "../models/city";
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const baseUrl = 'http://localhost:8080/city';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private httpClient: HttpClient) {
  }

  get(id: number): Observable<any> {
    return this.httpClient.get(`${baseUrl}/${id}`);
  }

  getAll(request: any): Observable<any> {
    const params = request;
    return this.httpClient.get(baseUrl, {params});
  }

  update(id: number, name: any, photo: any): Observable<any> {
    return this.httpClient.put(`${baseUrl}/${id}`, {name, photo}, httpOptions);
  }
}
