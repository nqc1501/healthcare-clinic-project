import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/floor';

@Injectable({
  providedIn: 'root'
})
export class FloorService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllFloor(): Observable<any[]> {
    return this.http.get<[]>(URL, {withCredentials: true});
  }

  getById(id: number): Observable<any> {
    return this.http.get(URL + `/${id}`, {withCredentials: true});
  }
}
