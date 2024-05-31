import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewSupplyComponent } from './add-new-supply.component';

describe('AddNewSupplyComponent', () => {
  let component: AddNewSupplyComponent;
  let fixture: ComponentFixture<AddNewSupplyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddNewSupplyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddNewSupplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
