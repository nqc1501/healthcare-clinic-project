import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Router } from '@angular/router';
import { PatientService } from '../../services/patient/patient.service';

@Component({
  selector: 'app-doctor-list-patient',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule
  ],
  templateUrl: './doctor-list-patient.component.html',
  styleUrl: './doctor-list-patient.component.css'
})
export class DoctorListPatientComponent {

  displayedColumns = [
    'position',
    'name',
    'age',
    'birthday',
    'gender',
    'healthInsuranceCode',
    'status',
  ];
  dataSource = new MatTableDataSource<any>();
  
  constructor(
    private sPat: PatientService,
    private router: Router
  ) { }

  ngOnInit() {
    // this.dataSource = new MatTableDataSource(DATA);
    this.getAllPatient();
  }

  getAllPatient() {
    this.sPat.getAllPatient().subscribe({
      next: (res) => {
        this.dataSource = new MatTableDataSource(res);
      }
    });
  }

  onClick(object: any) {
    this.sPat.setPatientData(object);
    this.router.navigate(['/doctor/diagnostic/', object.id], {state: {object}});
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
