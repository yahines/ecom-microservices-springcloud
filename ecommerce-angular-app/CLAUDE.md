# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
ng serve          # dev server at http://localhost:4200 (default config: development)
ng build          # production build to dist/ (default config: production)
npm run watch     # rebuild on change, development configuration
ng test           # run unit tests with Vitest
```

Run a single spec by passing a path/pattern to the Vitest-backed test builder, e.g.
`ng test --include src/app/guards/auth-guard.spec.ts`.

There is no configured e2e runner despite the README mention. Formatting is Prettier
(`printWidth: 100`, `singleQuote: true`, Angular parser for `*.html`).

## Architecture

This is the Angular frontend for a Spring Cloud microservices e-commerce system. The backend
services live in the parent directory (`../gateway-service`, `../inventory-mono`,
`../customer-service`, `../discovery-service`, `../config-service`, etc.). The frontend talks to
those services over HTTP and authenticates against Keycloak.

**NgModule-based, not standalone.** The CLI schematics are configured with `standalone: false`
(see `angular.json`), so new components/directives/pipes are generated as non-standalone and must
be declared in `AppModule` (`src/app/app-module.ts`). Bootstrap is the classic
`platformBrowser().bootstrapModule(AppModule)` in `src/main.ts`.

**Authentication & authorization run through Keycloak** via `keycloak-angular` + `keycloak-js`,
wired entirely in `AppModule` providers:
- `provideKeycloak(...)` — realm `nesrine-dev-realm`, client `ecom-ang`, Keycloak server at
  `http://localhost:8090`. Uses `onLoad: 'check-sso'` with a silent SSO iframe served from
  `public/silent-check-sso.html`.
- `includeBearerTokenInterceptor` attaches the access token **only** to requests matching
  `urlCondition` — currently the regex `^http://localhost:8082(/.*)?$` (the API gateway/backend
  origin). If you add calls to a new backend origin, extend this `INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG`
  pattern or the token won't be sent.

**Route guarding is role-based.** Routes in `src/app/app-routing-module.ts` carry a
`data: { role: [...] }` array and are protected by `canActivateAuthRole`
(`src/app/guards/auth-guard.ts`), built with `createAuthGuard`. The guard checks Keycloak **realm
roles** (`grantedRoles.realmRoles`) — not client/resource roles. Unauthenticated users are pushed
to the Keycloak login; authenticated-but-unauthorized users are redirected to `/forbidden` (note:
no `/forbidden` route is currently defined). Example: `/products` requires realm role `ADMIN`,
`/customers` requires `USER`.

**Component-level role checks** use `App.hasRole()` (`src/app/app.ts`), which reads
`keycloak.tokenParsed?.realm_access?.roles`. The navbar in `app.html` uses `*ngIf="hasRole('ADMIN')"`
to conditionally show links and `profile` to toggle login/logout UI.

**Backend endpoints** are currently hardcoded absolute URLs in components (no environment files),
e.g. `Products` fetches `http://localhost:8082/monoApi/products`. Keep the port/origin in sync with
both the bearer-token interceptor pattern and the running gateway.

Bootstrap 5 CSS/JS and bootstrap-icons are loaded globally via `angular.json` `styles`/`scripts`.