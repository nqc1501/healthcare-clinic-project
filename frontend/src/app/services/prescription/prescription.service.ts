import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { StorageService } from '../../auth/services/storage/storage.service';

const URL = 'http://localhost:8080/api/v1/prescription';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  constructor(
    private http: HttpClient,
    private storage: StorageService,
  ) { }

  addNewPrescription(request: any): Observable<any> {
    return this.http.post(URL, request, {withCredentials: true});
  }

  getByDiagnosis(diagnosisId: number) {
    return this.http.get<[]>(URL + `/find-by-diagnosis/${diagnosisId}`, {withCredentials: true})
  }

  convertToPrescribed(object: any) {
    return {medicationId: object.id, quantity: object.id, dosage: object.dosage, note: object.note};
  }
}
