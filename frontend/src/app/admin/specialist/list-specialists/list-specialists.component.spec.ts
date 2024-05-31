import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListSpecialistsComponent } from './list-specialists.component';

describe('ListSpecialistsComponent', () => {
  let component: ListSpecialistsComponent;
  let fixture: ComponentFixture<ListSpecialistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListSpecialistsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListSpecialistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
