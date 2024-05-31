import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { SpecialistService } from '../../../../services/specialist/specialist.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-new-service',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  templateUrl: './add-new-service.component.html',
  styleUrl: './add-new-service.component.css'
})
export class AddNewServiceComponent {

  form: FormGroup;
  isEdit: boolean = false;

  constructor(
    private sSpecialist: SpecialistService,
    private fb: FormBuilder,
    private snackbar: MatSnackBar,
    private dialogRef: MatDialogRef<AddNewServiceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any | null = null
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required]
    });
  }

  ngOnInit() {
    if (this.data.isAdd || this.data === null) {
      this.isEdit = false;
    } else {
      this.isEdit = true;
      this.form.patchValue(this.data);
    }
  }

  onFormSubmit() {
    // Process form submission
    if (this.form.valid) {
      if (this.isEdit) {
        const formData = {
          id: this.data.id,
          name: this.form.value.name
        }

        this.sSpecialist.updateService(formData).subscribe({
          next: (res) => {
            console.log(res);
            this.snackbar.open('Sửa thông tin dịch vụ thành công', 'OK', { duration: 3000 });
            this.dialogRef.close(this.form.value);
          },
          error: (err) => {
            console.log(err);
          }
        });
      } else {
        const formData = {
          id: this.data.id,
          name: this.data.name,
          listServices: [this.form.value]
        }

        this.sSpecialist.updateSpecialist(formData).subscribe({
          next: (res) => {
            console.log(res);
            this.snackbar.open('Thêm dịch vụ thành công.', 'OK', { duration: 3000 });
            this.dialogRef.close(this.form.value);
          }
        });
      }
    }
  }

}
