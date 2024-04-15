import { Component } from '@angular/core';
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {merge} from 'rxjs';
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-cliente-create',
  standalone: true,
  imports: [
    MatFormField,
    MatIcon,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule,
    MatDatepickerModule
  ],
  templateUrl: './cliente-create.component.html',
  styleUrl: './cliente-create.component.css'
})
export class ClienteCreateComponent {
  email = new FormControl('', [Validators.required, Validators.email]);
  formattedAmount: any;
  amount : any;
  errorMessage = '';

  constructor(private currencyPipe : CurrencyPipe) {
    merge(this.email.statusChanges, this.email.valueChanges)
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }

  transformAmount(element: any){
    this.formattedAmount = this.currencyPipe.transform(this.formattedAmount, '$');

    element.target.value = this.formattedAmount;
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
