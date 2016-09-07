package fr.lteconsulting.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import fr.lteconsulting.client.map.GoogleMapsWidget;
import fr.lteconsulting.client.map.LatLng;
import fr.lteconsulting.client.map.Marker;
import fr.lteconsulting.shared.Personne;
import fr.lteconsulting.shared.Sexe;

public class FormulairePersonne extends Composite
{
	private TextBox nom = new TextBox();
	private TextBox prenom = new TextBox();
	private DatePicker dateNaissance = new DatePicker();
	private PasswordTextBox motDePasse = new PasswordTextBox();
	private ListBox sexe = new ListBox();
	private CheckBox accepteMarketing = new CheckBox();
	private GoogleMapsWidget map = new GoogleMapsWidget();
	private Marker marker = null;

	public static void create( AsyncCallback<FormulairePersonne> callback )
	{
		GWT.runAsync( new RunAsyncCallback()
		{
			@Override
			public void onSuccess()
			{
				callback.onSuccess( new FormulairePersonne() );
			}

			@Override
			public void onFailure( Throwable reason )
			{
				callback.onFailure( reason );
			}
		} );
	}

	private FormulairePersonne()
	{
		initListBox();
		initWidget( createUi() );
	}

	public void updatePersonneFromForm( Personne personne )
	{
		if( personne == null )
			return;

		personne.setNom( nom.getValue() );
		personne.setPrenom( prenom.getValue() );
		personne.setDateNaissance( dateNaissance.getValue() );
		personne.setAccepteMarketing( accepteMarketing.getValue() );
		personne.setSexe( getSelectedSexe() );
		personne.setMotDePasse( motDePasse.getText() );
		personne.setLatitude( marker.getPosition().lat() );
		personne.setLongitude( marker.getPosition().lng() );
	}

	public void updateFormFromPersonne( Personne personne )
	{
		if( personne == null )
		{
			nom.setText( "" );
			prenom.setText( "" );
			accepteMarketing.setValue( false );
			selectSexe( Sexe.Femme );
			motDePasse.setText( "" );
			marker.setPosition( new LatLng( 0, 0 ) );
			map.setCenter( 0, 0 );
		}
		else
		{
			nom.setText( personne.getNom() );
			prenom.setText( personne.getPrenom() );
			Date date = personne.getDateNaissance();
			if( date == null )
				date = new Date();
			dateNaissance.setValue( date );
			dateNaissance.setCurrentMonth( date );
			motDePasse.setText( personne.getMotDePasse() );
			selectSexe( personne.getSexe() );
			accepteMarketing.setValue( personne.isAccepteMarketing() );
			marker.setPosition( new LatLng( personne.getLatitude(), personne.getLongitude() ) );
			map.setCenter( personne.getLatitude(), personne.getLongitude() );
		}
	}

	private Widget createUi()
	{
		FlexTable table = new FlexTable();

		table.setText( 0, 0, "Nom" );
		table.setWidget( 0, 1, nom );

		table.setText( 1, 0, "Prénom" );
		table.setWidget( 1, 1, prenom );

		table.setText( 2, 0, "Date de naissance" );
		table.setWidget( 2, 1, dateNaissance );

		table.setText( 3, 0, "Mot de passe" );
		table.setWidget( 3, 1, motDePasse );

		table.setText( 4, 0, "Sexe" );
		table.setWidget( 4, 1, sexe );

		table.setText( 5, 0, "Accepte le marketing" );
		table.setWidget( 5, 1, accepteMarketing );

		table.setText( 6, 0, "Domicile" );
		table.setWidget( 6, 1, map );

		map.setSize( "30em", "30em" );
		marker = map.addMarker( 0, 0, "Domicile", true );

		return table;
	}

	private void initListBox()
	{
		for( Sexe value : Sexe.values() )
			sexe.addItem( "" + value, "" + value );
		sexe.addItem( "", "" );
	}

	private void selectSexe( Sexe value )
	{
		sexe.setSelectedIndex( value != null ? value.ordinal() : Sexe.values().length );
	}

	private Sexe getSelectedSexe()
	{
		int index = sexe.getSelectedIndex();
		if( index >= Sexe.values().length )
			return null;

		return Sexe.values()[index];
	}
}
