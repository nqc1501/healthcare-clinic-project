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
      unit: ['', Validators.required],
      price: ['', Validators.required],
      expiryDate: ['', Validators.required],
      manufacturingDate: ['', Validators.required],
      usageInstruction: ['', Validators.required],
      description: ['', Validators.required],
      length: ['', Validators.required],
      width: ['', Validators.required]
    });
  }

  ngOnInit() {
    if (this.data) {
      this.supplyForm.patchValue({
        name: this.data.name,
        unit: this.data.unit,
        price: this.data.price,
        expiryDate: this.formatDate(this.data.expiryDate),
        manufacturingDate: this.formatDate(this.data.manufacturingDate),
        usageInstruction: this.data.usageInstruction,
        description: this.data.description,
        length: this.data.length,
        width: this.data.width,
      });
    }
  }

  onFormSubmit() {
    if (this.supplyForm.valid) {
      if (this.data) {
        const supRequest = this.cvtToSupply(this.supplyForm.value, this.data.id, this.data.roomId);
        this.sItem.updateSupply(supRequest).subscribe({
          next: (res) => {
            console.log(res);
          },
          error: (err) => {
            console.log(err);
          }
        });
      } else {
        const supRequest = this.cvtToSupply(this.supplyForm.value, null, null);
        this.sItem.addNewSupply(supRequest).subscribe({
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

  private cvtToSupply(obj: any, id: number, roomId: number) {
    return {
      id: id,
      name: obj.name,
      unit: obj.unit,
      price: obj.price,
      expiryDate: new Date(obj.expiryDate),
      manufacturingDate: new Date(obj.manufacturingDate),
      usageInstruction: obj.usageInstruction,
      description: obj.description,
      length: obj.length,
      width: obj.width,
      roomId: roomId,
    };
  }

}
