import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Products } from './ui/products/products';
import { Customers } from './ui/customers/customers';


const routes: Routes = [
  {path : "products", component : Products},
  {path : "customers", component : Customers},
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
