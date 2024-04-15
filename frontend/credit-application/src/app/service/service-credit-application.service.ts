import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../Modelos/Client';

@Injectable({
  providedIn: 'root'
})
export class ServiceCreditApplicationService {

  url = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  saveClient(client: Client) {
    return this.http.post<Client>(this.url + "/Cliente/create", client);
  }
}
