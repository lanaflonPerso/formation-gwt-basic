package fr.lteconsulting.client;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
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

public class FormulairePersonneUiBinder extends Composite implements IFormulairePersonne
{
	@UiField
	TextBox nom;

	@UiField
	TextBox prenom;

	@UiField
	DatePicker dateNaissance;

	@UiField
	PasswordTextBox motDePasse;

	@UiField
	ListBox sexe;

	@UiField
	CheckBox accepteMarketing;

	@UiField
	GoogleMapsWidget map;

	private Marker marker = null;

	@UiTemplate( "FormulairePersonneUiBinder.ui.xml" )
	interface MyUiBinder extends UiBinder<Widget, FormulairePersonneUiBinder>
	{
	}

	private static final MyUiBinder binder = GWT.create( MyUiBinder.class );

	public FormulairePersonneUiBinder()
	{
		initWidget( binder.createAndBindUi( this ) );

		initListBox();

		marker = map.addMarker( 0, 0, "Domicile", true );
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
