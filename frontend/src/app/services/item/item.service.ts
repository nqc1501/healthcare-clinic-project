import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = ['http://localhost:9003/api/v1/item'];

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  // supply
  findAllSupplies(): Observable<any> {
    return this.http.get<[]>(URL + '/supply/find-all-supplies');
  }

  addNewSupply(request: any) {
    return this.http.post(URL + '/supply/add-new-supply', request);
  }

  updateSupply(request: any) {
    return this.http.put(URL + '/supply/update-supply', request);
  }

  // medication
  findAllMedication() {
    return this.http.get<[]>(URL + '/medication/find-all-medication');
  }

  addNewMedication(request: any) {
    return this.http.post(URL + '/medication/add-new-medication', request);
  }

  updateMedication(request: any) {
    return this.http.put(URL + '/medication/update-medication', request);
  }

  // authenticate
  createAuthorizationHeader(): HttpHeaders {
    let authHeader : HttpHeaders = new HttpHeaders();
    return authHeader.set(
      "Authorization", "Bearer " + this.storage.getJwtFromCookie()
    );
  }
}
