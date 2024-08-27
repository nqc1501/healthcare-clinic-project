import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { StorageService } from '../../auth/services/storage/storage.service';
import { ShiftService } from '../../services/shift/shift.service';
import { DoctorService } from '../../services/doctor/doctor.service';
import { ScheduleService } from '../../services/schedule/schedule.service';

@Component({
  selector: 'app-doctor-register-schedule',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatCheckboxModule,
    FormsModule,
  ],
  templateUrl: './doctor-register-schedule.component.html',
  styleUrl: './doctor-register-schedule.component.css'
})
export class DoctorRegisterScheduleComponent {

  displayedColumns: string[] = [
    'position', 
    'status', 
    'start-date', 
    'end-date', 
    'day-of-week', 
    'start-time', 
    'end-time',
    'quantity',
    'quantity-registered',
    'select'
  ];

  regsiteredColumns: string[] = [
    'position', 
    'start-date', 
    'end-date',
    'day-of-week', 
    'start-time', 
    'end-time'
  ];

  days: string[] = ['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ Nhật'];

  dayForm: FormGroup;

  dataSource = new MatTableDataSource<any>();
  listShift: any[] = [];
  listFilter: any[] = [];

  dataRegistered = new MatTableDataSource<any>();
  listRegistered: any[] = [];

  checked = false;

  private userId: string = this.sStorage.getUserId();

  constructor(
    private sShift: ShiftService,
    private sSchedule: ScheduleService,
    private sDoctor: DoctorService,
    private sStorage: StorageService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.getAllShift();
    this.getShiftByUser();

    this.dayForm = this.fb.group({
      selectedDay: ['']
    });

    this.dayForm.get('selectedDay').valueChanges.subscribe(
      day => {
        this.getOnDay(day);
      }
    );
  }

  formatDate(date: Date) {
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();

    return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
  }

  updateSelection(obj: any) {
    obj.isChecked = !obj.isChecked;
    if (obj.isChecked) {
      const existedRecord = this.dataRegistered.data.find(record => record.id === obj.shiftData.id);
      if (!existedRecord) {
        obj.shiftData.quantityRegistered++;
        this.dataRegistered.data.push(obj.shiftData);
        this.listRegistered.push(obj.shiftData);
  
        this.dataRegistered._updateChangeSubscription(  );
      }
    } else {
      obj.shiftData.quantityRegistered--;

      const inList = this.listRegistered.indexOf(obj.shiftData);
      const inData = this.dataRegistered.data.indexOf(obj.shiftData);

      if (inList !== -1 && inData !== -1) {
        this.listRegistered.splice(inList, 1);
        this.dataRegistered.data.splice(inData, 1);

        this.dataRegistered._updateChangeSubscription();
      }
    }
  }

  registerShift() {
    this.sShift.registerByUser(1, this.initDataToReg()).subscribe({
      next: (res) => {
        this.sDoctor.registerShift('', res.data).subscribe({
          next: (r) => {
            this.getAllShift();
            this.getShiftByUser();
          },
          error: (e) => {
            console.log(e);
          }
        });
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  private getAllShift() {
    this.sShift.getAllShift().subscribe({
      next: (res) => {
        console.log(res);
        this.listShift = [];
        for (let r of res) {
          const shift = {
            id: r.id,
            startDate: new Date(r.startDate),
            endDate: new Date(r.endDate),
            dayOfWeek: r.dayOfWeek,
            startTime: r.startTime,
            endTime: r.endTime,
            quantity: r.quantity,
            quantityRegistered: r.quantityRegistered,
            isSelected: r.isSelected
          };
          this.listShift.push(shift);
        }
        console.log(this.listShift);
        this.initData(this.listShift);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  private getShiftByUser() {
    this.sSchedule.getAllByDoctor(this.userId).subscribe({
      next: (res) => {
        const list: any[] = [];
        for (let r of res) {
          const shift = {
            id: r.id,
            startDate: new Date(r.startDate),
            endDate: new Date(r.endDate),
            dayOfWeek: r.dayOfWeek,
            startTime: r.startTime,
            endTime: r.endTime,
            quantity: r.quantity,
            quantityRegistered: r.quantityRegistered,
            isSelected: r.isSelected
          };
          this.listRegistered.push(shift);
          list.push(shift);
        }
        this.dataRegistered = new MatTableDataSource(list);
      },
      error: (err) => {
        console.log(err);
      }
    }); 
  }

  private initData(object: any[]) {
    const list: any[] = [];
    for (let obj of object) {
      if (obj.isSelected === false) {
        list.push({shiftData: obj, isChecked: false});
      } else {
        list.push({shiftData: obj, isChecked: true});
      }
    }
    this.dataSource.data = [];
    this.dataSource = new MatTableDataSource(list);
  }

  private initDataToReg() {
    const data: any[] = [];

    for (let shift of this.listRegistered) {
      data.push({userId: this.userId, shiftId: shift.id});
    }

    return data;
  }

  private getOnDay(day: string) {
    const list: any[] = [];
    for (let obj of this.listShift) {
      if (obj.dayOfWeek === day) {
        if (obj.isSelected === false) {
          list.push({shiftData: obj, isChecked: false});
        } else {
          list.push({shiftData: obj, isChecked: true});
        }
      }
    }

    this.dataSource.data = [];
    this.dataSource = new MatTableDataSource(list);
  }
}

export interface Shift {
  id: number;
  startDate: Date;
  endDate: Date;
  dayOfWeek: string;
  startTime: string;
  endTime: string;
  quantity: number;
  quantityRegistered: number;
  isSelected: boolean;
}

const DATA: Shift[] = [
  {id: 1, startDate: new Date('2024-05-06'), endDate: new Date('2024-05-13'), dayOfWeek: 'Thứ 2', startTime: '8:00', endTime: '9:00', quantity: 10, quantityRegistered: 9, isSelected: false},
  {id: 2, startDate: new Date('2024-05-07'), endDate: new Date('2024-05-14'), dayOfWeek: 'Thứ 3', startTime: '7:00', endTime: '8:00', quantity: 10, quantityRegistered: 0, isSelected: false},
  {id: 3, startDate: new Date('2024-05-08'), endDate: new Date('2024-05-15'), dayOfWeek: 'Thứ 4', startTime: '10:00', endTime: '12:00', quantity: 10, quantityRegistered: 0, isSelected: false},
  {id: 4, startDate: new Date('2024-05-13'), endDate: new Date('2024-05-27'), dayOfWeek: 'Thứ 2', startTime: '10:00', endTime: '12:00', quantity: 10, quantityRegistered: 0, isSelected: false},
];