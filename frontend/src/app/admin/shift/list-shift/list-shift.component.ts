import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ShiftService } from '../../../services/shift/shift.service';
import { ShiftDetailComponent } from '../shift-detail/shift-detail.component';

@Component({
  selector: 'app-list-shift',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
  ],
  templateUrl: './list-shift.component.html',
  styleUrl: './list-shift.component.css'
})
export class ListShiftComponent {

  displayedColumns: string[] = [
    'position', 'start-date', 'end-date', 'day-of-week', 'start-time', 'end-time', 'quantity', 'quantity-registered', 'action'
  ];
  dataSource = new MatTableDataSource<any>();

  constructor(
    private sShift: ShiftService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar,
  ) {}

  ngOnInit() {
    this.getAllShift();
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
    const dialogRef = this.dialog.open(ShiftDetailComponent);
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllShift();
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(ShiftDetailComponent, {data});
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllShift();
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  openDeleteForm(id: number) {
    const result = window.confirm('Xóa ca làm việc này ?');
    if (result) {
      this.sShift.deleteShift(id).subscribe({
        next: (res) => {
          this.getAllShift();
          this.snackbar.open('Ca làm việc đã được xóa thành công', 'OK', {duration: 3000});
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }

  formatDate(dateString: string) {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();

    return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
  }
}
