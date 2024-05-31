import { Component } from '@angular/core';
import { DoctorService } from '../../services/doctor/doctor.service';
import { ShiftService } from '../../services/shift/shift.service';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent {

  constructor(
    private sDoc: DoctorService,
    private sShift: ShiftService,
  ) { }

  ngOnInit() {
    
  }

}
