import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TranslationService {
  private translations: any = {};
  private currentLang: string = 'en';

  private http = inject(HttpClient);

  loadTranslations(lang: string): Observable<any> {
    return this.http.get(`/assets/i18n/${lang}.json`).pipe(
      map((translations: any) => {
        this.translations = translations;
        this.currentLang = lang;
        return translations;
      }),
      catchError(() => of({}))
    );
  }

  translate(key: string, replace: string = ""): string {
    const keys = key.split('.');
    let result = this.translations;
    for (const k of keys) {
      result = result ? result[k] : key;
    }
    return result?.replace("${}", replace) || key;
  }

  setLanguage(lang: string) {
    this.loadTranslations(lang).subscribe();
  }
}
