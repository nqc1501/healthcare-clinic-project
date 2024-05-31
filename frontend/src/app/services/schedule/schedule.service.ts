import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const URL = ['http://localhost:9005/api/v1/schedule'];

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor(
    private http: HttpClient
  ) { }

  addNewSchedule(listSchedule: any[]) {
    return this.http.post(URL + '/add-new-schedule', listSchedule);
  }

}
