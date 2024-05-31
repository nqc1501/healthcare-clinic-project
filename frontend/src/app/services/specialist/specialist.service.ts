import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL = ['http://localhost:9002/api/v1/service-management'];

@Injectable({
  providedIn: 'root'
})
export class SpecialistService {

  constructor(
    private http: HttpClient
  ) { }

  // specialist
  findAllSpecialist() {
    return this.http.get<[]>(URL + '/find-all-specialist');
  }

  findSpecialistByServiceId(id: number): Observable<any> {
    return this.http.get(URL + '/find-specialist-by-service-id/' + id);
  }

  updateSpecialist(request: any) {
    return this.http.put(URL + '/update-specialist', request);
  }

  //service
  findAllServices(): Observable<any> {
    return this.http.get<[]>(URL + '/find-all-services');
  }

  findAllBySpecialist(specialist: string) {
    return this.http.get<[]>(URL + '/find-all-by-specialist/' + specialist);
  }

  findServiceById(id: number): Observable<any> {
    return this.http.get(URL + '/find-service-by-id' + id);
  }

  updateService(request: any) {
    return this.http.put(URL + '/update-service', request);
  }

  deleteService(id: number) {
    return this.http.delete(URL + '/delete-service-by-id/' + id);
  }
}
