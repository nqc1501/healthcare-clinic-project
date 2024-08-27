import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListSuppliesComponent } from './list-supplies.component';

describe('ListSuppliesComponent', () => {
  let component: ListSuppliesComponent;
  let fixture: ComponentFixture<ListSuppliesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListSuppliesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListSuppliesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
