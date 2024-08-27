import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SpecialtyService } from '../../services/specialty/specialty.service';
import { PatientService } from '../../services/patient/patient.service';
import { DoctorService } from '../../services/doctor/doctor.service';
import { AppointmentService } from '../../services/appointment/appointment.service';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent {

  isBooked: boolean = false;

  minDate: Date;
  maxDate: Date;
  listHour: string[] = ['7:00', '8:00', '9:00', '10:00', '11:00'];

  listSpecialty: any[];
  listDoctor: any[];

  appointmentForm: FormGroup;

  constructor(
    private sSpec: SpecialtyService,
    private sDoctor: DoctorService,
    private sPatient: PatientService,
    private sApp: AppointmentService,
    private fb: FormBuilder,
  ) {
    this.minDate = new Date();
    this.maxDate = new Date();
    this.maxDate.setMonth(this.maxDate.getMonth() + 1);

    this.appointmentForm = this.fb.group({
      name: ['', Validators.required],
      birthday: ['', Validators.required],
      tel: ['', Validators.required],
      email: ['', Validators.required],
      gender: ['', Validators.required],
      healthInsuranceCode: ['', Validators.required],
      address: ['', Validators.required],
      specialty: ['', Validators.required],
      doctor: ['', Validators.required],
      date: ['', Validators.required],
      hour: ['', Validators.required],
      description: [''],
    });
   }

  ngOnInit() {    
    this.getAllSpecialty();
  }

  getAllSpecialty() {
    this.sSpec.getAllSpecialty().subscribe({
      next: (res) => {
        this.listSpecialty = res;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  getDoctorBySpecialist(specialtyId: number) {
    this.sDoctor.getDoctorBySpecialty(specialtyId).subscribe({
      next: (res) => {
        this.listDoctor = res;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  selectionSpecialty(specialty: any) {
    this.getDoctorBySpecialist(specialty.id);
  }

  onFormSubmit() {
    if (this.appointmentForm.valid) {
      this.sPatient.addPatient(this.cvtToPatient(this.appointmentForm.value)).subscribe({
        next: (res) => {
          console.log(res);
          const appointment = this.cvtToAppointment(res.data.id, this.appointmentForm.value)
          this.sApp.addAppointment(res.id, appointment).subscribe({
            next: (r) => {
              this.isBooked = true;
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
  }

  private cvtToPatient(object: any) {
    return {
      name: object.name,
      birthday: object.birthday,
      gender: object.gender,
      tel: object.tel,
      email: object.email,
      address: object.address,
      healthInsuranceCode: object.healthInsuranceCode,
    }
  }

  private cvtToAppointment(patientId: string, object: any) {
    return {
      patientId: patientId,
      doctorId: object.doctor.id,
      date: object.date,
      hour: object.hour,
      description: object.description,
    }
  }

}
