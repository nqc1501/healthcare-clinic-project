import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, Injectable, OnInit, ViewChild } from '@angular/core';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { MatPaginator, MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Subject, forkJoin, of } from 'rxjs';
import { map, mergeMap } from 'rxjs/operators';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { SpecialtyService } from '../../../services/specialty/specialty.service';

@Injectable()
export class MyCustomPaginatorIntl implements MatPaginatorIntl {
  
  changes = new Subject<void>();

  firstPageLabel = $localize`Trang đầu`;
  itemsPerPageLabel = $localize`Số lượng`;
  lastPageLabel = $localize`Trang cuối`;

  nextPageLabel = 'Trang kế';
  previousPageLabel = 'Trang trước';

  getRangeLabel(page: number, pageSize: number, length: number): string {
    if (length === 0) {
      return $localize`Trang 1 / 1`;
    }
    const amountPages = Math.ceil(length / pageSize);
    return $localize`Trang ${page + 1} / ${amountPages}`;
  }
}

@Component({
  selector: 'app-doctor-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatSnackBarModule,
  ],
  providers: [{provide: MatPaginatorIntl, useClass: MyCustomPaginatorIntl}],
  templateUrl: './doctor-list.component.html',
  styleUrl: './doctor-list.component.css'
})
export class DoctorListComponent implements AfterViewInit {

  displayedColumns: string[] = [
    'position',
    'name',
    'specialty',
    'degree',
    'action'
  ];
  dataSource = new MatTableDataSource<any>();
  specialtyName: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private sDoctor: DoctorService,
    private sSpecialty: SpecialtyService,
    private snackbar: MatSnackBar,
  ) { }

  ngAfterViewInit() {
    this.getAllDoctors();
  }

  getAllDoctors() {
    this.sDoctor.getAllDoctors().subscribe({
      next: (res) => {
        const doctorObservables = res.map((r) => {
          if (r.specialtyId === null) {
            return of({doctorData: r, specialtyData: null});
          } else {
            return this.sSpecialty.getById(r.specialtyId).pipe(
              map((response) => this.convertToDoctor(r, response))
            );
          }
        });
  
        forkJoin(doctorObservables).subscribe({
          next: (listDoctor: any[]) => {
            console.log(listDoctor);
            this.dataSource = new MatTableDataSource(listDoctor);
            this.dataSource.paginator = this.paginator;
          },
          error: (error) => {
            console.log(error);
          }
        });
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  applyFilter(event: any) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteDoctor(id: number) {
    const result = window.confirm('Bạn có mốn xóa bác sĩ này không?');
    if (result) {
      this.sDoctor.deleteDoctor(id).subscribe({
        next: (res) => {
          this.getAllDoctors();
          this.snackbar.open('Bác sĩ đã được xóa.', 'OK', {duration: 5000});
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }

  private convertToDoctor(doctorDB: any, specialty: any) {
    return {doctorData: doctorDB, specialtyData: specialty};
  }

}
