import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../Modelos/Client';
import { Credit } from '../Modelos/Credit';
import { Login } from '../Modelos/login';

@Injectable({
  providedIn: 'root'
})
export class ServiceCreditApplicationService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  login(login: Login) {
    return this.http.post<Client>(this.url + "/login", login);
  }

  saveClient(client: Client) {
    return this.http.post<Client>(this.url + "/Cliente/create", client);
  }

  saveCredit(credit: Credit) {
    return this.http.post<Client>(this.url + "/Credito/create", credit);
  }
}
