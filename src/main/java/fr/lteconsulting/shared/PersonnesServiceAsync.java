package fr.lteconsulting.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PersonnesServiceAsync
{
	void getPersonnes( AsyncCallback<List<Personne>> callback );
	
	void createPersonne( Personne personne, AsyncCallback<Personne> callback );

	void deletePersonne( String id, AsyncCallback<Boolean> callback );

	void updatePersonne( Personne personne, AsyncCallback<Personne> callback );
}
