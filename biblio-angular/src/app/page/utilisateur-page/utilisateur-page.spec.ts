import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilisateurPage } from './utilisateur-page';

describe('UtilisateurPage', () => {
  let component: UtilisateurPage;
  let fixture: ComponentFixture<UtilisateurPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UtilisateurPage],
    }).compileComponents();

    fixture = TestBed.createComponent(UtilisateurPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
