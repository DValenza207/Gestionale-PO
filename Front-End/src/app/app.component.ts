import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from './login-dialog/auth.service';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  isLoggedIn = false;
  title = 'my-app';
  constructor(public dialog: MatDialog, private route: ActivatedRoute,
    private router: Router, private authenticationService: AuthenticationService) { }
  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    console.log('menu ->' + this.isLoggedIn);
  }


  loginDialog() {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '350px',
      data: {},
      disableClose: true
    });

  }

  handleLogout() {
    this.authenticationService.logout();
  }
}
