import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const accessToken = localStorage.getItem("access_token");
    
    // If there is an access token pass together with the Authorization
    if(accessToken){
      const cloned = req.clone({
        setHeaders: {
          Authorization: `Bearer ${accessToken}`
        }
      })
      return next.handle(cloned);
    }

    // If there is none, pass the request as is
    return next.handle(req);
  }
}
