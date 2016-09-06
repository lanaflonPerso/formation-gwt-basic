package fr.lteconsulting.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.lteconsulting.shared.Personne;
import fr.lteconsulting.shared.Sexe;

public class Application implements EntryPoint
{
	private FormulairePersonne formulaire = new FormulairePersonne();
	private Button okButton = new Button( "Valider" );

	private CellList<Personne> cellList;
	private Personne editedPersonne;

	ListDataProvider<Personne> dataProvider = new ListDataProvider<>( new ArrayList<>() );

	@Override
	public void onModuleLoad()
	{
		initUi();
		initHandlers();

		dataProvider.addDataDisplay( cellList );

		genererDonnees();
	}

	private void initUi()
	{
		cellList = new CellList<Personne>( new AbstractCell<Personne>()
		{
			@Override
			public void render( Context context, Personne value, SafeHtmlBuilder sb )
			{
				sb.append( SafeHtmlUtils.fromString( value.getPrenom() + " " + value.getNom() ) );
			}
		} );
		cellList.setKeyboardSelectionPolicy( KeyboardSelectionPolicy.ENABLED );
		cellList.setPageSize( 500 );

		MenuBar fileMenu = new MenuBar( true );
		fileMenu.addItem( "Générer", this::genererDonnees );

		MenuBar menu = new MenuBar();
		menu.addItem( "File", fileMenu );

		VerticalPanel vp = new VerticalPanel();
		vp.add( formulaire );
		vp.add( okButton );

		DockLayoutPanel layout = new DockLayoutPanel( Unit.EM );
		layout.addNorth( menu, 2 );
		layout.addWest( cellList, 14 );
		layout.add( vp );

		RootLayoutPanel.get().add( layout );
	}

	private void initHandlers()
	{
		SingleSelectionModel<Personne> selectionModel = new SingleSelectionModel<>();
		cellList.setSelectionModel( selectionModel );
		selectionModel.addSelectionChangeHandler( event -> {
			editedPersonne = selectionModel.getSelectedObject();
			formulaire.updateFormFromPersonne( editedPersonne );
		} );

		okButton.addClickHandler( event -> {
			formulaire.updatePersonneFromForm( editedPersonne );
			dataProvider.getList().set( dataProvider.getList().indexOf( editedPersonne ), editedPersonne );
		} );
	}

	private void genererDonnees()
	{
		for( int i = 0; i < 10; i++ )
		{
			Personne personne = new Personne();
			personne.setNom( Mots.nom() );
			personne.setPrenom( Mots.nom() );
			personne.setDateNaissance( new Date() );
			personne.setMotDePasse( Mots.mot() );
			personne.setSexe( Math.random() > 0.5 ? Sexe.Homme : Sexe.Femme );
			personne.setAccepteMarketing( Math.random() > 0.5 );

			dataProvider.getList().add( personne );
		}
	}
}