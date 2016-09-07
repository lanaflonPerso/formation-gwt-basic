package fr.lteconsulting.client;

import com.google.gwt.user.client.ui.IsWidget;

import fr.lteconsulting.shared.Personne;

public interface IFormulairePersonne extends IsWidget
{
	void updatePersonneFromForm( Personne personne );

	void updateFormFromPersonne( Personne personne );
}
