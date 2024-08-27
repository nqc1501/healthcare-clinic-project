import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = ['http://localhost:8080/api/v1/schedule'];

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor(
    private http: HttpClient,
    private storage: StorageService,
  ) { }

  getAllByDoctor(doctorId: string): Observable<any> {
    return this.http.get<[]>(URL + `/doctor?doctorId=${doctorId}`, {withCredentials: true});
  }

  addRoomToSchedule(id: number, request: any) {
    return this.http.post(URL + `/${id}/add-room`, request, {withCredentials: true});
  }
}
