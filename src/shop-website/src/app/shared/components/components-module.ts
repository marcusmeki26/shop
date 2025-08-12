import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoadingScreen } from './loading-screen/loading-screen';

@NgModule({
  declarations: [
    LoadingScreen
  ],
  imports: [
    CommonModule
  ],
  exports: [
    LoadingScreen
  ]
})
export class ComponentsModule { }
