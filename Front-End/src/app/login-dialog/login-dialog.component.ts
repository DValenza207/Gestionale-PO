import { Component, Inject, Injectable, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HttpService } from '../http.service';
import { AuthenticationService } from './auth.service';


export class LoginResponse {
  token!: string
}

export class UserDTO {
  name!: string;
  password!: string;
}
@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {
  name!: string;
  password!: string;


  constructor(public dialogRef: MatDialogRef<LoginDialogComponent>, public restApi: HttpService, @Inject(MAT_DIALOG_DATA) public data: UserDTO, private authenticationService: AuthenticationService) { this.name = data.name, this.password = data.password }

  ngOnInit(): void {
  }

  //quando utente fa il login -> chiama AuthorizationController /login. se autenticazione ok -> salva il token da qualche parte
  //nell'interceptor angular che abbiamo gia' visto metti l'header AUTHORIZATION con "Basic " + token a tutte le chiamate
  login(UserDTO: any) {
    console.log("user", UserDTO);
    this.restApi.login(UserDTO).subscribe((data) => {
      console.log("datatoken", data.token);
      this.authenticationService.registerSuccessfulLogin(data.token);
      window.location.reload();
    });
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
