import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./ui/login/login.component";
import {SellerPanelComponent} from "./ui/seller-panel/panel/seller-panel.component";
import {AuthGuard} from "./authentication/auth.guard";
import {General} from "./ui/general/general.component";


const routes: Routes = [
  {path: 'login-panel', component: LoginComponent},
  {path: '', component: General},
  {path: 'control-panel', component: SellerPanelComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
