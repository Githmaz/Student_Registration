import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { LogInFromComponent } from './components/log-in-from/log-in-from.component';
const routes: Routes = [
  { path: 'registration', component:RegistrationFormComponent },
  { path: 'login', component:LogInFromComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
