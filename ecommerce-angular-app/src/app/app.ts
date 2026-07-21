import { Component, signal, inject, OnInit } from '@angular/core';
import Keycloak, { KeycloakProfile } from "keycloak-js";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css',
})
export class App implements OnInit{
  protected readonly title = signal('ecommerce-angular-app');
  private readonly  keycloak = inject(Keycloak);
  public profile = signal<KeycloakProfile | null>(null);

  ngOnInit() {
    if(this.keycloak.authenticated){
      this.keycloak.loadUserProfile().then(p => this.profile.set(p));
    }
  }

  hasRole(role: string): boolean {
    return this.keycloak.tokenParsed?.realm_access?.roles?.includes(role) ?? false;
  }

  async handleLogin() {
    await this.keycloak.login({
      redirectUri: window.location.origin
    });
  }

  async handleLogout() {
    this.keycloak.logout({
      redirectUri: window.location.origin
    });
  }

}
