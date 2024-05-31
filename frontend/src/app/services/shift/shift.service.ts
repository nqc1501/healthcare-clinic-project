import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL = ['http://localhost:9005/api/v1/shift'];

@Injectable({
  providedIn: 'root'
})
export class ShiftService {

  constructor(
    private http: HttpClient
  ) { }

  getAllShift(): Observable<any> {
    return this.http.get<[]>(URL + '/find-all-shift');
  }

  getAllShiftByUser(userId: string): Observable<any> {
    return this.http.get<[]>(URL + '/find-by-user-id/' + userId);
  }

  addNewShift(request: any) {
    return this.http.post(URL + '/add-new-shift', request);
  }

  registerByUser(request: any): Observable<any> {
    return this.http.post(URL + '/register-shift', request);
  }

  updateShift(request: any) {
    return this.http.put(URL + '/update-shift', request);
  }
}
