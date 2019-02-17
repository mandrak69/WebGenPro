/* 
 * 
 * Za izabranu kategoriju vraca listu proizvoda te kategorije
 * a
 */

function vratiItems()
{
   var cat_id = event.target.id;
        var zahtev = new XMLHttpRequest();
    zahtev.open("GET", "ItemsServlet?category_id="+cat_id, true);
    zahtev.send(null);
    zahtev.onreadystatechange =
            function ()
            {
                if (zahtev.readyState == 4) {
                    var niz = zahtev.responseText;
                    var obj = JSON.parse(niz);
                    document.getElementById("items").innerHTML  = "";
                    for(var i=0; i<obj.length; i++){
                        var text = "<div id='noviItem'>";
                        text +="<img class=\"productimg\" src=\"" +"images/"+ obj[i].webslika+ "\"><p>"+obj[i].name+obj[i].webopis + " " + obj[i].cena + 
                            " " + obj[i].webcena1 + "</p>";
                    text += "</div>"
                 document.getElementById("items").innerHTML += text;
                    }
                }
            };
}

//Poziva se u slucaju da korisnicko ime vec postoji u bazi prilikom registracije.
function usernameExists() {
    window.onload = function () {
        alert("Username already exists!");
    };
}

//Poziva se u slucaju da proizvod vec postoji u bazi prilikom dodavanja novog proizvoda.
function productExists() {
    window.onload = function () {
        alert("Product already exists!");
    };
}

//Poziva se u slucaju da pri kreaciji novog proizvoda fajl nije .jpg
function imageInvalid() {
    window.onload = function () {
        alert("File is not .jpg!");
    };
}

//Poziva se u slucaju da je neko od polja ostavljeno prazno prilikom 
//popunjavanja forme.
function fieldsEmpty() {
    window.onload = function () {
        alert("Molimo vas za popunu neophodnih polja!");
    };
}

//Poziva se u slucaju da je kombinacija korisnickog imena i sifre nepostojeca 
//prilikom logovanja.
function userInvalid() {
    window.onload = function () {
        alert("Invalid user!");
    };
}

//Poziva se u slucaju da je kupovina proizvoda uspesna.
function purchaseSuccess() {
    window.onload = function () {
        alert("Purchase successful!");
    };
}

//Poziva se prilikom uspesne registracije novog naloga.
function registerSuccess() {
    window.onload = function () {
        alert("Register successful!");
    };
}

//Poziva se prilikom uspesnog logina.
function loginSuccess() {
    window.onload = function () {
        alert("Login successful!");
    };
}

function newproductSuccess() {
    window.onload = function () {
        alert("New product has been added successfully!");
    };
}

//Poziva se radi redirekcije na pocetnu stranu u slucaju neovlascenih pokusaja
//pristupa odredjenim stranicama.
function redirectToIndex() {
    window.onload = function () {
        location.href = "index.jsp";
    };
}

//Poziva se prilikom kupovine proizvoda radi potvrde kupovine, kako se ne bi
//desila slucajna transakcija.
function confirmBuy(Url) {
    if (confirm("Are you sure you want to buy this product?"))
    {
        document.location = Url;
    }
}