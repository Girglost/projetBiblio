import { Routes } from '@angular/router';
import { authGuard } from './guard/auth-guard';
import { AuteurPage } from './page/auteur-page/auteur-page';
import { AvisPage } from './page/avis-page/avis-page';
import { CollectionPage } from './page/collection-page/collection-page';
import { EditeurPage } from './page/editeur-page/editeur-page';
import { LivrePage } from './page/livre-page/livre-page';
import { LoginPage } from './page/login-page/login-page';
import { UtilisateurPage } from './page/utilisateur-page/utilisateur-page';


export const routes: Routes = [
  { path: "", redirectTo: "/livre", pathMatch: "full" },
  { path: "editeur", component: EditeurPage, canActivate: [authGuard] },
  { path: "livre", component: LivrePage, canActivate: [authGuard] },
  { path: "auteur", component: AuteurPage, canActivate: [authGuard] },
  { path: "collection", component: CollectionPage, canActivate: [authGuard] },
  { path: "utilisateur", component: UtilisateurPage, canActivate: [authGuard] },
  { path: "avis", component: AvisPage, canActivate: [authGuard] },
  { path: "login", component: LoginPage }

];
