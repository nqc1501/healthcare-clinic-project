import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/specialty';

@Injectable({
  providedIn: 'root'
})
export class SpecialtyService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllSpecialty() {
    return this.http.get<[]>(URL);
  }

  getById(id: any): Observable<any> {
    return this.http.get(URL + `/${id}`, {withCredentials: true});
  }

  getAllRoom(specialtyId: number): Observable<any> {
    return this.http.get<[]>(URL + `/${specialtyId}/list-room`, {withCredentials: true});
  }

  getSchedulesBySpecialtyId(specialtyId: number): Observable<any> {
    return this.http.get<[]>(URL + `/${specialtyId}/schedule-registry`, {withCredentials: true});
  }
}
