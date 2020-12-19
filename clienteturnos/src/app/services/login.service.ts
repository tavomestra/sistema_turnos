import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "./../../environments/environment";

enum EndPoint {
    LOGIN = "login"
}

@Injectable({
    providedIn: "root",
  })
export class LoginService {
    
    constructor(private http: HttpClient) {}

    private getUrlService(endPoint: EndPoint) {
      return environment.apiUrl + endPoint;
    }

    doLogin(user: string, password : string) {
        let body = { user : user, password : password};
        return this.http.post<any>(
          this.getUrlService(EndPoint.LOGIN) + `/login`,
          body
        );
      }

}