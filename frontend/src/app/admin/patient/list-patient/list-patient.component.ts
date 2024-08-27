import { Component } from '@angular/core';
import { PatientService } from '../../../services/patient/patient.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-list-patient',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
  ],
  templateUrl: './list-patient.component.html',
  styleUrl: './list-patient.component.css'
})
export class ListPatientComponent {

  displayedColumns: string[] = [
    'position', 'name', 'healthInsuranceCode', 'gender', 'age', 'status', 'action'
  ];

  dataSource = new MatTableDataSource<any>();
  listPatient: any[];

  constructor(
    private sPatient: PatientService,
    private route: ActivatedRoute,
    private snackbar: MatSnackBar,
  ) { }

  ngOnInit() {
    this.getListPatient();
  }

  getListPatient() {
    this.route.paramMap.subscribe(params => {
      const status = params.get('status');
      if (status === 'waiting') {
        this.getPatientByStatus('Đang chờ');
      } else {
        this.getPatientByStatus('Đã khám');
      }
    });
  }

  getPatientByStatus(status: string) {
    this.sPatient.getAllByStatus(status).subscribe({
      next: (res) => {
        console.log(res);
        this.dataSource = new MatTableDataSource(res);
      }, 
      error: (err) => {
        console.log(err);
      }
    });
  }

  openEditPatient(patient: any) {
  }

  openDeletePatient(id: string) {
    const result = window.confirm('Xóa bệnh nhân này ?');
    if (result) {
      this.sPatient.deletePatient(id).subscribe({
        next: (res) => {
          this.getListPatient();
          this.snackbar.open('Xóa bệnh nhân thành công', 'OK', {duration: 3000});
        }, 
        error: (err) => {
          console.log(err);
        }
      })
    }
  }

}
