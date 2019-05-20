var xhr = new XMLHttpRequest();
var url = "/guitars/secure";
xhr.open("POST", url, true);
xhr.setRequestHeader("Content-Type", "application/json");
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 201) {
        // var json = JSON.parse(xhr.responseText);
        log.console(xhr.responseText);
        $("output").html(xhr.responseText);
    }
};
var data = JSON.stringify({"brand": "Fender", "model": "bullet", "price": 1500});
xhr.send(data);