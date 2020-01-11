import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./ui/seller-panel/login/login.component";
import {SellerPanelComponent} from "./ui/seller-panel/panel/seller-panel.component";
import {AuthGuard} from "./authentication/auth.guard";


const routes: Routes = [
  {path: 'login-panel', component: LoginComponent},
  {path: 'control-panel', component: SellerPanelComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: 'login-panel'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
