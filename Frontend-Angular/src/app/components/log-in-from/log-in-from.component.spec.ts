import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogInFromComponent } from './log-in-from.component';

describe('LogInFromComponent', () => {
  let component: LogInFromComponent;
  let fixture: ComponentFixture<LogInFromComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LogInFromComponent]
    });
    fixture = TestBed.createComponent(LogInFromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
