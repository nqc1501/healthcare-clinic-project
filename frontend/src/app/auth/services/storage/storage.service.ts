import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

const TOKEN = 'c_token';
const ID = 'c_id';
const ROLE = 'c_role';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor(
    private sCookie: CookieService,
  ) { }

  public hasToken() {
    if (this.getJwtFromCookie() == '') {
      return false;
    }

    if (this.getJwtFromCookie() === null) {
      return false;
    }

    return true;
  }

  public saveJwtToken(jwt: string) {
    const expirationTime = 6 * 60 * 60 * 1000;
    this.sCookie.set(TOKEN, jwt, expirationTime, '/', null, true, 'Strict');
  }

  public saveId(id: number) {
    window.localStorage.removeItem(ID);
    window.localStorage.setItem(ID, JSON.stringify(id));
  }

  public saveRole(role: string) {
    window.localStorage.removeItem(ROLE);
    window.localStorage.setItem(ROLE, JSON.stringify(role));
  }

  public getJwtFromCookie() {
    return this.sCookie.get(TOKEN);
  }

  public getRole() {
    return JSON.parse(localStorage.getItem(ROLE));
  }

  public getUserRole() {
    const user = this.getRole();
    if (user == null) {
      return 'none';
    }
    return user.role;
  }

  public getUserId() {
    return JSON.parse(localStorage.getItem(ID));
  }

  public isAdminLoggedIn() {
    if (this.getJwtFromCookie() == '') {
      return false;
    }
    
    const role = this.getRole();
    return role == 'ADMIN';
  }

  public isDoctorLoggedIn() {
    if (this.getJwtFromCookie() == null) {
      return false;
    }

    const role = this.getUserRole();
    return role == 'DOCTOR';
  }

  public isPatientLoggedIn() {
    if (this.getJwtFromCookie() == null) {
      return false;
    }

    const role = this.getUserRole();
    return role == 'PATIENT';
  }

  public logout() {
    this.sCookie.delete(TOKEN, '/', null, false, 'Strict');
    window.localStorage.removeItem(ID);
    window.localStorage.removeItem(ROLE);
  }
}
