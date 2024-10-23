import {Component, OnInit} from '@angular/core';
import {MenuItemComponent} from "../menuitem/menu-item.component";
import {CommonModule} from '@angular/common';
import { PrimeModule } from '../../modules/prime.module';

@Component({
  selector: 'app-menu-wrapper',
  standalone: true,
  imports: [MenuItemComponent, CommonModule, PrimeModule],
  templateUrl: './menu-wrapper.component.html',
  styleUrl: './menu-wrapper.component.scss'
})
export class MenuWrapperComponent implements OnInit {

  model: any[] = [];

  ngOnInit() {
    this.model = [
      {
        label: 'home',
        items: [
          {label: 'dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/']}
        ]
      },
      {
        label: 'administration',
        icon: 'pi pi-fw pi-check-square',
        items: [
          {label: 'work_record', icon: 'pi pi-fw pi-list', routerLink: ['/timetable']},
          {label: 'employees', icon: 'pi pi-fw pi-user', routerLink: ['/user']},
          {label: 'structure', icon: 'pi pi-fw pi-sitemap', routerLink: ['/departmentUnit']},
          {label: 'locations', icon: 'pi pi-fw pi-building', routerLink: ['/location']},
          {label: 'devices', icon: 'pi pi-fw pi-tablet', routerLink: ['/device']},
          {label: 'cards', icon: 'pi pi-fw pi-id-card', routerLink: ['/card']},
          {label: 'roles', icon: 'pi pi-fw pi-code', routerLink: ['/role']},
          {label: 'permissions', icon: 'pi pi-fw pi-key', routerLink: ['/permission']},
          {label: 'shifts', icon: 'pi pi-fw pi-replay', routerLink: ['/shift']},
        ]
      }
    ];
  }
}
