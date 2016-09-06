package fr.lteconsulting.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.lteconsulting.client.Mots;
import fr.lteconsulting.shared.Personne;
import fr.lteconsulting.shared.PersonnesService;
import fr.lteconsulting.shared.Sexe;

public class PersonnesServiceImpl extends RemoteServiceServlet implements PersonnesService
{
	private static final long serialVersionUID = 8040896818489712249L;

	private static List<Personne> database;

	static
	{
		database = new ArrayList<>();
		genererDonnees();
	}

	@Override
	public List<Personne> getPersonnes()
	{
		return database;
	}

	private static void genererDonnees()
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

			database.add( personne );
		}
	}
}
