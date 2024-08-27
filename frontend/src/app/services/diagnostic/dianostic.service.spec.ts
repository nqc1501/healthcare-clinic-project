import { TestBed } from '@angular/core/testing';

import { DianosticService } from './dianostic.service';

describe('DianosticService', () => {
  let service: DianosticService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DianosticService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
