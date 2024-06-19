import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllRooms(): Observable<any[]> {
    return this.http.get<[]>(URL, {withCredentials: true});
  }

  getById(id: number): Observable<any> {
    return this.http.get(URL + `/${id}`, {withCredentials: true});
  }

  addNewRoom(room: any) {
    return this.http.post(URL, room, {withCredentials: true});
  }

  updateRoom(room: any) {
    return this.http.put(URL, room, {withCredentials: true});
  }

  deleteRoom(id: number) {
    return this.http.delete(URL + `/${id}`, {withCredentials: true});
  }
}
