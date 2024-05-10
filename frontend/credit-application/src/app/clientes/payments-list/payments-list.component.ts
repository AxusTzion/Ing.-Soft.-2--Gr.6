import { Component } from '@angular/core';
import { ServiceCreditApplicationService } from '../../service/service-credit-application.service';
import {MatTableModule} from '@angular/material/table';

@Component({
  selector: 'app-payments-list',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './payments-list.component.html',
  styleUrl: './payments-list.component.css'
})
export class PaymentsListComponent {
  displayedColumns: string[] = ['id', 'cantidad', 'paymentDate', 'creditoId'];
  dataSource : any;
  clienteId: any;
  constructor(private client: ServiceCreditApplicationService) {
    this.getData();
  }

  getData() {
    this.client.getPaymentByClientList(this.clienteId).subscribe(res => {
      this.dataSource = res;
    })
  }
}
