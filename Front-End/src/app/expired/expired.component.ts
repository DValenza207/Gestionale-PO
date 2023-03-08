import { Component, OnInit } from "@angular/core";
import { retry } from "rxjs";
import { AuthenticationService } from "../login-dialog/auth.service";

@Component({
    selector: 'app-expired',
    templateUrl: './expired.component.html',
    styleUrls: ['./expired.component.scss']
})

export class ExpiredComponent implements OnInit {

    constructor(private authenticationService: AuthenticationService) { }
    ngOnInit(): void {
        this.authenticationService.logout();
        if (this.authenticationService.isUserLoggedIn()) {
            window.location.reload();
        }
        else if (this.authenticationService.getUserToken == null) {
            window.location.reload();
        }
        window.location.href = "/";
    }
}