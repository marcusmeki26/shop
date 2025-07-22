import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {
  // shop owner
  constructor(private router: Router){}

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
