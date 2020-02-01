import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CUSTOMER_PORTAL_PATH, CUSTOMER_PORTAL_ROUTING, ROOT_PATH, SELLER_PORTAL_PATH } from './globals';
import { AuthGuard } from './_authentication/auth.guard';

const routes: Routes = [
  {path: ROOT_PATH + CUSTOMER_PORTAL_PATH, loadChildren: './customer-portal/customer-portal.module#CustomerPortalModule'},
  {path: ROOT_PATH + SELLER_PORTAL_PATH, loadChildren: './seller-portal/seller-portal.module#SellerPortalModule', canActivate: [AuthGuard]},
  {path: '**', pathMatch: 'full', redirectTo: ROOT_PATH + CUSTOMER_PORTAL_PATH + CUSTOMER_PORTAL_ROUTING.HOME}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
