import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/diagnosis';

@Injectable({
  providedIn: 'root'
})
export class DianosticService {

  constructor(
    private http: HttpClient,
  ) { }

  getDiagnosisByPatient(patientId: string, request: any) {
    return this.http.get(URL + `?patientId=${patientId}`, request);
  }

  addNewDiagnostic(request: any): Observable<any> {
    return this.http.post(URL, request, {withCredentials: true});
  }
}
