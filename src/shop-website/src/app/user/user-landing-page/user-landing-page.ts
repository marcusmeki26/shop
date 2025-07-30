import { Component, OnInit } from '@angular/core';
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
export class UserLandingPage implements OnInit {

  categories$!: Observable<Category[]>;
  products$!: Observable<Product[]>;

  constructor(private userService: User){}

  selectedCategory(arg0: string|undefined) {
    throw new Error('Method not implemented.');
  }
  selectedProduct(arg0: string|undefined) {
    throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
    this.categories$ = this.userService.getCategories();
    this.products$ = this.userService.getProducts();
  }
}
