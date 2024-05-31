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
      quantity: ['', Validators.required],
      unit: ['', Validators.required],
      price: ['', Validators.required],
      expiryDate: ['', Validators.required],
      manufacturingDate: ['', Validators.required],
      usage: ['', Validators.required],
      description: ['', Validators.required],
      length: ['', Validators.required],
      width: ['', Validators.required]
    });
  }

  ngOnInit() {

  }

  onFormSubmit() {

  }
}
