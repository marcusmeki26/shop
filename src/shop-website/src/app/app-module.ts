import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from './shared/interceptors/jwt-interceptor';
import { AdminModule } from './admin/admin-module';
import { UserModule } from './user/user-module';
import { ShopOwnerModule } from './shop-owner/shop-owner-module';
import { Login } from '../login/login';
import { Register } from '../register/register';

@NgModule({
  declarations: [
    App,
    Login,
    Register
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AdminModule,
    UserModule,
    ShopOwnerModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [App]
})
export class AppModule { }
