package fr.lteconsulting.client;

import com.google.gwt.core.client.GWT;

import fr.lteconsulting.client.map.LatLng;
import jsinterop.annotations.JsType;

public class GoogleMapsMock
{
	@JsType( namespace = "google.maps", name = "Map" )
	public static class GoogleMapsMapMock
	{
	}

	@JsType( namespace = "google.maps", name = "Marker" )
	public static class GoogleMapsMarkerMock
	{
		public void setPosition( LatLng position )
		{
			GWT.log( "COUCOU" );
		}
	}
}
