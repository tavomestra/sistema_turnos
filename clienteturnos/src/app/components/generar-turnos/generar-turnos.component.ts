import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NgbDate, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { NgxSpinnerService } from 'ngx-spinner';
import { ComercioRs } from 'src/app/model/ComercioRs';
import { ServicioRs } from 'src/app/model/ServicioRs';
import { TurnosRs } from 'src/app/model/TurnosRs';
import { TurnosService } from 'src/app/services/turnos.service';
import Swal, { SweetAlertIcon } from 'sweetalert2';

@Component({
  selector: 'app-generar-turnos',
  templateUrl: './generar-turnos.component.html',
  styleUrls: ['./generar-turnos.component.css'],
})
export class GenerarTurnosComponent implements OnInit {
  usuario: string = localStorage.getItem('user');

  public form: FormGroup;
  submitted = false;

  listComercios: ComercioRs[] = [];
  listServicios: ServicioRs[] = [];
  listTurnos: TurnosRs[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private turnosService: TurnosService,
    private spinner: NgxSpinnerService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      comercio: new FormControl('', [Validators.required]),
      servicio: new FormControl('', [Validators.required]),
      fechaInicio: new FormControl('', { validators: [Validators.required] }),
      fechaFinal: new FormControl('', { validators: [Validators.required] }),
    });
    this.getAllComercios();
  }

  get formulario() {
    return this.form.controls;
  }

  validarFechas() {
    let erroresFechaInicio = {};
    let erroresFechaFinal = {};

    if (this.formulario.fechaInicio.value && this.formulario.fechaFinal.value) {
      const fechaIni: NgbDate = NgbDate.from(this.formulario.fechaInicio.value);
      const fechaFin: NgbDate = NgbDate.from(this.formulario.fechaFinal.value);

      if (fechaIni.after(fechaFin)) {
        erroresFechaInicio['outRange1'] =
          'La fecha inicio debe ser menor a la fecha fin';
      } else {
        this.formulario.fechaInicio.setErrors(null);
      }

      if (fechaFin.before(fechaIni)) {
        erroresFechaFinal['outRange1'] =
          'La fecha fin no puede ser menor a la fecha inicio';
      } else {
        this.formulario.fechaFinal.setErrors(null);
      }
    }
    for (const key in erroresFechaInicio) {
      this.formulario.fechaInicio.setErrors(erroresFechaInicio);
    }

    for (const key in erroresFechaFinal) {
      this.formulario.fechaFinal.setErrors(erroresFechaFinal);
    }
  }

  getAllComercios() {
    this.spinner.show();
    this.listComercios = [];
    this.turnosService.getAllComercios().subscribe(
      (data) => {
        this.listComercios = data;
        this.spinner.hide();
      },
      (error) => {
        this.spinner.hide();
        console.log(error);
      }
    );
  }

  getServiciosByComercio(item) {
    this.listServicios = [];
    if(item){
      this.spinner.show();
      this.turnosService.getServicioByComercio(item).subscribe(
        (data) => {
          this.listServicios = data;
          this.spinner.hide();
        },
        (error) => {
          this.spinner.hide();
          console.log(error);
        }
      );
      this.formulario.servicio.setValue("");
    }
   
  }

  getTurnos() {
    this.spinner.show();
    this.listTurnos = [];
    let idServicio = this.formulario.servicio.value;
    let fechaIni = this.dateFormat(this.formulario.fechaInicio.value);
    let fechaFin = this.dateFormat(this.formulario.fechaFinal.value);

    this.turnosService.getTurnos(idServicio, fechaIni, fechaFin).subscribe(
      (data) => {
        this.listTurnos = data;
        this.spinner.hide();
        if (this.listTurnos.length == 0) {
          this.modal('No se encontrador resultados', 'warning');
        }
      },
      (error) => {
        this.spinner.hide();
      }
    );
  }
  generarTurnos() {
    this.spinner.show();
    this.listTurnos = [];
    let idServicio = this.formulario.servicio.value;
    let fechaIni = this.dateFormat(this.formulario.fechaInicio.value);
    let fechaFin = this.dateFormat(this.formulario.fechaFinal.value);

    this.turnosService.generarTurnos(idServicio, fechaIni, fechaFin).subscribe(
      (data) => {
        this.listTurnos = data;
        this.modal('Se generaron los turnos correctamente');
        this.spinner.hide();
      },
      (error) => {
        this.modal(error.error.message, 'error');
        this.spinner.hide();
      }
    );
  }

  buscar() {
    this.submitted = true;
    if (this.form.valid) {
      this.getTurnos();
    }
  }

  generar() {
    this.submitted = true;
    if (this.form.valid) {
      this.generarTurnos();
    }
  }

  private dateFormat(fecha: NgbDateStruct): string {
    return (
      fecha.year +
      '-' +
      (fecha.month <= 9 ? '0' + fecha.month : fecha.month) +
      '-' +
      (fecha.day <= 9 ? '0' + fecha.day : fecha.day)
    );
  }

  modal(mensaje: string, icon: SweetAlertIcon = 'success') {
    Swal.fire({
      icon: icon,
      text: mensaje,
    });
  }
}
