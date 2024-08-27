import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorDiagnosticComponent } from './doctor-diagnostic.component';

describe('DoctorDiagnosticComponent', () => {
  let component: DoctorDiagnosticComponent;
  let fixture: ComponentFixture<DoctorDiagnosticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorDiagnosticComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DoctorDiagnosticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
