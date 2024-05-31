import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { StorageService } from '../storage/storage.service';

const URL = ['http://localhost:8080/api/v1/auth'];

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(this.sStorage.hasToken());

  constructor(
    private http: HttpClient,
    private sStorage: StorageService
  ) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(URL + '/authenticate', {email, password});
  }

  isSignedIn() {
    return this.loggedIn.asObservable();
  }

  signIn() {
    this.loggedIn.next(true);
  }

  signOut() {
    this.loggedIn.next(false);
  }

  log(message: string) {
    console.log(message);
  }

}
