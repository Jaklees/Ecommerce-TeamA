import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { AuthenticationService } from './services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean>{
      return this.authService.isLoggedIn
      .pipe(
        take(1),
        map((isLoggedIn: boolean) => {
          if (!isLoggedIn){
            this.router.navigate(['/login']);  // {4}
            return false;
          }
          return true;
        })
      )
  }

}
