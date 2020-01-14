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
import {MatPaginatorIntl, MatPaginatorModule} from "@angular/material/paginator";
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
import {ProductComponent} from './ui/seller-panel/panel/manufacturers/manufacturers-add-edit/product/product.component';
import {QuillModule} from "ngx-quill";
import {PostsAddEditComponent} from './ui/seller-panel/panel/posts/posts-add-edit/posts-add-edit.component';
import {getDutchPaginatorIntl} from "./core/paginator/ukr-paginator-intl";
import {General} from './ui/general/general.component';
import {FooterComponent} from './ui/general/footer/footer.component';
import {MainComponent} from './ui/general/main/main.component';
import {AboutUsComponent} from './ui/general/about-us/about-us.component';
import {ProfileComponent} from './ui/general/profile/profile.component';
import {ShopComponent} from './ui/general/shop/shop.component';
import {BasketComponent} from './ui/general/basket/basket.component';

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
    ProductComponent,
    PostsAddEditComponent,
    General,
    FooterComponent,
    MainComponent,
    AboutUsComponent,
    ProfileComponent,
    ShopComponent,
    BasketComponent
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
    AuthGuard,
    {provide: MatPaginatorIntl, useValue: getDutchPaginatorIntl()}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
