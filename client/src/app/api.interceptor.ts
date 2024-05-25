import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class APIInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const apiReq = request.clone({
      url: 'http://localhost:8080/' + request.url, setHeaders: {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
      },
    });
    return next.handle(apiReq);
  }
}
