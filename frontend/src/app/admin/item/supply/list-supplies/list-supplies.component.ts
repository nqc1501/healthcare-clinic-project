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
  displayedColumns: string[] = ['position', 'name', 'quantity', 'unit', 'length', 'width', 'edit', 'delete'];
  dataSource = new MatTableDataSource<any>;

  listAllSupplies: any[] = [];

  constructor(
    private sItem: ItemService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);
  }

  getAllSupplies() {
    const listSupplies: {
      id: number, 
      name: string, 
      quantity: number, 
      unit: string, 
      length: number, 
      width: number
    }[] = [];
    
    this.sItem.findAllSupplies().subscribe({
      next: (res) => {
        // for (const r of res) {
        //   listSupplies.push({
        //     id: r.id,
        //     name: r.name,
        //     quantity: r.quantity,
        //     unit: r.unit,
        //     length: r.length,
        //     width: r.width
        //   });
        // }
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

  openDeleteForm() {
    const result = window.confirm('Xóa bản ghi này ?');
    if (result) {
      this.snackbar.open('Bản ghi đã được xóa thành công', 'OK', { duration: 3000 });
    }
  }

}

export interface PeriodicElement {
  name: string;
  position: number;
  quantity: number;
  unit: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', quantity: 1.0079, unit: 'H'},
  {position: 2, name: 'Helium', quantity: 4.0026, unit: 'He'},
  {position: 3, name: 'Lithium', quantity: 6.941, unit: 'Li'},
  {position: 4, name: 'Beryllium', quantity: 9.0122, unit: 'Be'},
  {position: 5, name: 'Boron', quantity: 10.811, unit: 'B'},
  {position: 6, name: 'Carbon', quantity: 12.0107, unit: 'C'},
  {position: 7, name: 'Nitrogen', quantity: 14.0067, unit: 'N'},
  {position: 8, name: 'Oxygen', quantity: 15.9994, unit: 'O'},
  {position: 9, name: 'Fluorine', quantity: 18.9984, unit: 'F'},
  {position: 10, name: 'Neon', quantity: 20.1797, unit: 'Ne'},
];
