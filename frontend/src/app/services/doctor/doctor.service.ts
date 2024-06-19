import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../auth/services/storage/storage.service';
import { Observable } from 'rxjs';

const URL = 'http://localhost:8080/api/v1/doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllDoctors(): Observable<any> {
    return this.http.get<[]>(URL);
  }

  getDoctorBySpecialty(specialtyId: number) {
    return this.http.get<[]>(URL + `/specialty?specialtyId=${specialtyId}`);
  }

  getById(id: string): Observable<any> {
    return this.http.get(URL + `/${id}`, {withCredentials: true});
  }

  registerShift(id: string, request: any) {
    return this.http.post(URL + `/${id}/register-shift`, request, {withCredentials: true});
  }

  uploadImage(image: any, id: number) {
    return this.http.post(URL + `/${id}/upload-image`, image, {withCredentials: true});
  }

  updateDoctor(doctor: any) {
    return this.http.put(URL, doctor, {withCredentials: true});
  }

  deleteDoctor(id: number) {
    return this.http.delete(URL + `/${id}`, {withCredentials: true});
  }
}
