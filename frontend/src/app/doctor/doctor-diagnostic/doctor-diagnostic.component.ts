import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../../services/patient/patient.service';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Observable, combineLatest } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { ItemService } from '../../services/item/item.service';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { DoctorTestResultComponent } from '../doctor-test-result/doctor-test-result.component';
import { DianosticService } from '../../services/diagnostic/dianostic.service';
import { PrescriptionService } from '../../services/prescription/prescription.service';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { MatToolbarModule } from '@angular/material/toolbar';
import { SymptomDialogComponent } from '../dialog/symptom-dialog/symptom-dialog.component';
import { SymptomService } from '../../services/symptom/symptom.service';

@Component({
  selector: 'app-doctor-diagnostic',
  standalone: true,
  imports: [
    CommonModule,
    MatInputModule,
    MatTableModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    FormsModule,
    MatToolbarModule,
    MatDialogModule,
  ],
  templateUrl: './doctor-diagnostic.component.html',
  styleUrl: './doctor-diagnostic.component.css'
})
export class DoctorDiagnosticComponent {

  @ViewChild('patientInfo', {static: false}) patientInfo: ElementRef;
  @ViewChild('diagnosticContainer', {static: false}) diagnosticContainer: ElementRef;
  @ViewChild('medicationContainer', {static: false}) medicationContainer: ElementRef;

  patient: Patient;
  today: Date;

  control = new FormControl();

  symptomColumns: string[] = ['position', 'name'];
  symptomData = new MatTableDataSource<any>();

  testColumns: string[] = ['position', 'name', 'result'];
  testData = new MatTableDataSource<any>(Test);

  diagnostic: FormGroup;

  medication: any[] = [];
  // medication = ['Champs-Élysées', 'Lombard Street', 'Abbey Road', 'Fifth Avenue'];
  filteredMedication: Observable<any>;

  displayedColumns: string[] = [
    'position',
    'name',
    'quantity',
    'unit',
    'dosage',
    'note'
  ];
  dataSource = new MatTableDataSource<any>();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private sPat: PatientService,
    private sMed: ItemService,
    private sDia: DianosticService,
    private sPre: PrescriptionService,
    private sSym: SymptomService,
    private fb: FormBuilder,
    private dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.patient = history.state.patient || this.sPat.getPatientData();
    if (!this.patient) {
      const id = this.route.snapshot.paramMap.get('id');
      // this.patient = DATA[0];
      console.log(id);
      this.getPatientById(id);
      this.getSymtomByPatient(id);
    } else {
      this.getSymtomByPatient(this.patient.id);
    }

    this.diagnostic = this.fb.group({
      conclusion: ['Sốt xuất huyết'],
    });

    const medication$ = this.sMed.findAllMedication().pipe(
      map(data => {
        this.medication = data;
        return data;
      })
    );

    this.filteredMedication = combineLatest([
      medication$,
      this.control.valueChanges.pipe(startWith(''))
    ]).pipe(
      map(([medication$, value]) => this._filter(value || ''))
    );
  }
  
  getAllMedication() {
    this.sMed.findAllMedication().subscribe({
      next: (res) => {
        console.log(res);
        this.medication = res;
      }, 
      error: (err) => {
        console.log(err);
      }
    });
  }
  
  getPatientById(id: string) {
    this.sPat.getById(id).subscribe({
      next: (res) => {
        this.patient = res;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  getSymtomByPatient(id: string) {
    this.sSym.getSymptomByPatient(id).subscribe({
      next: (res) => {
        console.log(res);
        this.symptomData = new MatTableDataSource(res);
      }, 
      error: (err) => {
        console.log(err);
      }
    });
  }

  expandTest(data: any) {
    const dialogRef = this.dialog.open(DoctorTestResultComponent, {data});
  }
  
  private _filter(value: string) {
    const filterValue = this._normalizeValue(value);
    return this.medication.filter(medic => this._normalizeValue(medic.name).includes(filterValue));
  }
  
  private _normalizeValue(value: any): string {
    if (typeof value === 'string') {
      return value.toLowerCase().replace(/\s/g, '');
    }
    return '';
  }

  displayMed(medic?: {id: number, name: string}): string | undefined {
    return medic ? medic.name : undefined;
  }

  addMedication(obj: any) {
    const medic = this.changeToMedic(obj);
    const index = this.dataSource.data.indexOf(medic);
    if (index === -1) {
      this.dataSource.data.push(this.changeToMedic(medic));
      this.dataSource._updateChangeSubscription();
    }
    this.control.setValue('');
    console.log(this.dataSource.data);
  }

  private changeToMedic(obj: any) {
    return {id: obj.id, name: obj.name, quantity: 0, unit: obj.unit, dosage: '', note: ''};
  }

  openAddForm() {
    const data = this.route.snapshot.paramMap.get('id');
    const dialogRef = this.dialog.open(SymptomDialogComponent, {data});
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getSymtomByPatient(data);
        }
      }
    });
  }

  printPrescription() {

    this.savePrescription();
    console.log(this.dataSource.data);

    const printContent = document.createElement('div');

    printContent.innerHTML = `
      <div>${this.patientInfo.nativeElement.innerHTML}</div>
      <div>${this.diagnosticContainer.nativeElement.innerHTML}</div>
      <div>${this.medicationContainer.nativeElement.innerHTML}</div>
    `;

    document.body.appendChild(printContent);

    html2canvas(printContent).then((canvas) => {
      const imgWidth = 208;
      const imgHeight = (canvas.height * imgWidth) / canvas.width;
      const contentDataURL = canvas.toDataURL('image/png');
      const pdf = new jsPDF('p', 'mm', 'a4');
      const position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
      pdf.save('prescription.pdf');

      document.body.removeChild(printContent);
    });
  }

  savePrescription() {
    this.sDia.addNewDiagnostic(this.diagnostic.value).subscribe({
      next: (res) => {
        const listMedication = this.dataSource.data.map(this.sPre.convertToPrescribed);
        // console.log(res.data.id);
        const preObj = {diagnosticId: res.data.id, listMedication};
        this.sPre.addNewPrescription(preObj).subscribe({
          next: (r) => {
            console.log(r);
          },
          error: (e) => {
            console.log(e);
          }
        });
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  formatDate(dateString: string) {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();

    return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
  }
}

export interface Patient {
  id: string,
  name: string,
  age: number,
  birthday: string,
  gender: string,
  healthInsuranceCode: string,
  status: string,
}

const Symptom = [
  {id: 1, name: 'Ho'},
  {id: 2, name: 'Sốt'},
  {id: 3, name: 'Đau đầu'},
];

const Test = [
  {id: 1, name: 'Xét nghiệm máu', result: 'Tiểu cầu giảm mạnh'}
];