cordova.define( "com.rvm.myplug.infotelefonoplugin",

	function( require, exports, module) {
		
	//  var contactsPlugin = require( 'org.apache.cordova.contacts');    //Previamente añadido con "phonegap local plugin add org.apache.cordova.contacts"
		var exec = require( 'cordova/exec');
		var InfoTelefono = require( './InfoTelefono');                   //Este está en nuestro namespace, por eso lo cargamos con ./

		var InfoTelefonoPlugin = function() {
			
		};
		
		InfoTelefonoPlugin.prototype.obtenerInfo = function( success, fail){
			exec( function(jsonJava){
					var resultado = new InfoTelefono();
					resultado.imei = jsonJava.imei;
					resultado.numero = jsonJava.numero;
					resultado.imsi = jsonJava.imsi;
					success( resultado);
				}, /*esto apunta a una funcion de callback*/ 
				fail,   /*lo mismo*/ 
				'InfoTelefonoPlugin', 
				'OBTENER_INFO_ACCION',
				[]
			);
		};
		
		module.exports = new InfoTelefonoPlugin();
	}

);