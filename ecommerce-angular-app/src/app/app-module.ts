import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import {
  provideKeycloak,
  KeycloakAngularModule,
  includeBearerTokenInterceptor,
  createInterceptorCondition,
  INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG,
  IncludeBearerTokenCondition,
} from 'keycloak-angular';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Products } from './ui/products/products';
import { Customers } from './ui/customers/customers';
import { Forbidden } from './ui/forbidden/forbidden';
import { Home } from './ui/home/home';
import { Orders } from './ui/orders/orders';
import { OrderDetails } from './ui/order-details/order-details';

// définit un pattern regex précisant sur quelles URLs le token doit être ajouté
const urlCondition = createInterceptorCondition<IncludeBearerTokenCondition>({
  urlPattern: /^http:\/\/localhost(:\d+)?(\/.*)?$/i,
});

@NgModule({
  declarations: [App, Products, Customers, Forbidden, Home, Orders, OrderDetails],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //     HttpClientModule,
    KeycloakAngularModule,
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(withInterceptors([includeBearerTokenInterceptor])),
    { provide: INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG, useValue: [urlCondition] },
    provideKeycloak({
      config: {
        url: 'http://localhost:8090',
        realm: 'nesrine-dev-realm',
        clientId: 'ecom-ang',
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html',
      },
    }),
  ],
  bootstrap: [App],
})
export class AppModule {}
