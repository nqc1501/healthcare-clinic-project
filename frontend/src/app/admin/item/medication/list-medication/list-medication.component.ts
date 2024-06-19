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

  displayedColumns: string[] = ['position', 'name', 'unit', 'composition', 'action'];
  dataSource = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private sItem: ItemService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getAllMedication();
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
    const dialogRef = this.dialog.open(AddNewMedicationComponent, {data});

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

  openDeleteForm(id: number) {
    const result = window.confirm('Xóa bản ghi này ?');
    if (result) {
      this.sItem.deleteMedication(id).subscribe({
        next: (res) => {
          this.getAllMedication();
          this.snackbar.open('Bản ghi đã được xóa thành công', 'OK', { duration: 3000 });
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }

}