
var exec = require("cordova/exec");

var PLUGIN_NAME = "integrityverdict";

var integrityverdict = {
  attest: function(nonce, cb, err){
    exec(cb, err, PLUGIN_NAME, "attest", [nonce]);
  }
};

module.exports = integrityverdict;
