import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { PatientService } from '../../services/patient/patient.service';
import { MatIconModule } from '@angular/material/icon';
import { AppointmentService } from '../../services/appointment/appointment.service';
import { DoctorService } from '../../services/doctor/doctor.service';
import { map, switchMap } from 'rxjs/operators';
import { forkJoin } from 'rxjs';
import { DianosticService } from '../../services/diagnostic/dianostic.service';

@Component({
  selector: 'app-medical-information',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './medical-information.component.html',
  styleUrl: './medical-information.component.css'
})
export class MedicalInformationComponent {

  searchQuery: string = '';

  appointmentColumns: string[] = [
    'position', 'patient', 'doctor', 'date', 'hour',
  ];
  appointmentData = new MatTableDataSource<any>();
  validSearch: boolean = false;

  selectedAppointment: any = null;
  diagnosisColumns: string[] = [
    'position', 'conclusion'
  ];
  diagnosisData = new MatTableDataSource<any>();

  constructor(
    private sPat: PatientService,
    private sDoc: DoctorService,
    private sApp: AppointmentService,
    private sDia: DianosticService,
  ) { }

  ngOnInit() {

  }

  onSearch() {
    this.getAppointmentByHealthCode(this.searchQuery);
    this.selectedAppointment = null;
  }

  onRowClick(appointment: any) {
    this.selectedAppointment = appointment;
    this.getDiagnosis(this.selectedAppointment.id);
  }

  getAppointmentByHealthCode(healthCode: string) {
    this.sApp.getByHealthCode(healthCode).pipe(
      switchMap(appoitments => {
        const listAppointment = appoitments.map(appoitment => {
          const patient$ = this.sPat.getById(appoitment.patientId);
          const doctor$ = this.sDoc.getById(appoitment.doctorId);

          return forkJoin([patient$, doctor$]).pipe(
            map(([patient, doctor]) => ({
              id: appoitment.id,
              patient: patient.name,
              doctor: doctor.name,
              date: appoitment.date,
              hour: appoitment.hour,
              description: appoitment.description,
            }))
          );
        });
        return forkJoin(listAppointment);
      })
    ).subscribe(res => {
      this.validSearch = true;
      this.appointmentData = new MatTableDataSource(res);
    });
  }

  getDiagnosis(appointmentId: number) {
    this.sApp.getAllDiagnosisById(appointmentId).subscribe(res => {
      this.diagnosisData = new MatTableDataSource(res);
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
