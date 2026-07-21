import { Component, OnInit, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class Products implements OnInit{
  public products = signal<any[]>([]);

  constructor(private http : HttpClient){}

  ngOnInit(){
    this.http.get<any[]>("http://localhost:8082/monoApi/products").subscribe({
      next : data => {
        this.products.set(data);
      },
      error : err => {
        console.log(err);
      }
    })
  }
}
