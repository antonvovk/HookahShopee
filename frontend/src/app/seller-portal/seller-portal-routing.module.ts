import { BLANK_PATH, CUSTOMER_PORTAL_ROUTING } from '../globals';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { SellersComponent } from './sellers/sellers.component';


const routes: Routes = [
  {
    path: BLANK_PATH,
    component: SellersComponent,
    children: []
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
