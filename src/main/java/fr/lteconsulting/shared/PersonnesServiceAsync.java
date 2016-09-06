package fr.lteconsulting.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PersonnesServiceAsync
{
	void getPersonnes( AsyncCallback<List<Personne>> callback );
}
