import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { HeaderComponent } from '../components/header/header.component';
import { FooterComponent } from '../components/footer/footer.component';
import { SidebarComponent } from '../components/sidebar/sidebar.component';
import { MatDividerModule } from '@angular/material/divider';
import { StorageService } from '../../auth/services/storage/storage.service';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../auth/services/auth/auth.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    MatSidenavModule,
    MatDividerModule,
    MatToolbarModule
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

  sideBarOpen = true;
  loggedIn = false;

  constructor(
    private sStorage: StorageService,
    private sAuth: AuthService

  ) { }

  ngOnInit() {
    this.sAuth.isSignedIn().subscribe((isLoggedIn) => {
      this.loggedIn = isLoggedIn;
    });
  }

  sideBarToggler() {
    this.sideBarOpen = !this.sideBarOpen;
  }

}
