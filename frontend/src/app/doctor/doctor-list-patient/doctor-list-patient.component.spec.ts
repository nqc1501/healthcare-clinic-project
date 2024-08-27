import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorListPatientComponent } from './doctor-list-patient.component';

describe('DoctorListPatientComponent', () => {
  let component: DoctorListPatientComponent;
  let fixture: ComponentFixture<DoctorListPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorListPatientComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DoctorListPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
