

    $(document).ready(function () {
    var token = localStorage.getItem("jwtToken");
    if(token==null) window.location.href ="/login";
    function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
}).join(''));
    return JSON.parse(jsonPayload);
}
    var payload = parseJwt(token);
    if(payload.scope=="USER") window.location.href ="/";
    $.ajax({
    url: '/admin',
    method: 'GET',
    headers: {
    'Authorization': 'Bearer ' + token,
    "Content-Type": "application/json"
},
    success: function(data) {
    $("#authen").prepend("<li><a className=\"dropdown-item\" href=\"/myInfo\" style=\"color:green;\"><span id=\"auth\"></span></a></li>")
    $("#auth").text(payload.sub)
    $("#authenticated").append("<li style='cursor: pointer'><a id=\"myInfo\"  class=\"dropdown-item\" >My Information</a></li>")
    $("#authenticated").append("<li style='cursor: pointer'><a id=\"logout\"><i  class=\"fa-solid fa-right-from-bracket\"></i> Logout</a></li>")

},
    error: function(xhr) {
    window.location.href = '/login';
}
});

    $(document).on("click", "#logout", function () {
    $.ajax({
    url: '/auth/logout',
    method: 'POST',
    contentType: 'application/json',
    dataType: 'json',
    data: JSON.stringify({ "token": token }),
    success: function () {
    localStorage.removeItem("jwtToken");
    window.location.href = '/login';
},
    error: function (xhr) {
    console.error("Lỗi khi logout:", xhr);
}
});
});


});