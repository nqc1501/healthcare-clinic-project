import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ShiftService } from '../../../services/shift/shift.service';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-shift-detail',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'vi-VN' },
  ],
  templateUrl: './shift-detail.component.html',
  styleUrl: './shift-detail.component.css'
})
export class ShiftDetailComponent {

  shiftForm: FormGroup;

  constructor(
    private sShift: ShiftService,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ShiftDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any | null = null,
  ) { 
    this.shiftForm = this.fb.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      dayOfWeek: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      quantity: ['', Validators.required],
      quantityRegistered: ['', Validators.required],
      isSelected: ['', Validators.required],
    });
  }

  ngOnInit() {
    if (this.data) {
      this.shiftForm.patchValue({
        startDate: this.formatDate(this.data.startDate),
        endDate: this.formatDate(this.data.endDate),
        dayOfWeek: this.data.dayOfWeek,
        startTime: this.data.startTime,
        endTime: this.data.endTime,
        quantity: this.data.quantity,
        quantityRegistered: this.data.quantityRegistered,
        isSelected: this.data.isSelected,
      });
    }
  }

  onFormSubmit() {
    if (this.shiftForm.valid) {
      const shift = this.cvtToShiftModel(this.shiftForm.value, this.data.id);
      if (this.data) {
        this.sShift.updateShift(shift).subscribe({
          next: (res) => {
            console.log(res);
            this.dialogRef.close(true);
          },
          error: (err) => {
            console.log(err);
          }
        });
      } else {
        this.sShift.addNewShift(shift).subscribe({
          next: (res) => {
            console.log(res);
            this.dialogRef.close(true);
          },
          error: (err) => {
            console.log(err);
          }
        });
      }
    }
  }

  private formatDate(dateString: string) {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();

    return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
  }

  private cvtToShiftModel(object: any, id: number) {
    return {
      id: id,
      startDate: new Date(this.parseDate(object.startDate)),
      endDate: new Date(this.parseDate(object.endDate)),
      dayOfWeek: object.dayOfWeek,
      startTime: object.startTime, 
      endTime: object.endTime,
      quantity: object.quantity,
      quantityRegistered: object.quantityRegistered,
      isSelected: object.isSelected,
    }
  }

  private parseDate(dateString: string) {
    const [day, month, year] = dateString.split('/');
    return `${month}/${day}/${year}`;
  }
}
