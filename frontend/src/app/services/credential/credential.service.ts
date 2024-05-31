import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../auth/services/storage/storage.service';
import { Observable } from 'rxjs';

const URL = ['http://localhost:9000/api/v1/account'];

@Injectable({
  providedIn: 'root'
})
export class CredentialService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  getAllCredentials() {
    return this.http.get<any>('/find-all-accounts');
  }

  getCredentialById(id: number) {
    return this.http.get('/find-account-by-id/' + id);
  }

  getCredentialByEmail(email: string): Observable<any> {
    return this.http.get(URL + '/find-account-by-email/' + email);
  }

  addDoctorAccount(request: any) {
    return this.http.post(URL + '/add-doctor-account', request);
  }

  // authenticate
  createAuthorizationHeader(): HttpHeaders {
    let authHeader : HttpHeaders = new HttpHeaders();
    return authHeader.set(
      "Authorization", "Bearer " + this.storage.getJwtFromCookie()
    );
  }

}
