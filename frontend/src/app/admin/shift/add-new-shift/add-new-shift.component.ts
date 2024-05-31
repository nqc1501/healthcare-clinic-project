import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { ShiftService } from '../../../services/shift/shift.service';

@Component({
  selector: 'app-add-new-shift',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    MatButtonModule
  ],
  templateUrl: './add-new-shift.component.html',
  styleUrl: './add-new-shift.component.css'
})
export class AddNewShiftComponent {

  shiftForm: FormGroup;

  constructor(
    private sShift: ShiftService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any | null = null
  ) { 
    this.shiftForm = this.fb.group({
      name: ['', Validators.required],
      date: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      quantityBookOn: ['', Validators.required],
      quantityBooked: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.shiftForm.patchValue(this.data);
  }

  onFormSubmit() {
    if (this.shiftForm.valid) {
      if (this.data) {
        this.sShift.updateShift(this.shiftForm.value).subscribe({
          next: (res) => {
            console.log(res);
          },
          error: (err) => {
            console.log(err);
          }
        });
      } else {
        this.sShift.addNewShift(this.shiftForm.value).subscribe({
          next: (res) => {
            console.log(res);
          },
          error: (err) => {
            console.log(err);
          }
        });
      }
    }
  }
}
