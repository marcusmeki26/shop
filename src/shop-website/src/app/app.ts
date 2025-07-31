import { Component, signal } from '@angular/core';
import { Auth } from './service/auth';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App{
  protected readonly title = signal('shop-website');
  private redirected = false;

  constructor(private router: Router, private authService: Auth){
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd && !this.redirected) {
        const url = event.urlAfterRedirects;

        const token = localStorage.getItem('access_token');
        const refresh = localStorage.getItem('refresh_token');

        if (token && refresh && (url === '/' || url === '/login')) {
          const role = this.authService.getRoleFromToken();

          if (!role) {
            this.router.navigate(['']);
          } else if (role.includes('ROLE_USER')) {
            this.router.navigate(['/user']);
          } else if (role.includes('ROLE_ADMIN')) {
            this.router.navigate(['/admin']);
          } else if (role.includes('ROLE_OWNER')) {
            this.router.navigate(['/owner']);
          }

          this.redirected = true;
        }
      }
    });
  }
}
