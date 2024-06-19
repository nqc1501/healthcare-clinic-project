import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { ItemService } from '../../../../services/item/item.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-add-new-medication',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './add-new-medication.component.html',
  styleUrl: './add-new-medication.component.css'
})
export class AddNewMedicationComponent {

  medicationForm: FormGroup;

  constructor(
    private sItem: ItemService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any | null = null
  ) { 
    this.medicationForm = this.fb.group({
      name: ['', Validators.required],
      unit: ['', Validators.required],
      price: ['', Validators.required],
      expiryDate: ['', Validators.required],
      manufacturingDate: ['', Validators.required],
      usageInstruction: ['', Validators.required],
      description: [''],
      composition: ['', Validators.required],
      userPersona: ['', Validators.required],
      caution: ['', Validators.required],
      storage: ['', Validators.required],
    });
  }

  ngOnInit() {
    if (this.data) {
      this.medicationForm.patchValue({
        name: this.data.name,
        unit: this.data.unit,
        price: this.data.price,
        expiryDate: this.formatDate(this.data.expiryDate),
        manufacturingDate: this.formatDate(this.data.manufacturingDate),
        usageInstruction: this.data.usageInstruction,
        description: this.data.description,
        composition: this.data.composition,
        userPersona: this.data.userPersona,
        caution: this.data.caution,
        storage: this.data.storage,
      });
    }
  }

  onFormSubmit() {
    if (this.medicationForm.valid) {
      if (this.data) {
        const medRequest = this.cvtToMedication(this.medicationForm.value, this.data.id);
        this.sItem.updateMedication(medRequest).subscribe({
          next: (res) => {
            console.log(res);
          },
          error: (err) => {
            console.log(err);
          }
        });
      } else {
        const medRequest = this.cvtToMedication(this.medicationForm.value, null);
        this.sItem.addNewMedication(medRequest).subscribe({
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

  formatDate(dateString: string) {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();

    return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
  }

  private cvtToMedication(obj: any, id: number) {
    return {
      id: id,
      name: obj.name,
      unit: obj.unit,
      price: obj.price,
      expiryDate: new Date(obj.expiryDate),
      manufacturingDate: new Date(obj.manufacturingDate),
      usageInstruction: obj.usageInstruction,
      description: obj.description,
      composition: obj.composition,
      userPersona: obj.userPersona,
      caution: obj.caution,
      storage: obj.storage,
    };
  }
}
