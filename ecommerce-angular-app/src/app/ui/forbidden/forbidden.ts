import { Component } from '@angular/core';

@Component({
  selector: 'app-forbidden',
  standalone: false,
  template: `
    <div class="container mt-5 text-center">
      <h2 class="text-danger">403 - Accès refusé</h2>
      <p>Vous n'avez pas les droits nécessaires pour accéder à cette page.</p>
      <a routerLink="/" class="btn btn-primary">Retour à l'accueil</a>
    </div>
  `
})
export class Forbidden {}
