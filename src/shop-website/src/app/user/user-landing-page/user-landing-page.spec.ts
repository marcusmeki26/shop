import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLandingPage } from './user-landing-page';

describe('UserLandingPage', () => {
  let component: UserLandingPage;
  let fixture: ComponentFixture<UserLandingPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserLandingPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserLandingPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
