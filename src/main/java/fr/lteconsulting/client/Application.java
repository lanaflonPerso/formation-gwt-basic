package fr.lteconsulting.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import fr.lteconsulting.shared.Personne;
import fr.lteconsulting.shared.Sexe;

public class Application implements EntryPoint
{
	private TextBox nom = new TextBox();
	private TextBox prenom = new TextBox();
	private DatePicker dateNaissance = new DatePicker();
	private PasswordTextBox motDePasse = new PasswordTextBox();
	private ListBox sexe = new ListBox();
	private CheckBox accepteMarketing = new CheckBox();

	private Button okButton = new Button( "Valider" );

	@Override
	public void onModuleLoad()
	{
		initListBox();
		initUi();

		Personne personne = new Personne();
		personne.setDateNaissance( new Date() );
		updateFormFromPersonne( personne );
		okButton.addClickHandler( event -> {
			updatePersonneFromForm( personne );
			Window.alert( "Personne mise à jour : " + personne );
		} );
	}

	private void updatePersonneFromForm( Personne personne )
	{
		if( personne == null )
			return;

		personne.setNom( nom.getValue() );
		personne.setPrenom( prenom.getValue() );
		personne.setDateNaissance( dateNaissance.getValue() );
		personne.setAccepteMarketing( accepteMarketing.getValue() );
		personne.setSexe( getSelectedSexe() );
		personne.setMotDePasse( motDePasse.getText() );
	}

	private void updateFormFromPersonne( Personne p )
	{
		if( p == null )
		{
			nom.setText( "" );
			prenom.setText( "" );
			accepteMarketing.setValue( false );
			selectSexe( Sexe.Femme );
			motDePasse.setText( "" );
		}
		else
		{
			nom.setText( p.getNom() );
			prenom.setText( p.getPrenom() );
			dateNaissance.setValue( p.getDateNaissance() );
			dateNaissance.setCurrentMonth( p.getDateNaissance() );
			motDePasse.setText( p.getMotDePasse() );
			selectSexe( p.getSexe() );
			accepteMarketing.setValue( p.isAccepteMarketing() );
		}
	}

	private void initUi()
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

		RootPanel.get().add( table );
		RootPanel.get().add( okButton );
	}

	private void initListBox()
	{
		for( Sexe value : Sexe.values() )
			sexe.addItem( value.name(), value.name() );
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