import { Component, OnInit, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-details',
  standalone: false,
  templateUrl: './order-details.html',
  styleUrl: './order-details.css',
})
export class OrderDetails implements OnInit{
  orderId :string;
  public orderDetails = signal<any>(null);

  constructor(private http : HttpClient,
              private route : ActivatedRoute){
    this.orderId = this.route.snapshot.params['id']}

  ngOnInit(){
    this.http.get<any>("http://localhost:8084/api/orders/"+this.orderId).subscribe({
          next : data => {
            this.orderDetails.set(data);
            },
          error : err => {
            console.log(err);
          }
        })
  }

  getTotal(): number {
     let total = 0;
     (this.orderDetails()?.productItems ?? []).forEach((item: any) => {
       total += item.price * item.quantity;
     });
     return total;
  }
}
