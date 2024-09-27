import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private eventSubject = new Subject<any>();
  event$ = this.eventSubject.asObservable();
  
  private loadingEventSubject = new Subject<any>();
  loadingEvent$ = this.loadingEventSubject.asObservable();
  
  emitEvent(data: any) {
    this.eventSubject.next(data);
  }

  emitSearchEvent(loading: boolean){
    this.loadingEventSubject.next(loading)
  }

}
