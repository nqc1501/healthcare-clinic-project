import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-doctor-test-result',
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    MatTableModule
  ],
  templateUrl: './doctor-test-result.component.html',
  styleUrl: './doctor-test-result.component.css'
})
export class DoctorTestResultComponent {

  medication = DATA;

  constructor(
    // private fb: FormBuilder,
    // private dialogRef: MatDialogRef<DoctorTestResultComponent>,
    // @Inject(MAT_DIALOG_DATA) public data: any | null = null,
  ) { }

  ngOnInit() {

  }

}

const DATA = [
  {name: 'Hello', unit: 'Viên', quantity: 8, dosage: 'Uống, Sáng 2 Viên, Tối 2 Viên'},
];
