import { Component, OnDestroy, OnInit } from '@angular/core';
import { Auth } from '../../service/auth';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { filter, Subscription } from 'rxjs';

@Component({
  selector: 'app-user-layout',
  standalone: false,
  templateUrl: './user-layout.html',
  styleUrl: './user-layout.css'
})
export class UserLayout implements OnInit, OnDestroy{

  private routerSubscription!: Subscription;

  username: string = "";
  currentUrl: string | undefined = "";
  placeholder: string = "";
  placeholderText: string | undefined = "";

  constructor(private router: Router, private authService: Auth, private route: ActivatedRoute){}

  searchForm = {
    searchItem: ''
  }

  searchProduct(searchForm: NgForm) {
    const keyword = searchForm.value.searchItem.trim();
    const hasKeyword = keyword !== "";

    if(this.currentUrl === "products" || this.currentUrl === "user"){
      if(hasKeyword){
        this.router.navigate(['products'], {
          relativeTo: this.route,
          queryParams: { keyword }
        });
      }
    }else{
      const queryParams = hasKeyword ? { keyword } : undefined;
      this.router.navigate([`category/${this.currentUrl}`], {
        relativeTo: this.route,
        queryParams
      });
    }
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }

  // Method to update the placeholder
  private updatePlaceHolder(url: string){
    let fullUrl = url;
    this.currentUrl = fullUrl.split("?")[0].split("/").pop();
    if(this.currentUrl == "products" || this.currentUrl == "user"){
      this.placeholder = "Search for anything!";
    }else{
      this.placeholderText = this.currentUrl?.replaceAll("-", " ");
      this.placeholder = `Search in ${this.placeholderText}`;
    }
  }

  ngOnInit(): void {
    // Initializing the placeholder
    this.updatePlaceHolder(this.router.url);

    // Handles URL changes to assign proper placeholder
    this.routerSubscription = this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event: NavigationEnd) => {
      this.updatePlaceHolder(event.url);
    });

    // assinging username
    let username = this.authService.getUsername();
    if(username === undefined){
      localStorage.clear();
      this.router.navigate(['']);
    }else{
      this.username = username;
    }
  }

  ngOnDestroy(): void {
    this.routerSubscription?.unsubscribe();
  }
}
