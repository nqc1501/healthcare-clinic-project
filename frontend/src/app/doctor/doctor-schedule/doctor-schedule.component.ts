import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ShiftService } from '../../services/shift/shift.service';
import { MatSelectModule } from '@angular/material/select';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { StorageService } from '../../auth/services/storage/storage.service';
import { ScheduleService } from '../../services/schedule/schedule.service';

@Component({
  selector: 'app-doctor-schedule',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSelectModule,
    MatInputModule
  ],
  templateUrl: './doctor-schedule.component.html',
  styleUrl: './doctor-schedule.component.css'
})
export class DoctorScheduleComponent {

  days: string[] = ['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ Nhật'];
  hours: string[] = ['7:00', '8:00', '9:00', '10:00', '11:00', '12:00'];

  dataSource = new MatTableDataSource<any>();
  // listData = [
  //   { time: '7:00', daysData: this.initialData() },
  //   { time: '8:00', daysData: this.initialData() },
  //   { time: '9:00', daysData: this.initialData() },
  //   { time: '10:00', daysData: this.initialData() },
  //   { time: '11:00', daysData: this.initialData() },
  //   { time: '12:00', daysData: this.initialData() },
  // ];

  listData: any[] = [];

  listShift: Shift[] = [];
  listShiftOfWeek: Shift[] = [];

  weekForm: FormGroup;
  weeks: Week[] = [];

  constructor(
    private sShift: ShiftService,
    private sSchedule: ScheduleService,
    private sStorage: StorageService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit() {
    // Khởi tạo
    this.listData = this.initializeListData();
    this.generateWeeks();
    this.weekForm = this.fb.group({
      selectedWeek: [this.weeks[0]]
    });
    this.setScheduleOnWeek(this.weeks[0]);
    this.getAllShift();

    // Set dữ liệu
    this.weekForm.get('selectedWeek').valueChanges.subscribe(
      week => {
        this.setScheduleOnWeek(week);
      }
    );
  }

  private generateWeeks() {
    const monday = new Date('2024-05-06');
  
    for (let i = 0; i < 4; i++) {
      const start = new Date(monday);
      const end = new Date(monday.getFullYear(), monday.getMonth(), monday.getDate() + 6);
      const day = {startDate: start, endDate: end};
      this.weeks.push(day);
      monday.setDate(monday.getDate() + 7);
    }
  }

  formatDate(date: Date) {
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();

    return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
  }

  private getAllShift() {
    this.sSchedule.getAllByDoctor(this.sStorage.getUserId()).subscribe({
      next: (res) => {
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
        this.setScheduleOnWeek(this.weeks[0]);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  private setScheduleOnWeek(week: Week) {
    this.listData = this.initializeListData();
    this.listShiftOfWeek = [];
    const first = week.startDate;
    const end = week.endDate;
    for (let shift of this.listShift) {
      if (this.formatDate(shift.endDate) < this.formatDate(first)) {
        continue;
      } else if (this.formatDate(shift.startDate) > this.formatDate(end)) {
        continue;
      } else {
        this.listShiftOfWeek.push(shift);
      }
    }
    this.setData(this.listShiftOfWeek);
    this.dataSource = new MatTableDataSource(this.listData);
  }

  private initializeListData(): any[] {
    const data: any[] = [];
    
    for (const time of this.hours) {
      data.push({ time, daysData: this.initializeData() });
    }
  
    return data;
  }

  private initializeData(): any {
    const emptyData: any = {};
    for (const day of this.days) {
      emptyData[day] = 0;
    }
    return emptyData;
  }  

  private setData(list: any[]) {
    for (const shift of list) {
      const startHourIndex = this.hours.indexOf(shift.startTime) !== -1 ? this.hours.indexOf(shift.startTime) : 1;
      const endHourIndex = this.hours.indexOf(shift.endTime) !== -1 ? this.hours.indexOf(shift.endTime) : this.hours.length;

      for (let i = startHourIndex; i < endHourIndex; i++) {
        this.listData[i].daysData[shift.dayOfWeek] = 1;
      }
    }
  }
}

export interface Week {
  startDate: Date;
  endDate: Date;
}

export interface Shift {
  startDate: Date;
  endDate: Date;
  dayOfWeek: string;
  startTime: string;
  endTime: string;
}

export interface CASE {
  date: string,
  startTime: string,
  endTime: string
}

const DATA: Shift[] = [
  {startDate: new Date('2024-05-06'), endDate: new Date('2024-05-13'), dayOfWeek: 'Thứ 2', startTime: '8:00', endTime: '9:00'},
  {startDate: new Date('2024-05-07'), endDate: new Date('2024-05-14'), dayOfWeek: 'Thứ 3', startTime: '7:00', endTime: '8:00'},
  {startDate: new Date('2024-05-08'), endDate: new Date('2024-05-15'), dayOfWeek: 'Thứ 4', startTime: '10:00', endTime: '12:00'},
  {startDate: new Date('2024-05-10'), endDate: new Date('2024-05-24'), dayOfWeek: 'Thứ 6', startTime: '10:00', endTime: '12:00'},
];