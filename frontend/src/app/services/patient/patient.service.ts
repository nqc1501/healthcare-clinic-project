import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL = 'http://localhost:9001/api/v1/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private patientData: any;

  constructor(
    private http: HttpClient
  ) { }

  setPatientData(data: any) {
    this.patientData = data;
  }

  getPatientData() {
    return this.patientData;
  }

  getAllPatient() {
    return this.http.get<[]>(URL);
  }

  getById(id: string): Observable<any> {
    return this.http.get( URL + '/' + id);
  }

}
