import { BLANK_PATH, CUSTOMER_PORTAL_ROUTING } from '../globals';
import { CustomerPortalComponent } from './customer-portal.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './_components/home/home.component';
import { ShopComponent } from './_components/shop/shop.component';
import { AboutUsComponent } from './_components/about-us/about-us.component';
import { ProfileComponent } from './_components/profile/profile.component';
import { BasketComponent } from './_components/basket/basket.component';
import { PostComponent } from './_components/post/post.component';
import { ProductComponent } from './_components/product/product.component';

const routes: Routes = [
  {
    path: BLANK_PATH,
    component: CustomerPortalComponent,
    children: [
      {
        path: CUSTOMER_PORTAL_ROUTING.HOME, component: HomeComponent
      },
      {
        path: CUSTOMER_PORTAL_ROUTING.SHOP, component: ShopComponent
      },
      {
        path: CUSTOMER_PORTAL_ROUTING.ABOUT, component: AboutUsComponent
      },
      {
        path: CUSTOMER_PORTAL_ROUTING.PROFILE, component: ProfileComponent
      },
      {
        path: CUSTOMER_PORTAL_ROUTING.CART, component: BasketComponent
      },
      {
        path: 'post/:uuid', component: PostComponent
      },
      {
        path: 'product/:uuid', component: ProductComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class CustomerPortalRoutingModule {
}
