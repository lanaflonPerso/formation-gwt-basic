package fr.lteconsulting.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.lteconsulting.client.Mots;
import fr.lteconsulting.shared.Personne;
import fr.lteconsulting.shared.PersonnesService;
import fr.lteconsulting.shared.Sexe;

public class PersonnesRepository implements PersonnesService
{
	private static Map<String, Personne> database;

	static
	{
		database = new HashMap<>();
		genererDonnees();
	}

	@Override
	public List<Personne> getPersonnes()
	{
		return new ArrayList<>( database.values() );
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

			database.put( personne.getId(), personne );
		}
	}

	@Override
	public Personne createPersonne( Personne personne )
	{
		personne.setId( UUID.randomUUID().toString() );
		database.put( personne.getId(), personne );

		return personne;
	}

	@Override
	public boolean deletePersonne( String id )
	{
		return database.remove( id ) != null;
	}

	@Override
	public Personne updatePersonne( Personne personne )
	{
		database.put( personne.getId(), personne );
		
		return personne;
	}
}
