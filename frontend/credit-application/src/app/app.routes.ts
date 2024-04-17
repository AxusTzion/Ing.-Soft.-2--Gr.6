import { Routes } from '@angular/router';
import {ClienteCreateComponent} from "./clientes/cliente-create/cliente-create.component";

export const routes: Routes = [
  {path: 'createclient', component:ClienteCreateComponent},
  {path: 'listarCredito', component:ClienteCreateComponent}
];
