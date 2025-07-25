import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerLayout } from './owner-layout';

describe('OwnerLayout', () => {
  let component: OwnerLayout;
  let fixture: ComponentFixture<OwnerLayout>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OwnerLayout]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerLayout);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
