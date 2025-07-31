import { Component, OnInit } from '@angular/core';
import { User } from '../../service/user';
import { map, Observable, tap } from 'rxjs';
import { Category } from '../../model/category.model';
import { Product } from '../../model/products.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-landing-page',
  standalone: false,
  templateUrl: './user-landing-page.html',
  styleUrl: './user-landing-page.css'
})
export class UserLandingPage implements OnInit {

  categories$!: Observable<Category[]>;
  products$!: Observable<Product[]>;

  constructor(private router: Router, private userService: User, private route: ActivatedRoute){}
  
  selectedCategory(categoryName: string | undefined) {
    if(categoryName === undefined){
      this.router.navigate([this.router.url]);
      return;
    }

    categoryName = categoryName.replaceAll(" ", "-");

    this.router.navigate([`/category/${categoryName}`]);
  }

  selectedProduct(arg0: string|undefined) {
    throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
    this.categories$ = this.userService.getCategories().pipe(
      map(categories => {
        categories.map(category => {
          category.categoryName = category.categoryName?.replaceAll("-", " ");
        }); 

        return categories;
      })
    );
    this.products$ = this.userService.getProductsOrByName();
  }
}
