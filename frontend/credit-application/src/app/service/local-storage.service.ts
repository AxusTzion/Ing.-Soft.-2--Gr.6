import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  USER_KEY = 'USER_ID';
  // Set a value in local storage
  setUser(value: string): void {
    localStorage.setItem(this.USER_KEY, value);
  }

  isUserActive() {
    var user = this.getUser();
    return user != null;
  }

  // Get a value from local storage
  getUser(): string | null {
    return localStorage.getItem(this.USER_KEY);
  }

  // Remove a value from local storage
  removeUser(): void {
    localStorage.removeItem(this.USER_KEY);
  }

  // Clear all items from local storage
  clear(): void {
    localStorage.clear();
  }
}
