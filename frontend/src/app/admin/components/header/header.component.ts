import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatMenuModule } from '@angular/material/menu';
import { Output, EventEmitter } from '@angular/core';
import { FlexLayoutServerModule } from '@angular/flex-layout/server'
import { StorageService } from '../../../auth/services/storage/storage.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../../auth/services/auth/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    FlexLayoutModule,
    MatMenuModule,
    FlexLayoutServerModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  @Output() toggleSideBarForMe: EventEmitter<any> = new EventEmitter();

  constructor(
    private sStorage: StorageService,
    private sAuth: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  toggleSideBar() {
    this.toggleSideBarForMe.emit();
  }

  logout() {
    this.sAuth.signOut();
    this.sAuth.logout();
    this.sStorage.logout();
    this.router.navigateByUrl('/admin/login');
  }

}
