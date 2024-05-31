import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealhServiceComponent } from './healh-service.component';

describe('HealhServiceComponent', () => {
  let component: HealhServiceComponent;
  let fixture: ComponentFixture<HealhServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HealhServiceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HealhServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
