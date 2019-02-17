
function XMLZahtev()
{
    var zahtev = new XMLHttpRequest();
    zahtev.open("GET", "TimeServlet", true);
    zahtev.send(null);
    console.log("poslali zahtev");

    zahtev.onreadystatechange =
            function ()
            {
                if (zahtev.readyState === 4) {
                    document.getElementById("time").innerHTML =
                            zahtev.responseText;
                }
            };
}

function vratiItems()
{
   var vrst_id = event.target.id;
        var zahtev = new XMLHttpRequest();
    zahtev.open("GET", "ItemsServlet?category_id="+vrst_id, true);
    zahtev.send(null);
    zahtev.onreadystatechange =
            function ()
            {
                if (zahtev.readyState === 4) {
                    var niz = zahtev.responseText;
                    var obj = JSON.parse(niz);
                    document.getElementById("items").innerHTML  = "";
                    for(var i=0; i<obj.length; i++){
                        var text = "<div id='noviItem'>";
                        text +=obj[i].id + " " + obj[i].name + 
                            " " + obj[i].price + obj[i].manufacturer+ "<br>";
                    text += "</div>"
                 document.getElementById("items").innerHTML += text;
                    }
                }
            };
}