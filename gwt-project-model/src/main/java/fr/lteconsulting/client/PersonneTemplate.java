package fr.lteconsulting.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;

public interface PersonneTemplate extends SafeHtmlTemplates
{
	public static PersonneTemplate INSTANCE = GWT.create( PersonneTemplate.class );

	@Template( "<i>{0}</i> <b>{1}</b>" )
	SafeHtml display( String prenom, String nom );
}