package fr.lteconsulting.client;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

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

	public FormulairePersonne()
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
	}

	public void updateFormFromPersonne( Personne p )
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

		return table;
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
