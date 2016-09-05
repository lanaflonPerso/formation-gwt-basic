package fr.lteconsulting.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import fr.lteconsulting.shared.Personne;

public class Application implements EntryPoint
{
	private FormulairePersonne formulaire = new FormulairePersonne();
	private Button okButton = new Button( "Valider" );

	@Override
	public void onModuleLoad()
	{
		initUi();

		Personne personne = new Personne();
		personne.setDateNaissance( new Date() );

		formulaire.updateFormFromPersonne( personne );
		okButton.addClickHandler( event -> {
			formulaire.updatePersonneFromForm( personne );
			Window.alert( "Personne mise à jour : " + personne );
		} );
	}

	private void initUi()
	{
		RootPanel.get().add( formulaire );
		RootPanel.get().add( okButton );
	}
}