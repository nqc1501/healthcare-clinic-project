import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { AppointmentService } from '../../../services/appointment/appointment.service';
import { PatientService } from '../../../services/patient/patient.service';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-list-appointment',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
  ],
  templateUrl: './list-appointment.component.html',
  styleUrl: './list-appointment.component.css'
})
export class ListAppointmentComponent {

  displayedColumns: string[] = [
    'position', 'patient', 'doctor', 'date', 'hour'
  ];
  dataSource = new MatTableDataSource<any>();

  constructor(
    private sApp: AppointmentService,
    private sPat: PatientService,
    private sDoc: DoctorService,
  ) { }

  ngOnInit() {
    this.getAllAppointment();    
  }

  getAllAppointment() {
    this.sApp.getAllAppointment().pipe(
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
    ).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
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
