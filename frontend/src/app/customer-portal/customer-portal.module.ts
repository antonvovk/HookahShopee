import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './_components/home/home.component';
import { CustomerPortalComponent } from './customer-portal.component';
import { CustomerPortalRoutingModule } from './customer-portal-routing.module';
import { ProductsSliderComponent } from './_components/home/products-slider/products-slider.component';
import { HeaderComponent } from './_components/header/header.component';
import { FooterComponent } from './_components/footer/footer.component';
import { ShopComponent } from './_components/shop/shop.component';
import { AboutUsComponent } from './_components/about-us/about-us.component';
import { ProfileComponent } from './_components/profile/profile.component';
import { BasketComponent } from './_components/basket/basket.component';
import { LoginComponent } from './_components/profile/login/login.component';
import { RegistrationComponent } from './_components/profile/registration/registration.component';
import { OrderConfirmationComponent } from './_components/basket/order-confirmation/order-confirmation.component';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatBadgeModule } from '@angular/material/badge';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { PostComponent } from './_components/post/post.component';
import { ProductComponent } from './_components/product/product.component';

@NgModule({
  declarations: [
    CustomerPortalComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    ProductsSliderComponent,
    ProductComponent,
    PostComponent,
    ShopComponent,
    AboutUsComponent,
    ProfileComponent,
    BasketComponent,
    LoginComponent,
    RegistrationComponent,
    OrderConfirmationComponent
  ],
  imports: [
    CommonModule,
    CustomerPortalRoutingModule,
    MatStepperModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatSelectModule,
    NgxMatSelectSearchModule,
    MatButtonModule,
    MatInputModule,
    MatBadgeModule,
    CarouselModule,
    FormsModule,
    MatPaginatorModule,
    MatCardModule,
  ]
})
export class CustomerPortalModule {
}
