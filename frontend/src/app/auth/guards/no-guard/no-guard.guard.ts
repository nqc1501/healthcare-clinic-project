import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { Observable, of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';
import { StorageService } from '../../services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class NoGuard implements CanActivate {

  constructor(
    private sAuth: AuthService,
    private sStorage: StorageService,
    private router: Router
  ) { }  

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.sAuth.checkLogin().pipe(
      switchMap(isLoggedIn => {
        if (!isLoggedIn) {
          return of(true);
        }

        const role = this.sStorage.getRole();

        if (role === 'ADMIN') {
          this.router.navigateByUrl('/admin/dashboard');
        } else if (role === 'DOCTOR') {
          this.router.navigateByUrl('/doctor/dashboard');
        } else if (role === 'PATIENT') {
          this.router.navigateByUrl('/dashboard');
        } else {
          return of(true);
        }

        return of(false);
      })
    );
  }
}
