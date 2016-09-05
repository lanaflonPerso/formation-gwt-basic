package fr.lteconsulting.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Application implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{

		Label label = new Label( "Saisissez du texte et appuyez sur OK" );
		TextBox textBox = new TextBox();
		Button button = new Button( "Ok" );

		button.addClickHandler( event -> label.setText( textBox.getText() ) );

		RootPanel.get().add( label );
		RootPanel.get().add( textBox );
		RootPanel.get().add( button );
	}
}