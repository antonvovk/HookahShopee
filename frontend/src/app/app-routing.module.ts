import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellerPanelComponent } from './ui/seller-panel/panel/seller-panel.component';
import { AuthGuard } from './authentication/auth.guard';
import { ShopComponent } from './ui/general/shop/shop.component';
import { MainComponent } from './ui/general/main/main.component';
import { AboutUsComponent } from './ui/general/about-us/about-us.component';
import { ProfileComponent } from './ui/general/profile/profile.component';
import { BasketComponent } from './ui/general/basket/basket.component';
import { PostComponent } from './ui/general/post/post.component';
import { ProductComponent } from './ui/general/product/product.component';

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'post/:uuid', component: PostComponent},
  {path: 'product/:uuid', component: ProductComponent},
  {path: 'shop', component: ShopComponent},
  {path: 'about-us', component: AboutUsComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'shopping-cart', component: BasketComponent},
  {path: 'control-panel', component: SellerPanelComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
