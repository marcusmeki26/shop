import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopDetails } from './shop-details';

describe('ShopDetails', () => {
  let component: ShopDetails;
  let fixture: ComponentFixture<ShopDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShopDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
