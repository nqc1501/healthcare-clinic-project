import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewSpecialistComponent } from './add-new-specialist.component';

describe('AddNewSpecialistComponent', () => {
  let component: AddNewSpecialistComponent;
  let fixture: ComponentFixture<AddNewSpecialistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddNewSpecialistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddNewSpecialistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
