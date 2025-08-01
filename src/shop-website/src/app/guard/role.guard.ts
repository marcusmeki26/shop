import { Injectable } from "@angular/core";
import { CanActivate,ActivatedRouteSnapshot, RouterStateSnapshot, Router, GuardResult, MaybeAsync } from "@angular/router";
import { Auth } from "../service/auth";

@Injectable({
  providedIn: "root"
})
export class Role implements CanActivate{
  constructor(private router: Router, private authService: Auth){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRole = route.data['expectedRole'];
    const userRole = this.authService.getRoleFromToken();

    if(userRole === undefined){
      return false;
    }else if(!userRole.includes(expectedRole)){
      this.router.navigate(['']);
      return false;
    }

    return true;
  }
}