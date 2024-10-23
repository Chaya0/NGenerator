import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';
import {routes} from './app.routes';
import {provideAnimations} from '@angular/platform-browser/animations';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {authInterceptor} from './core/interceptors/auth.interceptor';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {loadingInterceptor} from "./core/interceptors/loading.interceptor";
import {LocationStrategy, PathLocationStrategy} from '@angular/common';
import {ToastService} from './core/services/toast.service';
import {ConfirmationService, MessageService} from "primeng/api";

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor, loadingInterceptor ])),
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes),
    provideAnimations(),
    provideAnimationsAsync(),
    {provide: LocationStrategy, useClass: PathLocationStrategy},
    {provide: MessageService, useClass: MessageService},
    {provide: ConfirmationService, useClass: ConfirmationService},
    ToastService
  ]
};
