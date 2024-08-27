import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, RouterStateSnapshot } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { StorageService } from '../../services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(
    private sAuth: AuthService,
    private sStorage: StorageService,
    private router: Router,
    private snackbar: MatSnackBar,
  ) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {

    return this.sAuth.checkLogin().pipe(
      switchMap(isLoggedIn => {
        const role = this.sStorage.getRole();
        if (!isLoggedIn || role === null) {
          this.router.navigateByUrl('/admin/login');
          this.snackbar.open('Vui lòng đăng nhập', 'OK', {duration: 3000});
          return of(false);
        }

        if (role === 'DOCTOR') {
          this.router.navigateByUrl('/doctor/dashboard');
          this.snackbar.open('Không có quyền truy cập', 'OK', {duration: 3000});
          return of(false);
        } else if (role === 'PATIENT') {
          this.router.navigateByUrl('/dashboard');
          this.snackbar.open('Không có quyền truy cập', 'OK', {duration: 3000});
          return of(false);
        }

        return of(true);
      })
    );
  }
}