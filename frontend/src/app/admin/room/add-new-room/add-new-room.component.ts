import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { RoomService } from '../../../services/room/room.service';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { FloorService } from '../../../services/floor/floor.service';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Component({
  selector: 'app-add-new-room',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    MatMenuModule,
    MatSelectModule
  ],
  templateUrl: './add-new-room.component.html',
  styleUrl: './add-new-room.component.css'
})
export class AddNewRoomComponent {

  info: any = null;
  roomForm: FormGroup;

  listSpecialist: any[] = [];
  listFloor$: Observable<any[]>;
  selectedFloor: any;

  constructor(
    private sRoom: RoomService,
    private sFloor: FloorService,
    private fb: FormBuilder,
    private snackbar: MatSnackBar,
    private dialogRef: MatDialogRef<AddNewRoomComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any | null = null,
  ) { 
    this.roomForm = this.fb.group({
      name: ['', Validators.required],
      floor: ['', Validators.required],
      function: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.listFloor$ = this.sFloor.getAllFloor().pipe(
      tap(floors => {
        if (this.data) {
          this.selectedFloor = floors.find(floor => floor.id === this.data.floorId) || null;
          this.roomForm.patchValue({
            name: this.data.name,
            floor: this.selectedFloor,
            function: this.data.function,
          });
        }
      }),
      catchError(error => {
        console.error('Error loading floors', error);
        return of([]);
      })
    )
  }

  onSelectionChange() {
    this.selectedFloor = this.roomForm.get('floor')?.value;
  }

  onFormSubmit() {
    if (!this.roomForm.valid) {
      this.snackbar.open('Vui lòng nhập đầy đủ thông tin', 'OK', { duration: 3000 });
    } else {      
      const fd = new FormData();
      fd.append('floorId', this.roomForm.value.floor.id);
      fd.append('name', this.roomForm.value.name);
      fd.append('fuction', this.roomForm.value.function);
      if (this.data) {
        this.sRoom.updateRoom(fd).subscribe({
          next: (res) => {
            console.log(res);
          },
          error: (err) => {
            console.log(err);
          }
        });
        this.dialogRef.close(true);
      } else {
        this.sRoom.addNewRoom(fd).subscribe({
          next: (res) => {
            console.log(res);
            this.snackbar.open('Thêm phòng thành công', 'OK', { duration: 3000 });
            this.dialogRef.close(true);
          },
          error: (err) => {
            console.log(err);
          }
        });
      }
    }
  }

}
