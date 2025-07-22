import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: false,
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductList {

  constructor(private router: Router){}

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
