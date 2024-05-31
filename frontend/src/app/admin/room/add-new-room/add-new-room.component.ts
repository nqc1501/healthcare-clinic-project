import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { RoomService } from '../../../services/room/room.service';
import { SpecialistService } from '../../../services/specialist/specialist.service';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';

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
  selectedSpecialist: any | null = null;

  // data = {
  //   name: 'Room name', 
  //   specialist: {
  //     id: 1,
  //     name: 'Room 1',
  //     listServices: [
  //       {
  //         id: 1,
  //         name: 'first'
  //       },
  //       {
  //         id: 2,
  //         name: 'second'
  //       }
  //     ]
  //   },
  //   function: {
  //     id: 1,
  //     name: 'first'
  //   }
  // };

  constructor(
    private sRoom: RoomService,
    private sSpecialist: SpecialistService,
    private fb: FormBuilder,
    private snackbar: MatSnackBar,
    private dialogRef: MatDialogRef<AddNewRoomComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any | null = null
  ) { 
    this.roomForm = this.fb.group({
      name: ['', Validators.required],
      specialist: ['', Validators.required],
      function: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.getData(this.data);
    this.getListService();
    // this.roomForm.patchValue(this.data);
    // this.listSpecialist = [
    //   {
    //     id: 1,
    //     name: 'Room 1',
    //     listServices: [
    //       {
    //         id: 1,
    //         name: 'first'
    //       },
    //       {
    //         id: 2,
    //         name: 'second'
    //       }
    //     ]
    //   },
    //   {
    //     id: 2,
    //     name: 'Room 2',
    //     listServices: [
    //       {
    //         id: 1,
    //         name: 'third'
    //       },
    //       {
    //         id: 2,
    //         name: 'forth'
    //       }
    //     ]
    //   }
    // ];
    // this.selectedSpecialist = this.listSpecialist[0];
    // this.roomForm.patchValue({
    //   specialist: this.listSpecialist[0],
    //   function: this.listSpecialist[0]?.listServices[0]
    // });
  }

  getListService() {
    this.sSpecialist.findAllSpecialist().subscribe({
      next: (res) => {
        this.listSpecialist = res;
      }
    });
  }

  private getData(data: any) {
    if (data) {
      this.sSpecialist.findSpecialistByServiceId(data.serviceId).subscribe({
        next: (res) => {
          this.selectedSpecialist = res;
          this.info = {
            'name': data.name,
            'specialist': res.id,
            'function': data.serviceId
          };
          this.roomForm.patchValue(this.info);
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }

  onSelectionChange() {
    const selectedSpecialistId = this.roomForm.get('specialist')?.value;
    this.selectedSpecialist = this.listSpecialist.find(spe => spe.id === selectedSpecialistId);
  }

  onFormSubmit() {
    if (!this.roomForm.valid) {
      this.snackbar.open('Vui lòng nhập đầy đủ thông tin', 'OK', { duration: 3000 });
    } else {
      const fd = new FormData();
      fd.append('name', this.roomForm.value.name);
      fd.append('serviceId', this.roomForm.value.function);
      if (this.data) {
        console.log(this.roomForm.value);
        this.dialogRef.close(true);
      } else {
        this.sRoom.addNewRoom(fd).subscribe({
          next: (res) => {
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
