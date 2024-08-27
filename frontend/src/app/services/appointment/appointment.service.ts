import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllAppointment(): Observable<any[]> {
    return this.http.get<[]>(URL, {withCredentials: true});
  }

  getByHealthCode(healthCode: string): Observable<any[]> {
    return this.http.get<[]>(URL + `/by-health-code?healthCode=${healthCode}`, {withCredentials: true});
  }

  getAllDiagnosisById(appointmentId: number): Observable<any[]> {
    return this.http.get<[]>(URL + `/${appointmentId}/diagnosis`);
  }

  addAppointment(patientId: string, appointment: any) {
    return this.http.post(URL + `/by-patient?patientId=${patientId}`, appointment);
  }
}
