import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { inject } from '@angular/core';
import { AuthGuardData, createAuthGuard } from 'keycloak-angular';
import Keycloak from "keycloak-js";

const isAccessAllowed = async (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  authData: AuthGuardData
): Promise<boolean | UrlTree> => {
  const { authenticated, grantedRoles } = authData;
  const keycloak = inject(Keycloak);

//   const requiredRole = route.data['role'];
  const requiredRoles: string[] = route.data['role'] ?? [];

  console.log('authenticated:', authenticated);
  console.log('requiredRoles:', requiredRoles);
  console.log('realmRoles:', grantedRoles.realmRoles);

  if (!authenticated) {
    // Redirige vraiment vers la page de login Keycloak
    await keycloak.login({ redirectUri: window.location.origin + state.url });
    return false;
  }

//   if (!requiredRole) {
  if (requiredRoles.length === 0) {
    const router = inject(Router);
    return router.parseUrl('/forbidden');
  }

// resourceRoles ne contient que le client technique account (rôles par défaut de Keycloak)
//   const hasRequiredRole = (role: string): boolean =>
//     Object.values(grantedRoles.resourceRoles).some((roles) => roles.includes(role));

//   const hasRequiredRole = (role: string): boolean =>
//     grantedRoles.realmRoles?.includes(role) ?? false;

  const hasRequiredRole = requiredRoles.some((role) =>
    grantedRoles.realmRoles?.includes(role) ?? false
  );

//  console.log('hasRequiredRole result:', hasRequiredRole(requiredRole));
  console.log('hasRequiredRole:', hasRequiredRole);

//   if (authenticated && hasRequiredRole(requiredRole)) {
//  if (hasRequiredRole(requiredRole)) {
  if (hasRequiredRole) {
    return true;
  }

  const router = inject(Router);
  return router.parseUrl('/forbidden');
};

export const canActivateAuthRole = createAuthGuard<CanActivateFn>(isAccessAllowed);

// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };
