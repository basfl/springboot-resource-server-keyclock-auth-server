const stateValueButton = document.getElementById("stateValueButton");
const codeVerifierValueButton = document.getElementById("codeVerifierValueButton");
const codeChallengeValueButton = document.getElementById("codeChallengeValueButton");
const getAuthCodeButton = document.getElementById("getAuthCodeButton");

const generateState = (length) => {
    let alphaNumericCharacters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let alphaNumericCharactersLength = alphaNumericCharacters.length;
    let stateValue = "";
    for (let i = 0; i < length; i++) {
        stateValue += alphaNumericCharacters.charAt(Math.floor(Math.random() * alphaNumericCharactersLength));
    }

    document.getElementById("stateValue").innerHTML = stateValue;

}
const generateCodeVerifier = () => {
    let returnValue = "";
    let randomByteArray = new Uint8Array(32);
    window.crypto.getRandomValues(randomByteArray);
    returnValue = base64urlencode(randomByteArray);
    document.getElementById("codeVerifierValue").innerHTML = returnValue;
    localStorage.setItem('codeVerifierValue', returnValue);

}

async function generateCodeChallenge() {
    let codeChallengeValue = "";
    let codeVerifier = document.getElementById("codeVerifierValue").innerHTML;
    let textEncoder = new TextEncoder('US-ASCII');
    let encodedValue = textEncoder.encode(codeVerifier);
    let digest = await window.crypto.subtle.digest("SHA-256", encodedValue);
    codeChallengeValue = base64urlencode(Array.from(new Uint8Array(digest)));
    document.getElementById("codeChallengeValue").innerHTML = codeChallengeValue;
}

const base64urlencode = (sourceValue) => {
    let stringValue = String.fromCharCode.apply(null, sourceValue);
    let base64Encoded = btoa(stringValue);
    let base64urlEncoded = base64Encoded.replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '');
    return base64urlEncoded;
}

const getAuthCode = () => {
    var state = document.getElementById("stateValue").innerHTML;
    var codeChallenge = document.getElementById("codeChallengeValue").innerHTML;

    var authorizationURL = "http://localhost:8080/auth/realms/appdeveloper/protocol/openid-connect/auth";
    authorizationURL += "?client_id=photo-app-PKCE-client";
    authorizationURL += "&response_type=code";
    authorizationURL += "&scope=openid";
    authorizationURL += "&redirect_uri=http://localhost:8081/oauthcodeReader.html";
    authorizationURL += "&state=" + state;
    authorizationURL += "&code_challenge=" + codeChallenge;
    authorizationURL += "&code_challenge_method=S256";

    console.log("authURL" + authorizationURL);

    window.open(authorizationURL, 'authorizationRequestWindow', 'width=800,height=600,left=200,top=200');
}

const postAuthorize1=()=>{
    alert("*****",localStorage.getItem('reqResponse'));
}

function postAuthorize(state, authCode, pCodeVerifier) {
    //   const code = pCodeVerifier;
    // alert("state" + state + "authCode" + authCode + "***code->" + pCodeVerifier);
    console.log("--------->before sending---->");
    var data = {
        "grant_type": "authorization_code",
        "client_id": "photo-app-PKCE-client",
        "code": authCode,
        "code_verifier": pCodeVerifier,
        "redirect_uri": "http://localhost:8181/authcodeReader.html"
    };
    console.log("--------->before sending---->", data);
    console.log("--------->before sending---->", data);
    const xhr = new XMLHttpRequest();
    //  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.open("POST", "http://localhost:8080/auth/realms/appsdeveloperblog/protocol/openid-connect/token");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            console(xhr.response);
        } else {
            console.log("error");

        };
    }
    xhr.send(data);

    // const originalStateValue = document.getElementById("stateValue").innerHTML;
    // console.log("originalstaevalue " + originalStateValue);

    // if (state === originalStateValue) {
    //     console.log("what!!!!!")
    // requestTokens(tempdata);
    // } else {
    //     alert("Invalid state value received");
    // }
}

// const requestTokens = (ele) => {
//   //  console.log("calling requestToken"+cVerifier);
//     // const codeVerifier = document.getElementById("codeVerifierValue").innerHTML;
//     console.log(ele);
//     alert(ele);
// }
function postRequestAccessToken(data, status, jqXHR) {
    document.getElementById("accessToken").innerHTML = data["access_token"];
}

stateValueButton.addEventListener('click', generateState.bind(null, 30));
codeVerifierValueButton.addEventListener('click', generateCodeVerifier);
codeChallengeValueButton.addEventListener('click', generateCodeChallenge);
getAuthCodeButton.addEventListener('click', getAuthCode);