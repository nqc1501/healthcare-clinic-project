import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/symptom';

@Injectable({
  providedIn: 'root'
})
export class SymptomService {

  constructor(
    private http: HttpClient,
    private storage: StorageService,
  ) { }

  getSymptomByPatient(id: string): Observable<any[]> {
    return this.http.get<[]>(URL + `/patient?patientId=${id}`, {withCredentials: true});
  }

  addSymptom(id: string, listSymptom: any[]) {
    return this.http.post(URL + `/patient?patientId=${id}/add-symptom`, listSymptom, {withCredentials: true});
  }
}
