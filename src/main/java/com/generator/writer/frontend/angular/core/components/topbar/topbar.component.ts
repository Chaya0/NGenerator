import { Component, ElementRef, inject, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from '../layout/layout.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { PrimeModule } from '../../prime/prime.modules';
import { LanguageService } from '../../../core/services/language.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { ConfigComponent } from "../config/config.component";
import { TranslationService } from '../../../core/services/translation.service';

@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [CommonModule, RouterModule, PrimeModule, FormsModule, ConfigComponent],
  templateUrl: './topbar.component.html',
  styleUrl: './topbar.component.scss'
})
export class TopbarComponent implements OnInit {
  languageMenuItems!: MenuItem[];
  userMenuItems!: MenuItem[];

  languageService = inject(LanguageService)
  authService = inject(AuthService)
  translationService = inject(TranslationService)
  router = inject(Router)

  ngOnInit() {
    this.userMenuItems = [
      {
        label: this.authService.getUsername(),
        items: [
          {
            separator: true,
          },
          {
            label: this.translationService.translate('manage_profile'),
            icon: 'pi pi-user',
            command: () => {
              this.router.navigate(['/profile']);
            }
          },
          {
            label: this.translationService.translate('logout'),
            icon: 'pi pi-sign-out',
            command: () => {
              this.authService.logout();
            }
          }
        ]
      }
    ];
    this.languageMenuItems = this.createLanguageMenuItems()
  }

  createLanguageMenuItems(): MenuItem[] {
    return this.languageService.getLanguages().map(language => ({
      label: language.fullName,
      icon: language.flag,
      value: language.code,
      command: () => {
        this.switchLanguage(language.code)
        console.log(`Language changed to: ${language.fullName}`);
      }
    }));
  }
  switchLanguage(languageCode: string) {
    this.languageService.changeLanguage(languageCode);
  }

  onConfigButtonClick() {
    this.layoutService.showConfigSidebar();
  }

  @ViewChild('menubutton') menuButton!: ElementRef;

  @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

  @ViewChild('topbarmenu') menu!: ElementRef;

  constructor(public layoutService: LayoutService) { }

}

