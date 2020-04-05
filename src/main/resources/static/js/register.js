var xmlHttpRequest = new XMLHttpRequest();

function usernameIsExist() {
    var username = document.registerForm.username.value;
    // var url = "usernameIsExist?username="+username;
    var url = "/account/usernameIsExist?username="+username;
    xmlHttpRequest.open("GET",url,true);
    xmlHttpRequest.onreadystatechange = function processResponse()
    {
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
        {
            var responseInfo = xmlHttpRequest.responseXML.getElementsByTagName("msg")[0].firstChild.data;
            window.alert(responseInfo);

            var div1 = document.getElementById('usernameMsg');

            if (responseInfo == "Exist")
            {
                div1.innerHTML = "<font color='red'>用户名已存在</font>";
            }
            else
            {
                div1.innerHTML = "<font color='green'>用户名可用</font>";
            }
        }
    };
    xmlHttpRequest.send();
}
