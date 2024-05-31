import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ItemService } from '../../../../services/item/item.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AddNewMedicationComponent } from '../add-new-medication/add-new-medication.component';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-list-medication',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatSnackBarModule,
    MatPaginatorModule
  ],
  templateUrl: './list-medication.component.html',
  styleUrl: './list-medication.component.css'
})
export class ListMedicationComponent {

  displayedColumns: string[] = ['position', 'name', 'quantity', 'unit', 'usage', 'edit', 'delete'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private sItem: ItemService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);
  }

  applyFilter(event: Event) {

    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
      this.dataSource.paginator = this.paginator;
    }

  }

  getAllMedication() {
    this.sItem.findAllMedication().subscribe({
      next: (res) => {
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  openAddForm() {
    const dialogRef = this.dialog.open(AddNewMedicationComponent);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllMedication();
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(AddNewMedicationComponent, data);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllMedication();
        }
      },
      error: (err) => {
        console.log(err);
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
