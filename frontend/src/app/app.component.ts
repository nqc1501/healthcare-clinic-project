import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { StorageService } from './auth/services/storage/storage.service';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatIconModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  dataSource: MatTableDataSource<any>;

  buttonMenus = [
    { label: 'Trang chủ', link: '/user/dashboard' },
    { label: 'Giới thiệu', link: '/user/dashboard' },
    { label: 'Dịch vụ y tế', link: '/user/dashboard' },
    { label: 'Đội ngũ chuyên gia', link: '/user/dashboard' },
    { label: 'Hướng dẫn khách hàng', link: '/user/dashboard' },
    { label: 'Nghiên cứu khoa học', link: '/user/dashboard' },
    { label: 'Tin tức', link: '/user/login', 
      options: [
        { label: 'Tin tức tổng hợp', link: '' },
        { label: 'Tin tức y khoa', link: '' },
        { label: 'Thông báo dịch vụ', link: '' },
      ] 
    },
    { label: 'Tuyển dụng', link: '/user/dashboard' }
  ];

  constructor(
    private storage: StorageService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit() {

  }

  logout() {

  }

  toDashboard() {
    
  }

  openRegisterForm() {
    const dialogRef = this.dialog.open(RegisterComponent);    
  }

  openLoginForm() {
    const dialogRef = this.dialog.open(LoginComponent);
  }

  navigateToLink(url: string) {
    this.router.navigateByUrl(url);
  }

}
