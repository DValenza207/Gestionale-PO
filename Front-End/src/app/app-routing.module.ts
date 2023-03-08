import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArticleFormComponent } from './articleForm/articleform.component';
import { ExpiredComponent } from './expired/expired.component';
import { FormFieldOverviewExample } from './forms/form.component';
import { HomeComponent } from './homepage/home.component';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'purchaseOrders', component: FormFieldOverviewExample },
  { path: 'articles', component: ArticleFormComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'logout', component: LoginDialogComponent },
  { path: 'expired', component: ExpiredComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
