import { AfterViewInit, Component, Injectable, ViewChild } from '@angular/core';
import { RoomService } from '../../../services/room/room.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatPaginator, MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { Subject } from 'rxjs';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AddNewRoomComponent } from '../add-new-room/add-new-room.component';
import { SpecialistService } from '../../../services/specialist/specialist.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

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
  selector: 'app-list-room',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  providers: [{provide: MatPaginatorIntl, useClass: MyCustomPaginatorIntl}],
  templateUrl: './list-room.component.html',
  styleUrl: './list-room.component.css'
})
export class ListRoomComponent implements AfterViewInit {

  displayedColumns: string[] = [
    'position', 'name', 'function', 'edit', 'delete'
  ];
  dataSource = new MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private sRoom: RoomService,
    private sSpecialist: SpecialistService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.getAllRooms();
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
      this.dataSource.paginator = this.paginator;
    }
  }

  getAllRooms() {
    const listRooms: { id: number, name: string, serviceId: number, serviceName: string }[] = [];

    this.sRoom.getAllRooms().subscribe({
      next: (response) => {
        this.sSpecialist.findAllServices().subscribe({
          next: (res) => {
            for (const r of response) {
              const service = res.find(s => s.id === r.serviceId).name;

              if (service) {
                listRooms.push({
                  id: r.id,
                  name: r.name,
                  serviceId: r.serviceId,
                  serviceName: service
                });
              }
            }
            this.dataSource = new MatTableDataSource(listRooms);
          }
        });
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  openAddForm() {
    const dialogRef = this.dialog.open(AddNewRoomComponent);
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllRooms();
        }
      }
    });
  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(AddNewRoomComponent, {data});
    dialogRef.afterClosed().subscribe({
      next: (res) => {
        if (res) {
          this.getAllRooms();
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
