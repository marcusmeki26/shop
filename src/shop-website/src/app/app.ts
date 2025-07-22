import { Component, signal } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Auth } from './service/auth';
import { HttpRequest } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('shop-website');
}
