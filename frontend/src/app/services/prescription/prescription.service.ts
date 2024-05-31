import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

const URL = ['http://localhost:9007/api/v1/prescription'];

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  constructor(
    private http: HttpClient,
  ) { }

  addNewPrescription(request: any): Observable<any> {
    return this.http.post(URL + '/add-new-prescription', request);
  }

  convertToPrescribed(object: any) {
    return {medicationId: object.id, quantity: object.id, dosage: object.dosage, note: object.note};
  }
}
