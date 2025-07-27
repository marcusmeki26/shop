import { Component } from '@angular/core';
import { User } from '../../service/user';
import { Observable } from 'rxjs';
import { Category } from '../../model/category.model';
import { Product } from '../../model/products.model';

@Component({
  selector: 'app-user-landing-page',
  standalone: false,
  templateUrl: './user-landing-page.html',
  styleUrl: './user-landing-page.css'
})
export class UserLandingPage {
  categories$: Observable<Category[]>;
  products$: Observable<Product[]>;

  constructor(private userService: User){ 
    this.categories$ = this.userService.getCategories();
    this.products$ = this.userService.getProducts();
  }
}
