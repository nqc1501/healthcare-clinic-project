import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { CredentialService } from '../../../services/credential/credential.service';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinner, MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-doctor',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatRadioModule,
    MatSelectModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './create-doctor.component.html',
  styleUrl: './create-doctor.component.css'
})
export class CreateDoctorComponent {

  doctorForm: FormGroup;
  imageUrl: string;
  imageData: string;

  constructor(
    private sCredential: CredentialService,
    private sDoctor: DoctorService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.doctorForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      name: ['', Validators.required],
      image: ['', Validators.required],
      birthday: ['', Validators.required],
      gender: ['', Validators.required],
      tel: ['', Validators.required],
      address: ['', Validators.required],
      about: ['', Validators.required],
      specialist: ['', Validators.required],
      degree: ['', Validators.required]
    });
  }

  onFormSubmit() {
    if (this.doctorForm.valid) {
      const creData = new FormData();
      creData.append('email', this.doctorForm.value.email);
      creData.append('password', this.doctorForm.value.password);

      this.sCredential.addDoctorAccount(creData).subscribe({
        next: (res) => {
          this.sCredential.getCredentialByEmail(this.doctorForm.value.email).subscribe({
            next: (r) => {
              const fd = new FormData();
              fd.append('credentialId', r.id);
              fd.append('name', this.doctorForm.value.name);
              fd.append('image', this.imageData);
              fd.append('birthday', this.doctorForm.value.birthday);
              fd.append('gender', this.doctorForm.value.gender);
              fd.append('tel', this.doctorForm.value.tel);
              fd.append('address', this.doctorForm.value.address);
              fd.append('about', this.doctorForm.value.about);
              fd.append('specialist', this.doctorForm.value.specialist);
              fd.append('degree', this.doctorForm.value.degree);
              this.sDoctor.addNewDoctor(fd).subscribe({
                next: (response) => {
                  console.log(response);
                  this.router.navigateByUrl('/admin/list-doctor');
                },
                error: (error) => {
                  console.log(error);
                }
              });
            },
            error: (e) => {
              console.log(e);
            }
          });
        }
      });
    }
  }

  chooseImage(event: any) {
    const files = event.target.files;
    if (files && files[0]) {
      const selectedFile = files[0];
      const reader = new FileReader();
      reader.onload = (e) => {
        const base64String = e.target?.result as string;
        this.imageData = base64String.split(',')[1];
        this.imageUrl = base64String;
      };
      reader.readAsDataURL(selectedFile);
    }
  }

  openFileInput() {
    const fileInput = document.getElementById('fileInput');
    if (fileInput) {
      fileInput.click();
    }
  }

}
