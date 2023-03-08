import { Injectable } from '@angular/core';




@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    USER_SESSION_TOKEN = 'token';


    constructor() { }


    registerSuccessfulLogin(token: string) {
        localStorage.setItem(this.USER_SESSION_TOKEN, token)
    }

    logout() {
        localStorage.removeItem(this.USER_SESSION_TOKEN)
    }

    isUserLoggedIn() {
        let user = localStorage.getItem(this.USER_SESSION_TOKEN)
        if (user === null) return false
        return true
    }

    getUserToken(): string | null {
        return localStorage.getItem(this.USER_SESSION_TOKEN)
    }
}