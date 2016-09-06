package fr.lteconsulting.client.map;

import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "google.maps" )
public class LatLng
{
	public LatLng( double lat, double lng )
	{
		// implémenté en JS
	}

	public native double lat();

	public native double lng();

	public native LatLngLiteral toJSON();

	public native String toString();

	public native String toUrlValue();

	public native String toUrlValue( double precision );
}
