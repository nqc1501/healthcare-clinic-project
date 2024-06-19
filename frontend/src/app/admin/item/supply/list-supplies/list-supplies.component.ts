import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { AddNewSupplyComponent } from '../add-new-supply/add-new-supply.component';
import { ItemService } from '../../../../services/item/item.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { error } from 'console';

@Component({
  selector: 'app-list-supplies',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  templateUrl: './list-supplies.component.html',
  styleUrl: './list-supplies.component.css'
})
export class ListSuppliesComponent {

  // displayedColumns: string[] = [
  //   'position',
  //   'name', 
  //   'quantity',
  //   'unit', 
  //   'price',
  //   'expiryDate',
  //   'manufacturingDate',
  //   'usage',
  //   'description',
  //   'length',
  //   'width'
  // ];
  displayedColumns: string[] = ['position', 'name', 'unit', 'length', 'width', 'action'];
  dataSource = new MatTableDataSource<any>;

  listAllSupplies: any[] = [];

  constructor(
    private sItem: ItemService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getAllSupplies();
  }

  getAllSupplies() {    
    this.sItem.findAllSupplies().subscribe({
      next: (res) => {
        this.dataSource = new MatTableDataSource(res);
      }, 
      error: (err) => {
        console.log(err);
      }
    });
  }

  openAddForm() {
    const dialogRef = this.dialog.open(AddNewSupplyComponent);
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllSupplies();
        }
      }
    });
  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(AddNewSupplyComponent, {data});
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllSupplies();
        }
      }
    });
  }

  openDeleteForm(id: number) {
    const result = window.confirm('Xóa bản ghi này ?');
    if (result) {
      this.sItem.deleteSupply(id).subscribe({
        next: (res) => {
          this.getAllSupplies();
          this.snackbar.open('Bản ghi đã được xóa thành công', 'OK', { duration: 3000 });
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }

}
