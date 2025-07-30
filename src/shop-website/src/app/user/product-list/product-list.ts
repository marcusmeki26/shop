import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../service/user';
import { Observable } from 'rxjs';
import { Product } from '../../model/products.model';

@Component({
  selector: 'app-product-list',
  standalone: false,
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductList implements OnInit{
  products$!: Observable<Product[] | null> | null;
  activeTab: string = "";
  
  constructor(private router: Router, private userService: User, private route: ActivatedRoute){}
  
  sortProducts(clickedButton: string) {
    this.activeTab = clickedButton;
  }

  ngOnInit(): void {
    this.activeTab = 'relevance';
    this.route.queryParamMap.subscribe(params => {
      const keyword = params.get("keyword")
      this.products$ = this.userService.getProductsByName(keyword);  
    });
  }
}
