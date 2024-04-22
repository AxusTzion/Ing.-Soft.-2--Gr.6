import { Routes } from '@angular/router';
import {ClienteCreateComponent} from "./clientes/cliente-create/cliente-create.component";
import { NavBarComponent } from './utils/nav-bar/nav-bar.component';

export const routes: Routes = [
  {path: '', component:ClienteCreateComponent}
];
