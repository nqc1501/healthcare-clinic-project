import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistryScheduleManagementComponent } from './registry-schedule-management.component';

describe('RegistryScheduleManagementComponent', () => {
  let component: RegistryScheduleManagementComponent;
  let fixture: ComponentFixture<RegistryScheduleManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistryScheduleManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegistryScheduleManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
