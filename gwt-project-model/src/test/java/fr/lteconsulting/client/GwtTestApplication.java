package fr.lteconsulting.client;

import org.junit.Ignore;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.shared.Personne;
import fr.lteconsulting.shared.PersonnesService;
import fr.lteconsulting.shared.PersonnesServiceAsync;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase.
 * Using <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * 
 * If you run the tests using the Maven command line, you will have to
 * navigate with your browser to a specific url given by Maven.
 * See https://gwt-maven-plugin.github.io/gwt-maven-plugin/user-guide/testing.html
 * for details.
 */
public class GwtTestApplication extends GWTTestCase
{
	public String getModuleName()
	{
		return "fr.lteconsulting.ApplicationJUnit";
	}

	@Ignore
	public void testFormulaire()
	{
		FormulairePersonne form = new FormulairePersonne();

		Personne personne = new Personne();
		personne.setNom( "toto" );

		form.updateFormFromPersonne( personne );

		Personne personne2 = new Personne();
		form.updatePersonneFromForm( personne2 );

		assertEquals( "toto", personne2.getNom() );
	}

	public void testBidon()
	{
		TextBox tb = new TextBox();
		tb.setText( "toto" );
		assertEquals( "toto", tb.getText() );
	}

	public void testGreetingService()
	{
		PersonnesServiceAsync greetingService = GWT.create( PersonnesService.class );

		ServiceDefTarget target = (ServiceDefTarget) greetingService;
		target.setServiceEntryPoint( GWT.getModuleBaseURL() + "applicationtests/personnes" );

		delayTestFinish( 10000 );

		Personne p = new Personne();
		p.setNom( "Titi" );

		greetingService.createPersonne( p, new AsyncCallback<Personne>()
		{
			@Override
			public void onSuccess( Personne result )
			{
				assertNotNull( result );
				assertNotNull( result.getId() );
				assertEquals( "Titi", result.getNom() );

				finishTest();
			}

			@Override
			public void onFailure( Throwable caught )
			{
				fail( "Request failure: " + caught.getMessage() );
			}
		} );
	}
}
