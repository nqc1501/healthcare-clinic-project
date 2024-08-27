import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { StorageService } from '../storage/storage.service';
import { map, tap } from 'rxjs/operators';

const URL = 'http://localhost:8080/api/v1/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(
    private http: HttpClient,
    private sStorage: StorageService
  ) {
    const isLoggedIn = this.sStorage.isLoggedIn();
    if (isLoggedIn) {
      this.loggedIn.next(true);
    }
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(URL + '/authenticate', {email, password}, {withCredentials: true}).pipe(
      tap(() => this.signIn())
    );
  }

  isSignedIn() {
    return this.loggedIn.asObservable();
  }

  signIn() {
    this.loggedIn.next(true);
    this.sStorage.saveLoggedIn(true);
  }

  signOut() {
    this.loggedIn.next(false);
    this.sStorage.saveLoggedIn(false);
  }

  checkLogin() {
    return this.http.get<any>(URL + '/check-login', {withCredentials: true});
  }

  logout() {
    return this.http.post(URL + '/logout', null, {withCredentials: true}).pipe(
      map(() => {
        this.loggedIn.next(false);
        this.sStorage.saveLoggedIn(false);
      })
    );
  }

  log(message: string) {
    console.log(message);
  }

}
