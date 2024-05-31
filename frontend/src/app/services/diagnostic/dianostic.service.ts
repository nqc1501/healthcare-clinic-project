import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL = ['http://localhost:9007/api/v1/diagnostic'];

@Injectable({
  providedIn: 'root'
})
export class DianosticService {

  constructor(
    private http: HttpClient,
  ) { }

  addNewDiagnostic(request: any): Observable<any> {
    return this.http.post(URL + '/add-new-diagnostic', request);
  }
}
