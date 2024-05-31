import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { noGuardGuard } from './no-guard.guard';

describe('noGuardGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => noGuardGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
