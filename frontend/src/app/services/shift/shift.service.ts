import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/shift';

@Injectable({
  providedIn: 'root'
})
export class ShiftService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllShift(): Observable<any> {
    return this.http.get<[]>(URL, {withCredentials: true});
  }

  getById(id: number) {
    return this.http.get(URL + `/${id}`, {withCredentials: true});
  }

  addNewShift(request: any) {
    return this.http.post(URL, request, {withCredentials: true});
  }

  registerByUser(id: number, request: any): Observable<any> {
    return this.http.post(URL + `/${id}/register-shift`, request, {withCredentials: true});
  }

  updateShift(request: any) {
    return this.http.put(URL, request, {withCredentials: true});
  }

  deleteShift(id: number) {
    return this.http.delete(URL + `/${id}`, {withCredentials: true});
  }
}
