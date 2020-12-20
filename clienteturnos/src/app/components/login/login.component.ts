import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { LoginService } from 'src/app/services/login.service';
import Swal, { SweetAlertIcon } from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public form_login: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private spinner: NgxSpinnerService
  ) {}

  ngOnInit(): void {
    this.form_login = this.formBuilder.group({
      user: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  get formulario() {
    return this.form_login.controls;
  }

  modal(mensaje: string, icon: SweetAlertIcon = 'success') {
    Swal.fire({
      icon: icon,
      text: mensaje,
    });
  }

  login() {
    this.submitted = true;   
    this.spinner.show(); 
    if (this.form_login.valid) {
      let user = this.formulario.user.value;
      this.loginService
        .doLogin(user, this.formulario.password.value)
        .subscribe(
          (data) => {
            this.spinner.hide();
            localStorage.setItem('user', user);
            this.router.navigateByUrl('generar-turnos');
          },
          (error) => {
            this.spinner.hide();
            this.modal(error.error.message, 'error');
          }
          
        );
       
    }
  }
}
