package fr.lteconsulting.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "personnes" )
public interface PersonnesService extends RemoteService
{
	List<Personne> getPersonnes();

	Personne createPersonne( Personne personne );

	boolean deletePersonne( String id );

	Personne updatePersonne( Personne personne );
}
