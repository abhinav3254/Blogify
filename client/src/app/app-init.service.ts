import { Injectable } from '@angular/core';
import { AuthService } from './components/auth/auth.service';

@Injectable({
    providedIn: 'root'
})
export class AppInitService {
    constructor(private authService: AuthService) { }

    init(): void {
        // This ensures the login status is updated based on the presence of the token
        this.authService.isLoggedIn();
    }
}
