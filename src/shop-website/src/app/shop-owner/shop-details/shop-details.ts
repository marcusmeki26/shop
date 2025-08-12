import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { finalize, Observable, Subscription, take } from 'rxjs';
import { Shop } from '../../model/shop.model';
import { Store } from '@ngxs/store';
import { ShopDetailsState } from '../../state/ShopDetails.state';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { Owner } from '../../service/owner';
import { UpdateShop } from '../../action/ShopDetails.action';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-shop-details',
  standalone: false,
  templateUrl: './shop-details.html',
  styleUrl: './shop-details.css'
})
export class ShopDetails implements OnInit{
  shop$: Observable<Shop> = inject(Store).select(ShopDetailsState.getShop);
  ownerService = inject(Owner);
  store = inject(Store);
  cdr = inject(ChangeDetectorRef);

  isInitialized: boolean = false;
  isEditEnabled: boolean = false;
  isSubmitted: boolean = false;
  formLabel = ['Name', 'Description', 'Phone number', 'Image'];
  formControlsArray!: [string, AbstractControl][];
  shopImg: string = "";

  shopDetails = new FormGroup({
    shopName: new FormControl<string>('', Validators.required),
    description: new FormControl<string>('', Validators.required),
    phoneNumber: new FormControl<string>('', Validators.required),
    shopImg: new FormControl<string>('', Validators.required),
  })

  // ADD METHOD FOR ADDING SHOP AS WELL AS ADDING CONTROLLER TO JAVA
  updateShop() {
    this.isSubmitted = true;
    if(!this.shopDetails.invalid){
      const sanitizedFormvalues: Shop = {
        shopName: this.shopDetails.value.shopName ?? '',
        description: this.shopDetails.value.description ?? '',
        phoneNumber: this.shopDetails.value.phoneNumber ?? '',
        shopImg: this.shopDetails.value.shopImg ?? '',
      }
      this.ownerService.updateShop(sanitizedFormvalues).pipe(
        finalize(() => {
          this.isSubmitted = false;
          this.isEditEnabled = false;
        })
      ).subscribe({
        next: (res) => {
          if(res.status === 200 || res.status === 204){
            this.store.dispatch(new UpdateShop(sanitizedFormvalues));
            window.alert("Updated Successfully");
          }
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
  }

  editShop() {
    this.isEditEnabled = !this.isEditEnabled;
  }

  cancel() {
    this.isEditEnabled = !this.isEditEnabled;
  }

  ngOnInit(): void {
    this.formControlsArray = Object.entries(this.shopDetails.controls);
    this.shop$.pipe(take(1))
    .subscribe({
      next: (value) => {
        if(value){
          this.shopDetails.patchValue(value);
          
          this.ownerService.getShopImage(value.shopImg).subscribe({
            next: (blob) => {
              const objectUrl = URL.createObjectURL(blob);
              this.shopImg = objectUrl;
              this.isInitialized = true;
              this.cdr.detectChanges();
            }
          })
        }
      },
      error: (error) => {
        console.log(error);
      },
    })
  }
}
