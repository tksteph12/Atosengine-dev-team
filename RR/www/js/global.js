
// -- fill selected box with database
function fillSelectBox() {
    var selectBox = $('#idgcm');
    var url = "http://10.255.241.71:8080/RRWebService/webresources/com.atos.ressources.rr/allgcm";
    var options = retrieveFrom(url);

    for(var i = 0, l = options.length; i < l; i++){
        var option = options[i];
        selectBox[0].options.add( new Option(option.code, option.code, option.selected) );
    }

    var selectBox = $('#city');
    var url = "http://10.255.241.71:8080/RRWebService/webresources/com.atos.ressources.rr/cities";
    var options = retrieveFrom(url);

    for(var i = 0, l = options.length; i < l; i++){
        var option = options[i];
        selectBox[0].options.add( new Option(option.ville, option.ville, option.selected) );
    }
}

// -- retrieves the data from the url passed in parameters
function retrieveFrom(url){
    var results;
    $.ajax({
        type: "GET",
        url: url,
        async: false,
        dataType: "json",
        success: function(data) {
          results = data;
      },
      error : function(){
        console.log("Failed!!");
    },
    });
    return results;
}


// -- load html file
function setViewPanel(id,htmlfile,obj) {
   // $('#'+id).load(htmlfile);

    $.get(htmlfile+'.html', obj, function (htmlfile) {
    // Render the data using the remote template
  // passer les données dans le template
    $.tmpl(htmlfile, obj).appendTo('#'+id);
  });
}


// -- get the search from parameters
function getSearchResult() {

    if ( idrr != '' ) {
        alert("idrr search");
        var url = "http://192.168.43.138:8080/RestWSDB/webresources/com.atos.restfull.idrr";
        var options = retrieveFrom(url);
        
    }
    $("#setidrr").html("idrr");
}

function fillTable(id, source) {
    //fonctionn pour remplir un tableau de données
      var dataArea = document.getElementById(id);
      var row, dataRow;
      var i, j, k, l;
      var text = (dataArea.innerText?"innerText":(dataArea.textContent? "textContent": "innerHTML"));
      i = dataArea.rows.length;
 
      while (i--) {
         dataArea.deleteRow(i);
      }

     for (i = 0,j = source.length; i < j; i++) {
        /* The first "cell" in the source data 
         * for each row is the row ID. The for-loop
         * starts with k = 1 as the display data and 
         * stops at 5 because only four columns out 
         * of six are displayed.
         */
        row = dataArea.insertRow(i);
        dataRow = source[i];
        row.id = dataRow.idRr; 

        /*for (k = 1; k < 5; k++) { 
            cell = row.insertCell(k-1);
            //cell[text] = dataRow[k];
            $('<a>'+dataRow[k]+'</a>').attr({
                'href': 'details.html' ,
                'id': row.id    // Supposant que l'idrr est la ds la position 0
            }).appendTo(cell);
        }*/
        var k = 0;
        for(key in dataRow){
            if ((key==='nomRr' || key==='role' || key==='gcmRr'||key==='ville')){
                k++
                cell = row.insertCell(k-1);
                $('<a>'+dataRow[key]+'</a>').attr({
                    'href': 'details.html' ,
                    'id': row.id    // Supposant que l'idrr est la ds la position 0
                }).appendTo(cell);
            }


        }
   }
 }


function searchAndFill(id,htmlfile,filters){
  var form_idRR = $('#'+filters[0]).val();
  var form_keyword = $('#'+filters[1]).val();
  var form_idgcm = $('#'+filters[2]).val();
  var form_city = $('#'+filters[3]).val();
  var form_datefrom = $('#'+filters[4]).val();

  $('#'+id).empty();

  var resTags = {
    info: "Rappel Des paramètres entrées pour la recherche",
    name: "nom",
    role: "role",
    city: "Ville",
    gcm: "gcm"
  }   
  

  var url = "http://10.255.241.71:8080/RRWebService/webresources/com.atos.ressources.rr/listrr?id="+form_idRR+"&gcm="+form_idgcm+"&motscles="+form_keyword+"&ville="+form_city+"&from="+form_datefrom;
  var arrayDatas = retrieveFrom(url);
  console.log(arrayDatas);
  // Récupérer le template à distance
  //NB possibilité d'utiliser tu templating mustache
  $.get("results.html", null, function (results) {
    dataArray = arrayDatas;
    // Render the data using the remote template
  // passer les données dans le template
    $.tmpl(results, resTags).appendTo('#'+id);
    fillTable('data-area', dataArray);
});

}


