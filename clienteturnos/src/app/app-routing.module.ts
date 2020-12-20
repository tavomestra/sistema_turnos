import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GenerarTurnosComponent } from './components/generar-turnos/generar-turnos.component';
import { LoginComponent } from './components/login/login.component';


const routes: Routes = [
  {path : "login", component : LoginComponent},
  {path : "generar-turnos", component : GenerarTurnosComponent},
  { path: "**", redirectTo: "login", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash : true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
