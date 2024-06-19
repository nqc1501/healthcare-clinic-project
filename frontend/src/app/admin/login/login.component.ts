import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../../auth/services/auth/auth.service';
import { MatButtonModule } from '@angular/material/button';
import { StorageService } from '../../auth/services/storage/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private sAuth: AuthService,
    private sStorage: StorageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.loginForm.valid) {
      this.sAuth.login(
        this.loginForm.value.email,
        this.loginForm.value.password
      ).subscribe({
        next: (res) => {
          console.log(res);
          this.sStorage.saveId(res.data.id);
          this.sStorage.saveRole(res.data.role);
          this.router.navigateByUrl('/admin/dashboard');
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }

}
