import { Component, OnInit, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  standalone: false,
  templateUrl: './orders.html',
  styleUrl: './orders.css',
})
export class Orders implements OnInit{
  public orders = signal<any[]>([]);

  constructor(private http : HttpClient,
    private router : Router){}

  ngOnInit(){
    this.http.get<any[]>("http://localhost:8084/api/orders").subscribe({
      next : data => {
        this.orders.set(data);
        },
      error : err => {
        console.log(err);
      }
    })
  }

  getOrderDetails(order : any) {
    this.router.navigateByUrl("/order-details/" + order.id);
  }
}
