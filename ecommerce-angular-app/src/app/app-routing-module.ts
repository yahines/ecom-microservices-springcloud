import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Products } from './ui/products/products';
import { Customers } from './ui/customers/customers';
import { Forbidden } from './ui/forbidden/forbidden';
import { canActivateAuthRole } from './guards/auth-guard';


const routes: Routes = [
  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: 'products', component: Products, canActivate: [canActivateAuthRole], data: { role: ['ADMIN'] } },
  { path: 'customers', component: Customers, canActivate: [canActivateAuthRole], data: { role: ['USER'] } },
  { path: 'forbidden', component: Forbidden },
  { path: '**', redirectTo: '/' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
