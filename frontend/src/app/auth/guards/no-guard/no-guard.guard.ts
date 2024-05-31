import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../../services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class NoGuard implements CanActivate {

  constructor(
    private sStorage: StorageService,
    private router: Router
  ) { }  

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.sStorage.hasToken && this.sStorage.isAdminLoggedIn()) {
      this.router.navigateByUrl('/admin/dashboard');
      return false;
    }
    return true;
  }
}
