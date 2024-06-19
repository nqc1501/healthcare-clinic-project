import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = ['http://localhost:8080/api/v1'];

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(
    private http: HttpClient,
  ) { }

  // supply
  findAllSupplies(): Observable<any> {
    return this.http.get<[]>(URL + '/supply', {withCredentials: true});
  }

  addNewSupply(supply: any) {
    return this.http.post(URL + '/supply', supply, {withCredentials: true});
  }

  updateSupply(supply: any) {
    return this.http.put(URL + '/supply', supply, {withCredentials: true});
  }
 
  deleteSupply(id: number) {
    return this.http.delete(URL + `/supply/${id}`, {withCredentials: true});
  }

  // medication
  findAllMedication() {
    return this.http.get<[]>(URL + '/medication', {withCredentials: true});
  }

  addNewMedication(medication: any) {
    return this.http.post(URL + '/medication', medication, {withCredentials: true});
  }

  updateMedication(medication: any) {
    return this.http.put(URL + '/medication', medication, {withCredentials: true});
  }

  deleteMedication(id: number) {
    return this.http.delete(URL + `/medication/${id}`, {withCredentials: true});
  }
}
