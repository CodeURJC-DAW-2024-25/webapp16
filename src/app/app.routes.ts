import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './pages/home/home.component';
import { ErrorComponent } from './shared/error/error.component';
import { ConcertComponent } from './pages/concert/concert.component';
import { ClubbingComponent } from './pages/clubbing/clubbing.component';
import { AdminComponent } from './pages/admin/admin.component'; // Asegúrate de importar el componente AdminComponent
import { HttpClientModule } from '@angular/common/http';
import { ProfileComponent } from './pages/profile/profile.component';
export const routes: Routes = [
  { path: '', component: HomeComponent },    // Respuesta a /new
  { path: 'home', component: HomeComponent }, // Respuesta a /new/home
  { path: 'concert/:id', component: ConcertComponent },
  
  {path:  'clubbing/:id', component: ClubbingComponent}, // Respuesta a /new/clubbing/:id
  { path: 'clubbing', component: ClubbingComponent }, // Respuesta a /new/clubbing
  { path: 'error', component: ErrorComponent }, // Respuesta a /new/error
  { path: 'admin', component: AdminComponent }, // Respuesta a /new/admin
  { path: 'profile', component: ProfileComponent },
  { path: '**', redirectTo: 'error' }, // Redirige rutas no encontradas

];
@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true , scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled' })],
  exports: [RouterModule],
  declarations: [

  ], // Asegúrate de declarar los componentes aquí si no son standalone
  providers: [],
  bootstrap: [] // Puedes agregar servicios aquí si es necesario
})
export class AppRoutingModule { }