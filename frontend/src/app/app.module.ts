import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './ui/seller-panel/login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {ErrorInterceptor} from "./authentication/error.interceptor";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "./authentication/jwt.interceptor";
import {SellerPanelComponent} from './ui/seller-panel/panel/seller-panel.component';
import {HeaderComponent} from './ui/seller-panel/panel/header/header.component';
import {SidenavComponent} from './ui/seller-panel/panel/sidenav/sidenav.component';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {FlexLayoutModule, FlexModule} from "@angular/flex-layout";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatMenuModule} from "@angular/material/menu";
import {MatSidenavModule} from "@angular/material/sidenav";
import {AuthGuard} from "./authentication/auth.guard";
import {SellersComponent} from './ui/seller-panel/panel/sellers/sellers.component';
import {ClientsComponent} from './ui/seller-panel/panel/clients/clients.component';
import {ManufacturersComponent} from './ui/seller-panel/panel/manufacturers/manufacturers.component';
import {OrdersComponent} from './ui/seller-panel/panel/orders/orders.component';
import {PostsComponent} from './ui/seller-panel/panel/posts/posts.component';
import {MatSelectModule} from "@angular/material/select";
import {AddSellerComponent} from './ui/seller-panel/panel/sellers/add-seller/add-seller.component';
import {ManufacturersAddEditComponent} from './ui/seller-panel/panel/manufacturers/manufacturers-add-edit/manufacturers-add-edit.component';
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {ToastrModule} from "ngx-toastr";
import {ProductComponent} from './ui/seller-panel/panel/manufacturers/product/product.component';
import {QuillModule} from "ngx-quill";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SellerPanelComponent,
    HeaderComponent,
    SidenavComponent,
    SellersComponent,
    ClientsComponent,
    ManufacturersComponent,
    OrdersComponent,
    PostsComponent,
    AddSellerComponent,
    ManufacturersAddEditComponent,
    ProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    FlexModule,
    FlexLayoutModule,
    MatIconModule,
    MatToolbarModule,
    MatMenuModule,
    MatSidenavModule,
    MatSelectModule,
    MatSnackBarModule,
    ToastrModule.forRoot(),
    QuillModule.forRoot()
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
