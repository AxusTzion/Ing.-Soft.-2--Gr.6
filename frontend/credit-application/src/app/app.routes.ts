import { Routes } from '@angular/router';
import {ClienteCreateComponent} from "./clientes/cliente-create/cliente-create.component";
import { NavBarComponent } from './utils/nav-bar/nav-bar.component';
import { CreditListComponent } from './admin/credit-list/credit-list.component';
import { LoginComponent } from './login/login.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';

export const routes: Routes = [
  {path: '', component:ClienteCreateComponent},
  {path: 'admin-dashboard',component: CreditListComponent},
  { path: 'login-cliente', component: LoginComponent},
  { path: 'login-admin', component: LoginAdminComponent},
];
