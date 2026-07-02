import { Routes } from '@angular/router';
import { AuteurPage } from './page/auteur-page/auteur-page';
import { CollectionPage } from './page/collection-page/collection-page';
import { EditeurPage } from './page/editeur-page/editeur-page';
import { LivrePage } from './page/livre-page/livre-page';
import { UtilisateurPage } from './page/utilisateur-page/utilisateur-page';


export const routes: Routes = [
  { path: "editeur", component: EditeurPage },
  { path: "livre", component: LivrePage },
  { path: "auteur", component: AuteurPage },
  { path: "collection", component: CollectionPage },
  { path: "utilisateur", component: UtilisateurPage}
];
