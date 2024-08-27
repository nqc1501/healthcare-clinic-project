import { Component } from '@angular/core';
import { RoomService } from '../../services/room/room.service';
import { DoctorService } from '../../services/doctor/doctor.service';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShiftService } from '../../services/shift/shift.service';
import { ScheduleService } from '../../services/schedule/schedule.service';
import { SpecialtyService } from '../../services/specialty/specialty.service';
import { FloorService } from '../../services/floor/floor.service';
import { map, switchMap } from 'rxjs/operators';
import { Observable, forkJoin, of } from 'rxjs';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatRadioModule } from '@angular/material/radio';

@Component({
  selector: 'app-registry-schedule-management',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
    FormsModule,
    MatSnackBarModule,
    MatRadioModule,
  ],
  templateUrl: './registry-schedule-management.component.html',
  styleUrl: './registry-schedule-management.component.css'
})
export class RegistryScheduleManagementComponent {
  
  displayedColumns: string[] = [
    'position', 'startDate', 'endDate', 'dayOfWeek', 'startTime', 'endTime', 'doctor', 'room'
  ];
  dataSource = new MatTableDataSource<any>();
  listSpecialty: any[];
  selectedValue: any;
  isSelected: boolean = false;

  roomColumns: string[] = [
    'position', 'name', 'function', 'checkbox'
  ];
  roomData = new MatTableDataSource<any>();
  isRegistered: boolean = false;
  selectedSchedule: any;

  constructor(
    private sSpe: SpecialtyService,
    private sFloor: FloorService,
    private sDoc: DoctorService,
    private sRoom: RoomService,
    private sShift: ShiftService,
    private sSche: ScheduleService,
    private snackbar: MatSnackBar,
  ) { }

  ngOnInit() {
    this.getAllSpecialty();
  }

  getAllSpecialty() {
    this.sSpe.getAllSpecialty().subscribe({
      next: (res) => {
        this.listSpecialty = res;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  getScheduleBySpecialtyId(specialtyId: number) {
    this.sSpe.getSchedulesBySpecialtyId(specialtyId).pipe(
      switchMap(schedules => {
        const listSchedule$ = schedules.map(schedule => {
          const shift$ = this.sShift.getById(schedule.shiftId) as Observable<any>;
          const doctor$ = this.sDoc.getById(schedule.doctorId) as Observable<any>;
          const room$ = schedule.roomId ? this.sRoom.getById(schedule.roomId) as Observable<any> : of(null);

          return forkJoin([shift$, doctor$, room$]).pipe(
            map(([shift, doctor, room]) => ({
              id: schedule.id,
              shift: shift,
              doctor: doctor,
              room: room,
            }))
          );
        });
        return forkJoin(listSchedule$) as Observable<any[]>;
      })
    ).subscribe(res => {
      this.dataSource = new MatTableDataSource(res);
    });
  }

  getAllRoomBySpecialty(specialtyId: number, shiftId: number) {
    this.sSpe.getAllRoom(specialtyId).subscribe({
      next: (res) => {
        const listRoom = [];
        for (let room of res.data) {
          const index = this.dataSource.data.findIndex(
            schedule => schedule.room 
            && schedule.room.id === room.id 
            && schedule.shift.id === shiftId
          );
          if (index !== -1) {
            listRoom.push({
              id: room.id,
              floorId: room.floorId,
              name: room.name,
              function: room.function,
              isChecked: true
            });
          } else {
            listRoom.push({
              id: room.id,
              floorId: room.floorId,
              name: room.name,
              function: room.function,
              isChecked: false
            });
          }
        }
        this.roomData = new MatTableDataSource(listRoom);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  selectionSpecialty(specialty: any) {
    this.isSelected = false;
    this.selectedValue = specialty;
    this.getScheduleBySpecialtyId(this.selectedValue.id);
    this.selectedSchedule = null;
  }

  onRowClick(schedule: any) {
    this.isSelected = true;
    this.selectedSchedule = schedule;
    this.getAllRoomBySpecialty(this.selectedValue.id, this.selectedSchedule.shift.id);
    this.isRegistered = this.checkRegistry(this.selectedSchedule);
  }

  checkRegistry(schedule) {
    for (let s of this.dataSource.data) {
      if (s.id !== schedule.id) {
        if (s.shift.id === schedule.shift.id && s.doctor.specialtyId === schedule.doctor.specialtyId && s.room) {
          return true;
        }
      }
    }
    return false;
  }

  updateSchedule(room: any) {
    this.roomData.data.forEach(r => {
      if (r !== room) {
        r.isChecked = false;
      }
    });
    this.selectedSchedule.room = {id: room.id, floorId: room.floorId, name: room.name, function: room.function};
  }

  addRoomToSchedule() {
    if (!this.selectedSchedule.room) {
      this.snackbar.open('Bạn phải chọn phòng trước', 'OK', {duration: 3000});
    } else {
      const scheduleReq = {roomId: this.selectedSchedule.room.id};
      this.sSche.addRoomToSchedule(this.selectedSchedule.id, scheduleReq).subscribe({
        next: (res) => {
          console.log(res);
          this.selectionSpecialty(this.selectedValue);
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