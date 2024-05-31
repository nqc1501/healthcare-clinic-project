import { Component } from '@angular/core';
import { RoomService } from '../../services/room/room.service';
import { DoctorService } from '../../services/doctor/doctor.service';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { ShiftService } from '../../services/shift/shift.service';
import { ScheduleService } from '../../services/schedule/schedule.service';

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
  ],
  templateUrl: './registry-schedule-management.component.html',
  styleUrl: './registry-schedule-management.component.css'
})
export class RegistryScheduleManagementComponent {
  
  displayedColumns: string[] = [
    'position', 'startDate', 'endDate', 'dayOfWeek', 'startTime', 'endTime', 'room'
  ];
  dataSource = new MatTableDataSource<any>();
  listDoctor: any[];
  listShift: any[];
  listRoom: any[];
  selectedValue: any;

  constructor(
    private sDoc: DoctorService,
    private sRoom: RoomService,
    private sShift: ShiftService,
    private sSche: ScheduleService,
  ) { }

  ngOnInit() {
    this.getAllDoctors();
    this.getAllRoom();
  }


  getAllDoctors() {
    this.sDoc.getAllDoctors().subscribe({
      next: (res) => {
        this.listDoctor = res;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  getShiftByDoctor() {
    this.sShift.getAllShiftByUser(this.selectedValue.id).subscribe({
      next: (res) => {
        this.listShift = res;
        const data = [];
        for (let s of this.listShift) {
          data.push({shift: s, room: null});
        }
        this.dataSource = new MatTableDataSource(data);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  getAllRoom() {
    this.sRoom.getAllRooms().subscribe({
      next: (res) => {
        this.listRoom = res;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  addSchedule(listSchedule) {
    this.sSche.addNewSchedule(listSchedule).subscribe({
      next: (res) => {
        console.log(res);
      }, 
      error: (err) => {
        console.log(err);
      }
    });
  }

  onSelectedDoctor(doctor: any) {
    this.selectedValue = doctor;
    this.getShiftByDoctor();
  }

  onSelectedRoom(room: any, shiftId: number) {
    const index = this.dataSource.data.findIndex(item => item.shift.id === shiftId);

    if (index !== -1) {
      this.dataSource.data[index].room = room;
      this.dataSource._updateChangeSubscription();
    }
  }

  onSave() {
    const listSchedule = [];
    for (let s of this.dataSource.data) {
      listSchedule.push({roomId: s.room.id, shiftId: s.shift.id});
    }
    console.log(listSchedule);
    this.addSchedule(listSchedule);
  }

  getSelectedRoom(element: any) {
    return element.room ? element.room.name : null;
  }

  formatDate(dateString: string) {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();

    return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
  }

}

const Data = [
  {startDate: '11/2/2025', endDate: '11/3/2025', dayOfWeek: 'Thứ 2', startTime: '5:00', endTime: '10:00'},
];