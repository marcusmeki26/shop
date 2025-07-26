import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayUsersTable } from './display-users-table';

describe('DisplayUsersTable', () => {
  let component: DisplayUsersTable;
  let fixture: ComponentFixture<DisplayUsersTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DisplayUsersTable]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayUsersTable);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
