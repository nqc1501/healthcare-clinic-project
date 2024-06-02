import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL = 'http://localhost:9002/api/v1/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(
    private http: HttpClient
  ) { }

  getAllRooms(): Observable<any> {
    return this.http.get<[]>(URL);
  }

  addNewRoom(request: any) {
    return this.http.post(URL, request);
  }
}
