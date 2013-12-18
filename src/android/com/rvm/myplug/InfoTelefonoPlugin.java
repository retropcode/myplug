package com.rvm.myplug;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;

public class InfoTelefonoPlugin extends CordovaPlugin{
	
	public static final String OBTENER_INFO_ACCION = 
			"OBTENER_INFO_ACCION";
	
	@Override
	public boolean execute( String action, JSONArray args, 
			CallbackContext ctx)
	throws JSONException {
		boolean resultado = false;
		try{
			if( OBTENER_INFO_ACCION.equals(action) == true) {
				JSONObject jsonSuccess = this.obtenerInfoTelefonoImpl();
				//ctx.success(jsonSuccess);
				ctx.sendPluginResult(
					new PluginResult( PluginResult.Status.OK, jsonSuccess)
				);
			} else {
				throw new IllegalArgumentException(
						action + " no soportada ");
			}
			
			resultado = true;
		} catch ( Throwable exc) {
			JSONObject jsonError = new JSONObject(
					"{ \"mensaje\" : \"" + exc.getMessage() + "\"}"
			);
			ctx.sendPluginResult(
					new PluginResult( PluginResult.Status.OK, jsonError)
			);
		}
		
		return resultado;
	}

	private JSONObject obtenerInfoTelefonoImpl() throws JSONException {
		// TODO Auto-generated method stub
		TelephonyManager manager = (TelephonyManager)
				super.cordova.getActivity().getSystemService( Context.TELEPHONY_SERVICE);
		String numero = manager.getLine1Number();
		String imei = manager.getDeviceId();
		String imsi = manager.getSubscriberId();
		
		String jsonString = "{ 'numero': '{0}', 'imei': '{1}', 'imsi': '{2}'}";
		jsonString = jsonString.replaceAll("'", "\"")
				.replace( "{0}", numero == null ? "" : numero)
				.replace( "{1}", imei == null ? "" :imei )
				.replace( "{2}", imsi == null ? "" :imsi );
		
		return new JSONObject( jsonString);
	}	
	

}
