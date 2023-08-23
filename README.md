# Cordova Play Integrity API Verdict

A wrapper plugin for play integrity verdicts API on Play Enabled devices.

## Installation

Cordova:
```bash
cordova plugin add cordova-plugin-android-integrityverdict
```

Ionic:
```bash
ionic cordova plugin add cordova-plugin-android-integrityverdict
```
## Usage and Google Play Integrity API guide 
TBA

### Steps for Play Integrity API configuration
https://developer.android.com/google/play/integrity/setup

### Syntax for Attestation
A nonce security token from server side, is also required.
Detail requirements of the nonce are provided in above doc.


```javascript
window.integrityverdict.attest(nonce, successCallback ,errorCallback);
```
This method requests Play Integrity API request to Google server. And returns IntegrityToken that is need to send your server.

#### Success Callbacks
Play Integrity API request is successful and it returns the IntegrityToken (String)<br>
Server uses this token to verdict the device.

### Issues
Post the issues related to this library <a href="https://github.com/SkillCatApp/cordova-plugin-android-integrityverdict/issues"> here </a>. Do provide the device details as below. 
*   Device name & manufacturer
*   Android version 
*   Google Play version
