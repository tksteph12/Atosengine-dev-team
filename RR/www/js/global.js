var ip =  "192.168.43.111";//"10.255.241.71";//

// -- fill selected box with database
function fillSelectBox() {
    var selectBox = $('#idgcm');
    var url = "http://"+ip+":8080/RRWebService/webresources/com.atos.ressources.rr/allgcm";
    try{
    var options = retrieveFrom(url);
    }
    catch(e){
      alert(e);
    }

    for(var i = 0, l = options.length; i < l; i++){
        var option = options[i];
        selectBox[0].options.add( new Option(option.code, option.code, option.selected) );
    }

    var selectBox = $('#city');
    var url = "http://"+ip+":8080/RRWebService/webresources/com.atos.ressources.rr/cities";
    try{

    var options = retrieveFrom(url);
    }
    catch(e){
      alert(e);
    }
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
      error : function(xhr, ajaxOptions, thrownError){
        console.log("Failed!!");
        // An error Occured while processing the request : The server returned nothing
        var params = {
          xhr: xhr, 
          ajaxOptions : ajaxOptions, 
          thrownError : thrownError
        }
        //loadErrorView(params);
        throw thrownError;
    },
    });
    return results;
}


/*
  function to load error view when an error occurs in the navigation

*/

function loadErrorView(params){
  alert(params.thrownError);

}

/*
// -- loads html template file htmlfile in position represented by id 
  @id is the id of the space reserved for the template.
  @htmlfile is the template file to insert
  @obj is the parameters for templating 

*/
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
        var url = "http://"+ip+":8080/RestWSDB/webresources/com.atos.restfull.idrr";
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
                var dataRowCut = dataRow[key];
                if(key === 'role') {
                  dataRowCut = cutStr(dataRow[key],50,dataRowCut.length);
                }
                cell[text] = dataRowCut;
               /* $('<a>'+dataRowCut+'</a>').attr({
                    'href': 'detailssssss.html',
                    'id': row.id    // Supposant que l'idrr est la ds la position 0
                }).appendTo(cell);*/
            }
        }
   }

//Evenement pour récupérer l'id sur la ligne sur laquelle on clicke
   $("tr").click(function(){
      var object = findInArray(this.id,source);
      $('#viewpanel').empty();
      //Rajouter des métadonnées sur le paramètre object ici avant de le passer au template.
      // Les méta données sont tous les paramètres qui sont en dur dans le template
      setViewPanel('viewpanel','details',object);
    });
   $("tr").addClass("trhover");
 }

function cutStr(str, cutStart, cutEnd){
  return str.substr(0,cutStart) + str.substr(cutEnd+1);
}

function searchAndFill(id,htmlfile,filters){
  var form_idRR = $('#'+filters[0]).val();
  var form_keyword = $('#'+filters[1]).val();
  var form_idgcm = $('#'+filters[2]).val();
  var form_city = $('#'+filters[3]).val();
  var form_datefrom = $('#'+filters[4]).val();
  form_datefrom = convertDate(form_datefrom);

  $('#'+id).empty();

  var resTags = {
    info: "Rappel Des paramètres entrées pour la recherche",
    name: "nom",
    role: "role",
    city: "Ville",
    gcm: "gcm",
    form_idRR: form_idRR,
    form_keyword : form_keyword,
    form_idgcm : form_idgcm,
    form_city : form_city,
    form_datefrom : form_datefrom

  }   
  

  var url = "http://"+ip+":8080/RRWebService/webresources/com.atos.ressources.rr/listrr?id="+form_idRR+"&gcm="+form_idgcm+"&motscles="+form_keyword+"&ville="+form_city+"&from="+form_datefrom;
  var arrayDatas = retrieveFrom(url);
  allResults = arrayDatas;
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




/**
  La fonction est censé désactiver les champs de
*/
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


/**
  Cette fonction permet de rechercher dans une liste d'objets json l'élément dont l'id est passé en paramètre
   id : id à rechercher
   list: liste d'objets json dans laquelle il faut faire la recherche
*/
function findInArray(id,list){
  for(var i=0; i<list.length;i++){
    var obj = list[i];
    var idr=obj.idRr;
    if(idr.toString()===id)
      return obj;
  }
}
 
function convertDate(inputFormat) {
  var testtt = inputFormat.trim();
  if(testtt.length <1){
    inputFormat = "1970-01-01";
  }
  var date = inputFormat.replace(/-/g,'/');
  function pad(s) { return (s < 10) ? '0' + s : s; }
  var d = new Date(date);
  return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/');
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
//document.getElementById("niveauMin").innerHTML = "Niveau min : " + list2[0].niveauMin;
document.getElementById("niveauMinMax").innerHTML = "Niveau min/max : " + list2[0].niveauMin+ "/" +list2[0].niveauMax;
document.getElementById("demandeurRr").innerHTML = "ID Demandeur : " + list2[0].demandeurRr;
document.getElementById("dateDebut").innerHTML = "Date Debut : " + list2[0].dateDebut;
document.getElementById("dateFin").innerHTML = "Date Fin : " + list2[0].dateFin;
document.getElementById("adresse").innerHTML = "Adresse : " + list2[0].adresse;
document.getElementById("ville").innerHTML = "Ville : " + list2[0].ville;
document.getElementById("role").innerHTML = "Role : " + list2[0].role;
}


var allResults = [];



