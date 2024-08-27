import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorTestResultComponent } from './doctor-test-result.component';

describe('DoctorTestResultComponent', () => {
  let component: DoctorTestResultComponent;
  let fixture: ComponentFixture<DoctorTestResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorTestResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DoctorTestResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
