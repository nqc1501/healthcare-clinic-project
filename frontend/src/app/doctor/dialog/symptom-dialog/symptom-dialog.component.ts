import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SymptomService } from '../../../services/symptom/symptom.service';

@Component({
  selector: 'app-symptom-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './symptom-dialog.component.html',
  styleUrl: './symptom-dialog.component.css'
})
export class SymptomDialogComponent {

  symptomForm: FormGroup;

  constructor(
    private sSym: SymptomService,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<SymptomDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { 
    this.symptomForm = this.fb.group({
      symptoms: this.fb.array([this.createSymptom()])
    });
  }

  ngOnInit() {
  }

  createSymptom() {
    return this.fb.group({
      name: ['', Validators.required]
    });
  }

  get symptoms() {
    return this.symptomForm.get('symptoms') as FormArray;
  }

  addSymptom() {
    this.symptoms.push(this.createSymptom());
  }

  removeSymptom(index: number) {
    this.symptoms.removeAt(index);
  }

  onFormSubmit() {
    if (this.symptomForm.valid) {
      const listSymptom = this.symptomForm.value.symptoms as Symptom[];
      this.sSym.addSymptom(this.data, listSymptom).subscribe({
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

interface Symptom {
  name: string;
}
