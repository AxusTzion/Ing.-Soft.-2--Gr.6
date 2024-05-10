import { Routes } from '@angular/router';
import {ClienteCreateComponent} from "./clientes/cliente-create/cliente-create.component";
import { NavBarComponent } from './utils/nav-bar/nav-bar.component';
import { CreditListComponent } from './admin/credit-list/credit-list.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './utils/Guards/AuthGuard';
import { AuthAdminGuard } from './utils/Guards/AuthAdminGuard';
import { PaymentsListComponent } from './clientes/payments-list/payments-list.component';

export const routes: Routes = [
  {path: '', component:ClienteCreateComponent},
  {path: 'admin-dashboard',component: CreditListComponent , canActivate: [AuthGuard, AuthAdminGuard] },
  { path: 'login-cliente', component: LoginComponent},
  { path: 'cliente-dashboard/:id', component: PaymentsListComponent},
];
