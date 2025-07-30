import { Component, OnInit } from '@angular/core';
import { Auth } from '../../service/auth';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-layout',
  standalone: false,
  templateUrl: './user-layout.html',
  styleUrl: './user-layout.css'
})
export class UserLayout implements OnInit{
  username: string = "";

  constructor(private router: Router, private authService: Auth, private route: ActivatedRoute){}

  searchForm = {
    searchItem: ''
  }

  searchProduct(searchForm: NgForm) {
    if(typeof(searchForm) == 'string'){ 
      this.router.navigate(['search'], { relativeTo: this.route, queryParams: { keyword: searchForm } });
    }else if(searchForm instanceof NgForm){
      this.router.navigate(['search'], { relativeTo: this.route, queryParams: { keyword: searchForm.value.searchItem } });
    }
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
  }
}
