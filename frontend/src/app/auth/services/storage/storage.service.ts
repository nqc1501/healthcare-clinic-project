import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

const TOKEN = 'c_token';
const ID = 'c_id';
const ROLE = 'c_role';
const LOGGED_IN_KEY = 'loggedIn';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor(
    private sCookie: CookieService,
  ) { }

  saveLoggedIn(loggedIn: boolean) {
    localStorage.setItem(LOGGED_IN_KEY, loggedIn ? 'true' : 'false');
  }

  isLoggedIn(): boolean {
    const loggedIn = localStorage.getItem(LOGGED_IN_KEY);
    return loggedIn === 'true';
  }

  public saveId(id: number) {
    window.localStorage.removeItem(ID);
    window.localStorage.setItem(ID, JSON.stringify(id));
  }

  public saveRole(role: string) {
    window.localStorage.removeItem(ROLE);
    window.localStorage.setItem(ROLE, JSON.stringify(role));
  }

  public getUserId() {
    return JSON.parse(localStorage.getItem(ID));
  }

  public getRole() {
    return JSON.parse(localStorage.getItem(ROLE));
  }

  public logout() {
    window.localStorage.removeItem(ID);
    window.localStorage.removeItem(ROLE);
  }
}
