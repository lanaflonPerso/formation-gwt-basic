package fr.lteconsulting.client.map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class GoogleMapsWidget extends Widget
{
	private Map map;

	public GoogleMapsWidget()
	{
		this( 49.1203, 6.1778 );
	}

	public GoogleMapsWidget( double lat, double lng )
	{
		Element element = DOM.createDiv();
		setElement( element );

		Map.Options options = new Map.Options();
		options.center = LatLngLiteral.create( lat, lng );
		options.mapTypeId = "satellite";
		options.scrollwheel = true;
		options.zoom = 8;

		map = new Map( element, options );
	}

	public Marker addMarker( double lat, double lng, String title, boolean draggable )
	{
		return Marker.addMarker( map, LatLngLiteral.create( lat, lng ), title, draggable );
	}

	public void setCenter( double lat, double lng )
	{
		map.setCenter( LatLngLiteral.create( lat, lng ) );
	}
}
