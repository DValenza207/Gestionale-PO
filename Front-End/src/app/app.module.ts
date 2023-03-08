import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowseComponent } from './browse/browse.component';
import { BrowseArticleComponent } from './browseArticles/browseArticle.component';
import { ModalComponent } from './modal_dialog/modaldialog.component';
import { ArticleFormComponent } from './articleForm/articleform.component';
import { ArticleDialogComponent } from './articleDialog/articledialog.component';
import { HomeComponent } from './homepage/home.component';
import { DeletePOComponent } from './deletePODialog/deletePODialog.component';
import { DeleteArticleComponent } from './deleteADialog/deleteADialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepicker, MatDatepickerModule, MatDateRangePicker } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatDialogModule, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormFieldOverviewExample } from './forms/form.component';
import { FormsModule } from '@angular/forms';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { RegistrationComponent } from './registration/registration.component';
import { HttpService } from './http.service';
import { ExpiredComponent } from './expired/expired.component';



@NgModule({
  declarations: [
    AppComponent,
    BrowseComponent,
    BrowseArticleComponent,
    ModalComponent,
    ArticleFormComponent,
    ArticleDialogComponent,
    HomeComponent,
    DeletePOComponent,
    DeleteArticleComponent,
    FormFieldOverviewExample,
    LoginDialogComponent,
    RegistrationComponent,
    ExpiredComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatPaginatorModule,
    MatToolbarModule,
    MatSortModule,
    MatDialogModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpService,
    multi: true
  },
  {
    provide: MatDialogRef,
    useValue: {}
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
