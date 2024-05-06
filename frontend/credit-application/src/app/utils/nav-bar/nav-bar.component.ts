import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import { LocalStorageService } from '../../service/local-storage.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [MatIconModule, MatToolbarModule, MatButtonModule, RouterModule ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

  constructor(private localStorage : LocalStorageService, private router: Router) {
  }
  isUserActive(): boolean {
    return this.localStorage.isUserActive();
  }
}
