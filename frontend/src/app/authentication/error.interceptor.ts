import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AuthenticationService} from "../services/authentication.service";
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService, private toasterService: ToastrService) {

  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      // tap(evt => {
      //   if (evt instanceof HttpResponse) {
      //     if (evt.body && evt.body.success)
      //       this.toasterService.success(evt.body.success.message, evt.body.success.title, {positionClass: 'toast-bottom-center'});
      //   }
      // }),
      catchError(err => {
        if (err.status === 401) {
          // auto logout if 401 response returned from api
          this.authenticationService.logout();
          location.reload(true);
        }

        const error = err.error.apierror.message;
        return throwError(error);
      }))
  }
}
