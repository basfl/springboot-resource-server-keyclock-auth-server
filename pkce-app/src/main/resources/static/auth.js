

const authReader = document.getElementById("authReaderButton");
console.log("here")

function authCodeReader() {


    let urlParams = new URLSearchParams(window.location.search);
    const codeVerifer = localStorage.getItem('codeVerifierValue');
    alert("*****" + codeVerifer);

    let authCode = urlParams.get('code'),
        state = urlParams.get('state'),
        error = urlParams.get('error'),
        errorDescription = urlParams.get('error_description');
    ////////////////////////////////////////////////////
    var data = {
        "grant_type": "authorization_code",
        "client_id": "photo-app-PKCE-client",
        "code": authCode,
        "code_verifier": codeVerifer,
        "redirect_uri": "http://localhost:8181/authcodeReader.html"
    };
    console.log("--------->before sending---->", data);
    console.log("--------->before sending---->", data);
    const xhr = new XMLHttpRequest();
    //  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.open("POST", "http://localhost:8080/auth/realms/appsdeveloperblog/protocol/openid-connect/token");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // console(xhr.response);
            // alert(xhr.response);
            localStorage.setItem('reqResponse', xhr.response);
        } else {
            console.log("error");

        };
    }
    xhr.send(data);


    ////////////////////////////////////////////////////

    if (error) {
        window.alert("The following error took place:" + error + ". Description of error:" + errorDescription);
    } else {
        console.log("_____");
        postAuthorize1();
        //   postAuthorize(state, authCode,codeVerifer);
    }
    window.close();

}
authReader.addEventListener('click', authCodeReader);