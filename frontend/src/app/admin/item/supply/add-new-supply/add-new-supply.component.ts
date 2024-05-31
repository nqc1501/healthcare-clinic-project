import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { ItemService } from '../../../../services/item/item.service';

@Component({
  selector: 'app-add-new-supply',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    MatButtonModule
  ],
  templateUrl: './add-new-supply.component.html',
  styleUrl: './add-new-supply.component.css'
})
export class AddNewSupplyComponent {

  supplyForm: FormGroup;

  constructor(
    private sItem: ItemService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any | null = null
  ) { 
    this.supplyForm = this.fb.group({
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
    this.supplyForm.patchValue(this.data);
  }

  onFormSubmit() {
    if (this.supplyForm.valid) {
      if (this.data) {
        this.sItem.updateSupply(this.supplyForm.value).subscribe({
          next: (res) => {
            console.log(res);
          },
          error: (err) => {
            console.log(err);
          }
        });
      } else {
        this.sItem.addNewSupply(this.supplyForm.value).subscribe({
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
