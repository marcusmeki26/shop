import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopProducts } from './shop-products';

describe('ShopProducts', () => {
  let component: ShopProducts;
  let fixture: ComponentFixture<ShopProducts>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShopProducts]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopProducts);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
