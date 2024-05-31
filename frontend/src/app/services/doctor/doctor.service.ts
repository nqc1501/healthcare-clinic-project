import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../auth/services/storage/storage.service';
import { Observable } from 'rxjs';

const URL = ['http://localhost:9001/api/v1/doctor'];

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  getAllDoctors() {
    return this.http.get<[]>(URL + '/find-all-doctors', {
      headers: this.createAuthorizationHeader()
    });
  }

  addNewDoctor(request: any): Observable<any> {
    return this.http.post(URL + '/add-new-doctor', request, {
      headers: this.createAuthorizationHeader()
    });
  }

  registerShift(request: any) {
    return this.http.post(URL + '/register-shift', request);
  }

  // authenticate
  createAuthorizationHeader(): HttpHeaders {
    let authHeader : HttpHeaders = new HttpHeaders();
    return authHeader.set(
      "Authorization", "Bearer " + this.storage.getJwtFromCookie()
    );
  }

}
