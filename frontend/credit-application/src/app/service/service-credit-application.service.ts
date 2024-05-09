import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../Modelos/Client';
import { Credit } from '../Modelos/Credit';
import { CreditChangeStatusDto } from '../Modelos/CreditChangeStatusDto';
import { Login } from '../Modelos/login';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceCreditApplicationService {

  url = environment.apiUrl;
  constructor(private http: HttpClient) { }

  login(login: Login) {
    return this.http.post<Client>(this.url + "/login", login);
  }

  loginAdmin(login: Login) {
    return this.http.post<Client>(this.url + "/login/admin", login);
  }

  saveClient(client: Client) {
    return this.http.post<Client>(this.url + "/Cliente/create", client);
  }

  saveCredit(credit: Credit) {
    return this.http.post<Client>(this.url + "/Credito/create", credit);
  }

  getNonApprevedCreditList() {
    return this.http.get<Credit>(this.url + "/Credito/non-approved-credits");
  }

  changeCreditStatus(request: CreditChangeStatusDto) {
    return this.http.put(this.url + "/Credito/update-state", request);
  }
}
