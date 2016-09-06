package fr.lteconsulting.client.map;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "google.maps", name = "Map" )
public class Map
{
	@JsProperty( namespace = JsPackage.GLOBAL, name = "googleMapsInitialized" )
	public static native boolean googleMapsInitialized();

	@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
	public static class Options
	{
		public LatLngLiteral center;
		public String mapTypeId;
		public boolean scrollwheel;
		public int zoom;
	}

	public Map( Element element, Options options )
	{
		// Vide car implémenté en JS
	}

	public native void setCenter( LatLngLiteral position );
	
	public native void setCenter( LatLng position );
}
