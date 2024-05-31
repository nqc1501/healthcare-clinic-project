import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ShiftService } from '../../../services/shift/shift.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { AddNewShiftComponent } from '../add-new-shift/add-new-shift.component';

@Component({
  selector: 'app-list-shift',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    MatTableModule
  ],
  templateUrl: './list-shift.component.html',
  styleUrl: './list-shift.component.css'
})
export class ListShiftComponent {

  displayedColumns: string[] = [
    'position', 
    'name', 
    'date', 
    'start', 
    'end', 
    'quantity', 
    'booked', 
    'status', 
    'edit', 
    'delete'
  ];
  dataSource: MatTableDataSource<any>;

  listShift: any[] = [
    {name: 'Sáng', date: '12/1/2024', startTime: '4:30', endTime: '6:30', quantityBookOn: 2, quantityBooked: 1}
  ];

  constructor(
    private sShift: ShiftService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.dataSource = new MatTableDataSource(this.listShift);
  }

  getAllShift() {
    this.sShift.getAllShift().subscribe({
      next: (res) => {
        this.dataSource = new MatTableDataSource(res);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  openAddForm() {
    const dialogRef = this.dialog.open(AddNewShiftComponent);
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllShift();
        }
      }
    });
  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(AddNewShiftComponent, {data});
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllShift();
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

  // checkStatus(a: number, b: number): boolean {
  //   if (a < b) {
  //     return true;
  //   }
  //   return false;
  // }

}
