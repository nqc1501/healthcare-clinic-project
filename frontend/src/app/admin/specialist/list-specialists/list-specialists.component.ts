import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { SpecialistService } from '../../../services/specialist/specialist.service';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { AddNewServiceComponent } from '../services/add-new-service/add-new-service.component';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-list-specialists',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatDialogModule,
    MatIconModule,
    MatToolbarModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  templateUrl: './list-specialists.component.html',
  styleUrl: './list-specialists.component.css'
})
export class ListSpecialistsComponent {

  displayedColumns: string[] = ['position', 'name', 'edit', 'delete'];
  dataSource: MatTableDataSource<any>;
  listSpecialist: any[] = [];
  firstValue: FormControl = new FormControl();
  selected: any;

  constructor(
    private sSpecialist: SpecialistService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getAllSpecialists();
  }

  onSelectionChange(selectedSpecialist: any) {
    this.dataSource.data = selectedSpecialist.listServices;
    this.selected = selectedSpecialist;
  }

  getAllSpecialists() {
    this.sSpecialist.findAllSpecialist().subscribe({
      next: (res) => {
        console.log(res);
        this.listSpecialist = res;
        this.selected = this.listSpecialist[0];
        this.firstValue = new FormControl(this.listSpecialist[0]);
        this.dataSource = new MatTableDataSource(this.listSpecialist[0].listServices);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  openAddForm() {
    const data = {
      id: this.selected.id,
      name: this.selected.name,
      listServices: [],
      isAdd: true
    };

    const dialogRef = this.dialog.open(AddNewServiceComponent);
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.dataSource.data.push(res);
          this.dataSource._updateChangeSubscription();
        }
      }
    });
  }

  openEditForm(obj: any) {
    const data = {
      id: obj.id,
      name: obj.name,
      isAdd: false
    };
    
    const dialogRef = this.dialog.open(AddNewServiceComponent, {data});
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.dataSource.data[0] = res;
          this.dataSource._updateChangeSubscription();
        }
      }
    });
  }

  openDeleteForm(obj: any) {
    const isDelelte = window.confirm('Bạn có muốn xóa bản ghi này không?');
    if (isDelelte) {
      const index = this.dataSource.data.findIndex(item => item.id === obj.id);
      if (index !== -1) {
        this.sSpecialist.deleteService(obj.id).subscribe({
          next: (res) => {
            console.log(res);
            this.snackbar.open('Bạn đã xóa bản ghi thành công', 'OK', { duration: 3000 });
          },
          error: (err) => {
            console.log(err);
          }
        });
        this.firstValue = new FormControl(this.listSpecialist[this.findIndexSpecialist(this.selected)]);
        this.dataSource.data.splice(index, 1);
      }
      this.dataSource._updateChangeSubscription();
    }
  }

  findIndexSpecialist(obj: any) {
    return this.listSpecialist.findIndex(s => s.id === obj.id);
  }
}

export interface Specialist {
  id: number;
  name: string;
  listServices: Function[];
}

export interface Function {
  id: number;
  name: string;
}