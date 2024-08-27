import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private patientData: any;

  constructor(
    private http: HttpClient,
  ) { }

  setPatientData(data: any) {
    this.patientData = data;
  }

  getPatientData() {
    return this.patientData;
  }

  getAllPatient() {
    return this.http.get<[]>(URL, {withCredentials: true});
  }

  getAllByStatus(status: string) {
    return this.http.get<[]>(URL + `/by-status/${status}`, {withCredentials: true});
  }

  getById(id: string): Observable<any> {
    return this.http.get( URL + `/${id}`, {withCredentials: true});
  }

  addPatient(patient: any): Observable<any> {
    return this.http.post(URL, patient);
  }

  deletePatient(id: string) {
    return this.http.delete(URL + `/${id}`, {withCredentials: true});
  }
}
