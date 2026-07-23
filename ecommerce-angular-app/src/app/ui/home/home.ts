import { Component } from "@angular/core";

@Component({
  selector: "app-home",
  standalone: false,
  template: `
    <div class="container mt-5 text-center">
      <h1>Bienvenue sur l application e-commerce</h1>
      <p class="lead">Utilisez le menu pour naviguer.</p>
    </div>
  `
})
export class Home {}
