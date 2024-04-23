import { Component } from '@angular/core';
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {AbstractControl, AsyncValidatorFn, FormBuilder, FormControl, FormsModule, ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn, Validators} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {merge} from 'rxjs';
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { CurrencyPipe } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import { ServiceCreditApplicationService } from '../../service/service-credit-application.service';
import { Client } from '../../Modelos/Client';
import {MatSelectModule} from '@angular/material/select';
import { Credit } from '../../Modelos/Credit';
import {MatStepperModule} from '@angular/material/stepper';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cliente-create',
  standalone: true,
  imports: [
    MatFormField,
    MatIcon,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatDatepickerModule,
    MatSelectModule,
    MatStepperModule
  ],
  templateUrl: './cliente-create.component.html',
  styleUrl: './cliente-create.component.css'
})
export class ClienteCreateComponent {
  email = new FormControl('', [Validators.required, Validators.email]);
  errorMessage = '';
  cliente : Client = new Client();
  credit : Credit = new Credit();
  cuotasDeCredito:any;
  aceptableAmount: any = null;
  acceptableCredit: any = null;
  monthlyPayment: any = null;
  firstFormGroup = this.formBuilder.group({
    identificationNumber: [''],
    name: [''],
    lastName: [''],
    city: [''],
    dateOfBirth: [''],
    income: [''],
    email: ['',Validators.email],
    address: [''],
    mobile: ['',  Validators.pattern('[0-9]{10}')]
  });

  userExistsValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      return this.validateUserExists(control.value) ? {incomeValidator: true} : null;
    }
  }

  validateUserExists(cantidad: any) {
      var incomeNumber : number = Number(this.cliente.ingresos);
      var cuotasNumber : number = Number(this.credit.numeroDeCuotas);
      var interestRate : number = this.getInterestRate(this.credit.tipo);
      var creditNumber : number = Number(cantidad);
      var incomeAceptablePercent = incomeNumber * 0.7;
      var cuotasDeCredito = ((creditNumber * interestRate) + creditNumber) / cuotasNumber;
      this.aceptableAmount = cuotasDeCredito >= incomeAceptablePercent ? cuotasDeCredito : null;
      this.acceptableCredit =  incomeAceptablePercent *  cuotasNumber;
      this.monthlyPayment = incomeAceptablePercent;
      console.log( Number(cuotasDeCredito));
      console.log(Number(this.monthlyPayment) );
      console.log(Number(this.monthlyPayment) <= Number(cuotasDeCredito));
      return Number(this.monthlyPayment) <= Number(cuotasDeCredito) ;
  }

  getInterestRate(type: any) {
    switch (type){
      case "0" :
        return 1.1;
        break;
      case "3" :
        return 0.9;
        break;
      case "1" :
        return 1.7;
        break;
      case "2" :
        return 0.8;
        break;
      case "4" :
        return 1.5;
        break
      default :
        return 0;
      }
    }


  secondFormGroup = this.formBuilder.group({
    tipo: ['', [Validators.required]],
    plazo: ['', Validators.required],
    amount :['', [Validators.required, this.userExistsValidator()]]
  });

  constructor(private client: ServiceCreditApplicationService, private formBuilder: FormBuilder, private _snackBar: MatSnackBar, private router:Router) {
    merge(this.email.statusChanges, this.email.valueChanges)
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }

  ngOnInit(): void {

  }

  createCliente() {
    this.client.saveClient(this.cliente).subscribe(res => {
      this.cliente.id = res.id;
      this.createCredit();
    });
  }

  createCredit() {
    this.credit.cliente = this.cliente;
    this.client.saveCredit(this.credit).subscribe(res => {
      this._snackBar.open("Su solicitud a sido recibida, en los proximos dias recibira mas informacion sobre la peticion", "Ok");
    });
  }

  updateErrorMessage() {
    if (this.email.hasError('required')) {
      this.errorMessage = 'You must enter a value';
    } else if (this.email.hasError('email')) {
      this.errorMessage = 'Not a valid email';
    } else {
      this.errorMessage = '';
    }
  }
}
