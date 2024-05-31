import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorRegisterScheduleComponent } from './doctor-register-schedule.component';

describe('DoctorRegisterScheduleComponent', () => {
  let component: DoctorRegisterScheduleComponent;
  let fixture: ComponentFixture<DoctorRegisterScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorRegisterScheduleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DoctorRegisterScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
