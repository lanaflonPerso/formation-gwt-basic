package fr.lteconsulting.client.map;

import fr.lteconsulting.client.map.Map.Position;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "google.maps" )
public class Marker
{
	@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
	public static class Options
	{
		public Map map;
		public Position position;
		public String title;
	}

	@JsOverlay
	public static void addMarker( Map map, Position position, String title )
	{
		Options options = new Options();
		options.map = map;
		options.position = position;
		options.title = title;

		new Marker( options );
	}

	public Marker( Options options )
	{
	}
}