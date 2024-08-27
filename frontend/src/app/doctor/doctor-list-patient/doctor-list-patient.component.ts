import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
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
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const status = params.get('status');
      if (status === 'appointment-waiting') {
        this.getPatientByStatus('Đang chờ');
      } else {
        this.getPatientByStatus('Đã khám');
      }
    });
  }

  getPatientByStatus(status: string) {
    this.sPat.getAllByStatus(status).subscribe({
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
