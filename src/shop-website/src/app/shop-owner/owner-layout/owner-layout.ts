import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Auth } from '../../service/auth';
import { Owner } from '../../service/owner';
import { finalize, Observable, tap } from 'rxjs';
import { Shop } from '../../model/shop.model';
import { Store } from '@ngxs/store';
import { AddShop } from '../../action/ShopDetails.action';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-owner-layout',
  standalone: false,
  templateUrl: './owner-layout.html',
  styleUrl: './owner-layout.css',
  animations: [
    trigger('myAnimationTrigger', [
      // Define states
      state('state1', style({
        opacity: 0,
        transform: 'translateX(-100%)'
      })),
      state('state2', style({
        opacity: 1,
        transform: 'translateX(0)'
      })),
      state('imageState1', style({
        rotate: '0deg'
      })),
      state('imageState2', style({
        rotate: '180deg'
      })),

      // Define transitions
      transition('state1 => state2, imageState2 => imageState1', [
        animate('300ms ease-in')
      ]),
      transition('state2 => state1, imageState1 => imageState2', [
        animate('300ms ease-out')
      ]),
      // You can also use wildcards for transitions (e.g., '* => *')
      transition('* => void', [ // When element leaves the view
        animate('300ms ease-out', style({ opacity: 0, transform: 'translateY(0)' }))
      ]),
      transition('void => *', [ // When element enters the view
        style({ opacity: 0, transform: 'translateY(0)' }),
        animate('500ms ease-in', style({ opacity: 1, transform: 'translateY(0)' }))
      ])
    ])
  ]
})
export class OwnerLayout implements OnInit {
  isLoading = true;
  shop$: Observable<Shop> | null = null;
  username = "";
  activeTab: any = "";
  shopName : string = "";

  constructor(private router: Router, 
    private authService: Auth, 
    private ownerService: Owner, 
    private store: Store, 
    private route: ActivatedRoute,
    private cd: ChangeDetectorRef  ){}

  clickedTab(tabName: string){
    this.activeTab = tabName;
    this.router.navigate([tabName], { relativeTo: this.route });
  }
  
  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }

  imageState: string = "imageState1"
  currentState: string = "state1";
  isOrderOpen: boolean = false;
  
  orderTab() {
    this.isOrderOpen = !this.isOrderOpen;
    this.currentState = this.currentState === 'state1' ? 'state2' : 'state1' ;
    this.imageState = this.imageState === 'imageState1' ? 'imageState2' : 'imageState1' ;
  }

  ngOnInit(): void{
    // tap(shop => {
    //     this.shopName = shop.shopName
    //     this.store.dispatch(new AddShop(shop));
    //   }),
    //   finalize(() => {

    //     this.isLoading = false;
    //     this.cd.detectChanges();
    //   })

    let username =  this.authService.getUsername();
    this.ownerService.getShop().subscribe({
      next: (value) => {
        this.shopName = value.shopName;
        this.store.dispatch(new AddShop(value));
      },
      error: (error) => {
        console.log(error);
        this.isLoading = false;
      }, 
      complete: () => {
        this.isLoading = false;
        this.cd.detectChanges();
      }
    });
    
    if(username === undefined){
      localStorage.clear();
      this.router.navigate(['']);
      return;
    }else{
      this.username = username;
    }
    
    const fullUrl = this.router.url;
    this.activeTab = fullUrl.split('?')[0].split('/').pop();
  }
}