var input = ["keyword", "idgcm", "city", "datefrom"];

function disableInput(id) {
    var actionToDo = true;

    if($("#"+id).val() != '') {
        actionToDo = true;
    }
    else {
        actionToDo = false;
    }

    for (i = 0; i < input.length; i++) { 
        $("#"+input[i]).prop("disabled", actionToDo);
    }
}

 



function valider(emailObject,emailTo,emailSubject,emailBody){
var app ;
var email = emailObject.value;


if (email.indexOf('@') === -1){
alert("Adresse non valide");
}

else {

var mail = email.split("@")[1].split(".")[0];

if(typeof ActiveXObject != 'undefined'){
try{
var objNS = theApp.GetNameSpace('MAPI');
var email_item = theApp.CreateItem(0);
app = new ActiveXObject("Outlook.Application");

email_item.to = (emailTo.value);
     email_item.Subject = (emailSubject.value);
     email_item.Body = (emailBody.value);
     email_item.display();

}catch(err){
alert("Outlook configuration error."+err.message );
}
} else {

switch (mail){
case ("gmail") : 
window.open("https://mail.google.com/mail/u/0/#inbox?compose=new");
break;
case ("yahoo") : 
window.open("http://www.yahoo.com");
break;
case ("orange") : 
window.open("http://www.orange.fr");
}
}
} 
}

function getVars(){

var list2 = [
{"adresse":"",
"competenceRr":"\" - Products\r\nWindows 2000 Professional-Medior, Windows 7-Medior, Windows XP-Medior, TCP/IP IP-Medior, Windows Scripting",
"dateDebut":"2001-04-14T00:00:00",
"dateFin":"2031-12-15T00:00:00",
"demandeurRr":"A576493",
"equipeRm":"A205304",
"gcmRr":"BMC",
"idRr":26962,
"niveauMax":8,
"niveauMin":2,
"nomRr":"FR_SI_1_SM3_TRA_MISTRAL_IdF",
"role":"\"Mission:\r\n#\tIng����nieur d����ploiement mat����riels.\r\n#\tCharge : 100%.\r\n#\tD����placements ponctuels en RP et province.\r\n#\tMission bas����e ��  Bezons.\r\n#\tT����l����travail : A ����tudier apr����s p����riode de formation\r\nDescription:\r\n#\tR����daction documentaire: (manuels, CR techniques,",
"ville":"Bezons"}]

document.getElementById("idRr").innerHTML = "ID : " + list2[0].idRr;
document.getElementById("nomRr").innerHTML = "Nom Ressource : " + list2[0].nomRr;
document.getElementById("gcmRr").innerHTML = "Code GCM : " + list2[0].gcmRr;
document.getElementById("competenceRr").innerHTML = "Competences : " + list2[0].competenceRr;
document.getElementById("niveauMin").innerHTML = "Niveau min : " + list2[0].niveauMin;
document.getElementById("niveauMax").innerHTML = "Niveau max : " + list2[0].niveauMax;
document.getElementById("demandeurRr").innerHTML = "ID Demandeur : " + list2[0].demandeurRr;
document.getElementById("dateDebut").innerHTML = "Date Debut : " + list2[0].dateDebut;
document.getElementById("dateFin").innerHTML = "Date Fin : " + list2[0].dateFin;
document.getElementById("adresse").innerHTML = "Adresse : " + list2[0].adresse;
document.getElementById("ville").innerHTML = "Ville : " + list2[0].ville;
document.getElementById("role").innerHTML = "Role : " + list2[0].role;
}