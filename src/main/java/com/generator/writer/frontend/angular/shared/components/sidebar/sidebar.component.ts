import {Component, ElementRef} from '@angular/core';
import {MenuItemComponent} from "../menuitem/menu-item.component";
import {MenuWrapperComponent} from "../menuwrapper/menu-wrapper.component";
import { PrimeModule } from '../../modules/prime.module';
@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [MenuItemComponent, PrimeModule, MenuWrapperComponent],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  constructor(public el: ElementRef) {
  }

}
