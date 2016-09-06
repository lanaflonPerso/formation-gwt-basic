package fr.lteconsulting.client.map;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class LatLngLiteral
{
	public double lat;
	public double lng;

	public LatLngLiteral()
	{
		// vide, implémenté en JS
	}

	@JsOverlay
	public static LatLngLiteral create( double lat, double lng )
	{
		LatLngLiteral res = new LatLngLiteral();

		res.lat = lat;
		res.lng = lng;

		return res;
	}
}