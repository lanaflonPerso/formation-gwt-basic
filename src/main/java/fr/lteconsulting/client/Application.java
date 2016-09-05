package fr.lteconsulting.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
		MenuBar fileMenu = new MenuBar( true );
		fileMenu.addItem( "Ouvrir", () -> Window.alert( "OUVRIR ?" ) );

		MenuBar menu = new MenuBar();
		menu.addItem( "File", fileMenu );

		VerticalPanel vp = new VerticalPanel();
		vp.add( formulaire );
		vp.add( okButton );

		DockLayoutPanel layout = new DockLayoutPanel( Unit.EM );
		layout.addNorth( menu, 2 );
		layout.addWest( new Label( "Navigation" ), 14 );
		layout.add( vp );

		RootLayoutPanel.get().add( layout );
	}
}