import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Auth } from '../../service/auth';

@Component({
  selector: 'app-admin-layout',
  standalone: false,
  templateUrl: './admin-layout.html',
  styleUrl: './admin-layout.css'
})
export class AdminLayout implements OnInit { 
  username = "";
  activeTab: any = "";

  constructor(private router: Router, private authService: Auth, private route: ActivatedRoute){}

  clickedTab(tabName: string){
    if(tabName != "dashboard"){
      this.activeTab = tabName;
      this.router.navigate([tabName], { relativeTo: this.route, queryParams: { role: tabName.toUpperCase() } });
    }else{
      this.activeTab = tabName;
      this.router.navigate([tabName], { relativeTo: this.route });
    }
  }
  
  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    const fullUrl = this.router.url;
    this.activeTab = fullUrl.split('?')[0].split('/').pop();
  }
}
