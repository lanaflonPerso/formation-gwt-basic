package fr.lteconsulting.client.map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.client.map.Map.Options;
import fr.lteconsulting.client.map.Map.Position;

public class GoogleMapsWidget extends Widget
{
	private Map map;

	public GoogleMapsWidget( double lat, double lng )
	{
		Element element = DOM.createDiv();
		setElement( element );

		Position position = new Position();
		position.lat = lat;
		position.lng = lng;

		Options options = new Options();
		options.center = position;
		options.mapTypeId = "satellite";
		options.scrollwheel = true;
		options.zoom = 8;

		map = new Map( element, options );
	}

	public void addMarker( double lat, double lng, String title )
	{
		Position position = new Position();
		position.lat = lat;
		position.lng = lng;
		
		Marker.addMarker( map, position, title );
	}
}
