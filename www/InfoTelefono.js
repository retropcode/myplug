
cordova.define("com.rvm.myplug.InfoTelefono",
		function( require, exports, module) {

	var InfoTelefono = function InfoTelefono( numero, imei, imsi){
		this.numero = numero;
		this.imei = imei;
		this.imsi = imsi;
	}
	
	InfoTelefono.prototype.numero = null;
	InfoTelefono.prototype.imei = null;
	InfoTelefono.prototype.imsi = null;
	
	module.exports = InfoTelefono;
	
});
