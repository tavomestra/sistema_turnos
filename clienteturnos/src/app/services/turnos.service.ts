import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { ComercioRs } from '../model/ComercioRs';
import { ServicioRs } from '../model/ServicioRs';
import { TurnosRs } from '../model/TurnosRs';

enum EndPoint {
  COMERCIO = 'comercio',
  SERVICIO = 'servicio',
  TURNOS = 'turnos',
}

@Injectable({
  providedIn: 'root',
})
export class TurnosService {
  constructor(private http: HttpClient) {}

  private getUrlService(endPoint: EndPoint) {
    return environment.apiUrl + endPoint;
  }

  /**
   * Obtener todos los comercios.
   */
  getAllComercios()  {
    return this.http.get<ComercioRs[]>(
      this.getUrlService(EndPoint.COMERCIO) + `/get-all`
    );
  }

  /**
   * Obtener servicios por comercio
   * @param idComercio 
   */
  getServicioByComercio(idComercio : number)  {
    return this.http.get<ServicioRs[]>(
      this.getUrlService(EndPoint.SERVICIO) + `/get-by-comercio/` + idComercio
    );
  }

  /**
   * Listar turnos.
   * @param idServicio 
   * @param fechaInicio 
   * @param fechaFin 
   */
  getTurnos(idServicio : number, fechaInicio : string, fechaFin : string)  {
    let body = { idServicio: idServicio, fechaInicio : fechaInicio, fechaFin : fechaFin};
    return this.http.post<TurnosRs[]>(
      this.getUrlService(EndPoint.TURNOS) + `/find-turnos`,
      body
    );
  }

    /**
   * Generar turnos.
   * @param idServicio 
   * @param fechaInicio 
   * @param fechaFin 
   */
  generarTurnos(idServicio : number, fechaInicio : string, fechaFin : string)  {
    let body = { idServicio: idServicio, fechaInicio : fechaInicio, fechaFin : fechaFin};
    return this.http.post<TurnosRs[]>(
      this.getUrlService(EndPoint.TURNOS) + `/generar-turnos`,
      body
    );
  }
}
