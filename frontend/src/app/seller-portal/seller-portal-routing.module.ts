import { BLANK_PATH } from '../globals';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { SellersComponent } from './sellers/sellers.component';
import { ClientsComponent } from './clients/clients.component';
import { ManufacturersComponent } from './manufacturers/manufacturers.component';
import { OrdersComponent } from './orders/orders.component';
import { PostsComponent } from './posts/posts.component';
import { SellerPanelComponent } from './seller-panel.component';


const routes: Routes = [
  {
    path: BLANK_PATH,
    component: SellerPanelComponent,
    children: [
      {
        path: 'sellers', component: SellersComponent
      },
      {
        path: 'clients', component: ClientsComponent
      },
      {
        path: 'manufacturers', component: ManufacturersComponent
      },
      {
        path: 'orders', component: OrdersComponent
      },
      {
        path: 'posts', component: PostsComponent
      },
      {
        path: BLANK_PATH, redirectTo: 'sellers'
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
export class SellerPortalRoutingModule {
}
