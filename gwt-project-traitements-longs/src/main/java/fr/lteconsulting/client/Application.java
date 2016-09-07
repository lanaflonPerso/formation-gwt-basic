package fr.lteconsulting.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Application implements EntryPoint
{
	Button bTraitementAsynchrone = new Button( "Go (asynchrone) !" );

	@Override
	public void onModuleLoad()
	{
		Button bTraitementSynchrone = new Button( "Go (synchrone) !" );

		RootPanel.get().add( bTraitementSynchrone );
		RootPanel.get().add( bTraitementAsynchrone );
		RootPanel.get().add( new TextBox() );

		VerticalPanel panel = new VerticalPanel();
		RootPanel.get().add( new ScrollPanel( panel ) );

		bTraitementSynchrone.addClickHandler( event -> traitementSynchrone( panel, 200000 ) );
		bTraitementAsynchrone.addClickHandler( event -> traitementAsynchrone( panel, 200000 ) );
	}

	private void traitementSynchrone( VerticalPanel panel, int nbIterations )
	{
		for( int i = 0; i < nbIterations; i++ )
		{
			uniteDeTraitement( panel, i );
		}
	}

	private void traitementAsynchrone( VerticalPanel panel, int nbIterations )
	{
		bTraitementAsynchrone.setEnabled( false );

		Scheduler.get().scheduleIncremental( new RepeatingCommand()
		{
			int i = 0;

			@Override
			public boolean execute()
			{
				uniteDeTraitement( panel, i );

				i++;

				if( i < nbIterations )
				{
					return true;
				}
				else
				{
					bTraitementAsynchrone.setEnabled( true );
					return false;
				}
			}
		} );
	}

	private void uniteDeTraitement( VerticalPanel panel, int i )
	{
		TextBox tb = new TextBox();
		panel.add( tb );

		double d = Math.sqrt( i );

		tb.setText( "valeur : " + d );
	}
}