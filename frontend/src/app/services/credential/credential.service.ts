import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../auth/services/storage/storage.service';
import { Observable } from 'rxjs';

const URL = 'http://localhost:8080/api/v1/account';

@Injectable({
  providedIn: 'root'
})
export class CredentialService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllCredentials() {
    return this.http.get<[]>(URL, {withCredentials: true});
  }

  getCredentialById(id: number) {
    return this.http.get(URL + `/${id}`, {withCredentials: true});
  }
}
