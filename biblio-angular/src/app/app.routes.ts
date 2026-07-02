import { Routes } from '@angular/router';
import { authGuard } from './guard/auth-guard';
import { AuteurPage } from './page/auteur-page/auteur-page';
import { CollectionPage } from './page/collection-page/collection-page';
import { EditeurPage } from './page/editeur-page/editeur-page';
import { LivrePage } from './page/livre-page/livre-page';
import { LoginPage } from './page/login-page/login-page';


export const routes: Routes = [
  { path: "editeur", component: EditeurPage, canActivate: [authGuard] },
  { path: "livre", component: LivrePage, canActivate: [authGuard] },
  { path: "auteur", component: AuteurPage, canActivate: [authGuard] },
  { path: "collection", component: CollectionPage, canActivate: [authGuard] },
  { path: "login", component: LoginPage, canActivate: [] }
  //{ path: "utilisateur", component: CollectionPage, canActivate: [] }
];
