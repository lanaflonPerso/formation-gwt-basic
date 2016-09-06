package fr.lteconsulting.client;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.lteconsulting.client.map.GoogleMapsWidget;
import fr.lteconsulting.client.map.Map;
import fr.lteconsulting.shared.Personne;
import fr.lteconsulting.shared.PersonnesService;
import fr.lteconsulting.shared.PersonnesServiceAsync;

public class Application implements EntryPoint
{
	private FormulairePersonne formulaire = new FormulairePersonne();
	private Button okButton = new Button( "Valider" );
	GoogleMapsWidget map = new GoogleMapsWidget( 49.1203, 6.1778 );

	private CellList<Personne> cellList;
	private Personne editedPersonne;

	private PersonnesServiceAsync personnesService = GWT.create( PersonnesService.class );

	private ListDataProvider<Personne> dataProvider = new ListDataProvider<>();

	@Override
	public void onModuleLoad()
	{
		Scheduler.get().scheduleFixedDelay( () -> {
			if( Map.googleMapsInitialized() )
			{
				return false;
			}
			return true;
		}, 250 );

		initUi();
		initHandlers();

		dataProvider.addDataDisplay( cellList );

		personnesService.getPersonnes( new AsyncCallback<List<Personne>>()
		{
			@Override
			public void onSuccess( List<Personne> result )
			{
				dataProvider.getList().clear();
				dataProvider.getList().addAll( result );
			}

			@Override
			public void onFailure( Throwable caught )
			{
				Window.alert( "Une erreur s'est produite : " + caught );
			}
		} );
	}

	private void initUi()
	{
		cellList = new CellList<Personne>( new AbstractCell<Personne>()
		{
			@Override
			public void render( Context context, Personne value, SafeHtmlBuilder sb )
			{
				sb.append( SafeHtmlUtils.fromString( value.getPrenom() ) );
				sb.append( SafeHtmlUtils.fromSafeConstant( " " ) );
				sb.append( SafeHtmlUtils.fromTrustedString( "<b>" ) );
				sb.append( SafeHtmlUtils.fromString( value.getNom() ) );
				sb.append( SafeHtmlUtils.fromTrustedString( "</b>" ) );
			}
		} );
		cellList.setKeyboardSelectionPolicy( KeyboardSelectionPolicy.ENABLED );
		cellList.setPageSize( 500 );

		MenuBar fileMenu = new MenuBar( true );

		fileMenu.addItem( "Générer", () -> Window.alert( "Not yet implemented" ) );

		MenuBar menu = new MenuBar();
		menu.addItem( "File", fileMenu );

		VerticalPanel vp = new VerticalPanel();
		vp.add( formulaire );
		vp.add( okButton );

		DockLayoutPanel layout = new DockLayoutPanel( Unit.EM );
		layout.addNorth( menu, 2 );
		layout.addWest( new ScrollPanel( cellList ), 14 );
		layout.addEast( map, 44 );
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
			map.addMarker( 49.1202 + Math.random(), 6.1779 + Math.random(), editedPersonne.getNom() );

			formulaire.updatePersonneFromForm( editedPersonne );

			int personneIndex = dataProvider.getList().indexOf( editedPersonne );
			dataProvider.getList().set( personneIndex, editedPersonne );
		} );
	}
}