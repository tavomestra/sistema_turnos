import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import Swal, { SweetAlertIcon } from 'sweetalert2';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'clienteturnos';
  public form_login: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.form_login = this.formBuilder.group({
      user: new FormControl("", []),
      password: new FormControl("", [])
    });
  }

  get formulario() {
    return this.form_login.controls;
  }

  modal(mensaje: string, icon: SweetAlertIcon = "success") {
    Swal.fire({
      icon: icon,
      text: mensaje
    })
  }

  login() {
    this.loginService.doLogin(this.formulario.user.value, this.formulario.password.value)
      .subscribe(
        (data) => { console.log(data) },
        (error) => {  this.modal(error.error.message, 'error'); }

      );
   
  }



}
