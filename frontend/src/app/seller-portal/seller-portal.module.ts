import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SellerPortalRoutingModule } from './seller-portal-routing.module';
import { SellerPanelComponent } from './seller-panel.component';
import { ClientsComponent } from './clients/clients.component';
import { AdminHeaderComponent } from './header/admin-header.component';
import { ManufacturersComponent } from './manufacturers/manufacturers.component';
import { ManufacturersAddEditComponent } from './manufacturers/manufacturers-add-edit/manufacturers-add-edit.component';
import { ProductsComponent } from './manufacturers/manufacturers-add-edit/products/products.component';
import { OrdersComponent } from './orders/orders.component';
import { PostsComponent } from './posts/posts.component';
import { PostsAddEditComponent } from './posts/posts-add-edit/posts-add-edit.component';
import { SellersComponent } from './sellers/sellers.component';
import { AddSellerComponent } from './sellers/add-seller/add-seller.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { QuillModule } from 'ngx-quill';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { FlexLayoutModule, FlexModule } from '@angular/flex-layout';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    SellerPanelComponent,
    ClientsComponent,
    AdminHeaderComponent,
    ManufacturersComponent,
    ManufacturersAddEditComponent,
    ProductsComponent,
    OrdersComponent,
    PostsComponent,
    PostsAddEditComponent,
    SellersComponent,
    AddSellerComponent,
    SidenavComponent
  ],
  imports: [
    CommonModule,
    SellerPortalRoutingModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    QuillModule,
    MatIconModule,
    MatInputModule,
    FlexModule,
    MatPaginatorModule,
    MatButtonModule,
    MatSelectModule,
    FlexLayoutModule,

  ]
})
export class SellerPortalModule {
}
