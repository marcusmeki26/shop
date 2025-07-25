import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, switchMap, throwError } from 'rxjs';
import { Auth } from '../../service/auth';

@Injectable()
export class JwtInterceptor implements HttpInterceptor{

  constructor(private authService: Auth){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const accessToken = this.authService.getAccessToken();
    
    // if the requests includes endpoint without the need of authorization    
    if(request.url.includes('/login') || request.url.includes('/register') || request.url.includes('/refresh')){
      return next.handle(request);
    }

    // If there is an access token pass together with the Authorization
    if(accessToken){
      request = this.addToken(request, accessToken);
    }

    // goes to the request
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => { // if there is an error 401, try to get fresh access token.
        if (error.status === 401) {
          // Token expired, attempt refresh
          return this.authService.refreshToken().pipe(
            switchMap((newAccessToken: string) => {
              request = this.addToken(request, newAccessToken);
              return next.handle(request); // Retry original request with new token
            }),
            catchError((refreshError) => {
              this.authService.logout(); // Logout if refresh fails
              return throwError(refreshError);
            })
          );
        }
        return throwError(error);
      })
    ) as Observable<HttpEvent<any>>;
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
}
