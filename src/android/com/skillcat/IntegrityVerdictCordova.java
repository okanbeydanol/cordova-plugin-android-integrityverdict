/**
 *
 */
package com.skillcat;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import com.google.android.play.core.integrity.model.IntegrityErrorCode;

public class IntegrityVerdictCordova extends CordovaPlugin {
    private static final String TAG = "integrityverdict";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.cordova.getActivity().getApplicationContext())
                == ConnectionResult.SUCCESS) {
            if (action.equals("attest")) {
                String nonce = args.getString(0);

                // Create an instance of a manager.
                IntegrityManager integrityManager = IntegrityManagerFactory.create(this.cordova.getActivity().getApplicationContext());

                // Request the integrity token by providing a nonce.
                Task<IntegrityTokenResponse> integrityTokenResponse = integrityManager.requestIntegrityToken(
                        IntegrityTokenRequest.builder()
                                .setNonce(nonce)
                                .build());

                integrityTokenResponse.addOnSuccessListener(integrityTokenResponse1 -> {
                    String integrityToken = integrityTokenResponse1.token();
                    callbackContext.success(integrityToken);
                });

                integrityTokenResponse.addOnFailureListener(e -> {
                    callbackContext.error("failed "+e.getMessage());
                });
            } else {
                callbackContext.error("failed invalid method");
            }
        } else {
            /** Play services not found */
            callbackContext.error("Play Services not found");
        }
        return true;
    }
}
