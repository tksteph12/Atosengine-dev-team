
// -- fill selected box with database
function fillSelectBox() {
    var selectBox = $('#idgcm');
    var url = "http://192.168.43.138:8080/RestWSDB/webresources/com.atos.restfull.gcm";
    var options = retrieveFrom(url);

    for(var i = 0, l = options.length; i < l; i++){
        var option = options[i];
        selectBox[0].options.add( new Option(option.code, option.idGcm, option.selected) );
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


// -- filter parameters
var idrr;
var keyword;
var idgcm;
var city;
var client;
var datefrom;


// -- get parameters of the form
function getDataForm() {
    idrr = $('#idrr').val();
    keyword = $('#idrr').val();
    idgcm = $('#idgcm').val();
    city = $('#city').val();
    idrr = $('#idrr').val();
    datefrom = $('#datefrom').val();
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
        row.id = dataRow[0]; 

        for (k = 1; k < 5; k++) { 
            cell = row.insertCell(k-1);
            //cell[text] = dataRow[k];
            $('<a>'+dataRow[k]+'</a>').attr({
                'href': 'details.html' ,
                'id': row.id    // Supposant que l'idrr est la ds la position 0
            }).appendTo(cell);
        }
   }
 }


function searchAndFill(id,htmlfile){
  $('#'+id).empty();

  var resTags = {
    info: "Rappel Des paramètres entrées pour la recherche",
    name: "nom",
    role: "role",
    city: "city",
    gcm: "gcm"
  }

  // Récupérer le template à distance
  //NB possibilité d'utiliser tu templating mustache
  $.get("results.html", null, function (results) {
    // Render the data using the remote template
  // passer les données dans le template
    $.tmpl(results, resTags).appendTo('#'+id);
    fillTable('data-area', dataArray);
});

}


function disableInput() {
    if($('#idrr').val() != '') {
        $("#keyword").prop("disabled", true);
        $("#idgcm").prop("disabled", true);
        $("#city").prop("disabled", true);
        $("#datefrom").prop("disabled", true);
    }
    else {
        $("#keyword").prop("disabled", false);
        $("#idgcm").prop("disabled", false);
        $("#city").prop("disabled", false);
        $("#datefrom").prop("disabled", false);
    }
}

 var dataArray = [];
        dataArray[0] = ["r-1", "FR_TS_RA_1 admin système unix_ERDF_Lyon", "Administration système unix...", "More...", "777", "The Flintstones"];
        dataArray[1] = ["r-2", "Wilma", "Flintstone", "Records", "701", "The Flintstones"];
        dataArray[2] = ["r-3", "Barney", "Rubble", "Marketing", "710", "The Flintstones"];
        dataArray[3] = ["r-4", "Betty", "Rubble", "Records", "701", "The Flintstones"];
        dataArray[4] = ["r-5", "Pebbles", "Flintstone", "Home", "792", "The Flintstones"];
        dataArray[5] = ["r-6", "Bamm-Bamm", "Rubble", "Home", "791", "The Flintstones"];
        dataArray[6] = ["r-7", "Gary", "Granite", "Theater", "722", "The Flintstones"];
        dataArray[7] = ["r-8", "Rock", "Hudstone", "Theater", "723", "The Flintstones"];
        dataArray[8] = ["r-9", "George", "Rockstone", "Gravel Pit", "700", "The Flintstones"];
        dataArray[9] = ["r-10", "Zeke", "Flintstone", "Sales", "702", "The Flintstones"];
        dataArray[10] = ["r-11", "Stone", "Hatrock", "Sales", "702", "The Flintstones"];
        dataArray[11] = ["r-12", "Stony", "Curtis", "Rock Vegas", "721", "The Flintstones"];
        dataArray[12] = ["r-13", "Ed", "Sulleystone", "Theater", "720", "The Flintstones"];
        dataArray[13] = ["r-14", "Rock", "Hudstone", "Theater", "724", "The Flintstones"];
        dataArray[14] = ["r-15", "Ann", "Margrock", "Theater", "725", "The Flintstones"];
        dataArray[15] = ["r-16", "Alvin", "Brickrock", "Police", "730", "The Flintstones"];
        dataArray[16] = ["r-17", "Perry", "Masonary", "Court", "710", "The Flintstones"];
        dataArray[17] = ["r-18", "Tex", "Hardrock", "Gravel Pit", "777", "The Flintstones"];
        dataArray[18] = ["r-19", "Pearl", "Slaghoople", "Market", "760", "The Flintstones"];
        dataArray[19] = ["r-20", "Great", "Gazoo", "Aliens", "790", "The Flintstones"];
        dataArray[20] = ["r-21", "Harold", "Slate", "Owner", "700", "The Flintstones"];
        dataArray[21] = ["r-22", "Joe", "Rockhead", "Gravel Pit", "777", "The Flintstones"];
        dataArray[22] = ["r-23", "Arnold", "Flagstone", "News Paper", "780", "The Flintstones"];
        dataArray[23] = ["r-24", "Sam", "Slageheap", "Grand Poobah", "750", "The Flintstones"];
        dataArray[24] = ["r-25", "Ethel", "Slate", "Home", "799", "The Flintstones"];
        dataArray[25] = ["r-26", "The", "Hatrocks", "Rock Vegas", "722", "The Flintstones"];
        dataArray[26] = ["r-27", "Bedrock", "Hillbillies", "Rock Vegas", "723", "The Flintstones"];
        dataArray[27] = ["r-28", "Samantha", "Stephens", "Magic", "101", "Bewitched"];
        dataArray[28] = ["r-29", "Darrin", "Stephens", "Marketing", "111", "Bewitched"];
        dataArray[29] = ["r-30", "Larry", "Tate", "McMann and Tate", "110", "Bewitched"];
        dataArray[30] = ["r-31", "Endora", "Evans", "Retired", "120", "Bewitched"];
        dataArray[31] = ["r-32", "Maurice", "Evans", "Magic", "121", "Bewitched"];
        dataArray[32] = ["r-33", "Gladys", "Kravitz", "Mortal", "130", "Bewitched"];
        dataArray[33] = ["r-34", "Abner", "Kravitz", "Mortal", "130", "Bewitched"];
        dataArray[34] = ["r-35", "Tabitha", "Stephens", "Home", "101", "Bewitched"];
        dataArray[35] = ["r-36", "Adam", "Stephens", "Home", "101", "Bewitched"];
        dataArray[36] = ["r-37", "Aunt", "Clara", "Retired", "121", "Bewitched"];
        dataArray[37] = ["r-38", "Louise", "Tate", "Home", "102", "Bewitched"];
        dataArray[38] = ["r-39", "Esmeralda", "Ghostley", "Domestic", "115", "Bewitched"];
        dataArray[39] = ["r-40", "Dr. Bernard", "Bombay", "Hospital", "170", "Bewitched"];
        dataArray[40] = ["r-41", "Phyllis", "Stephens", "Retired", "132", "Bewitched"];
        dataArray[41] = ["r-42", "Frank", "Stephans", "Retired", "132", "Bewitched"];
        dataArray[42] = ["r-43", "Serena", "Evans", "Jetset", "000", "Bewitched"];
        dataArray[43] = ["r-44", "Ms. Maudie", "Peabody", "School", "180", "Bewitched"];
        dataArray[44] = ["r-45", "Harriet", "Kravits", "Mortal", "130", "Bewitched"];
        dataArray[45] = ["r-46", "Queen", "Ticheba", "Royal Court", "190", "Bewitched"];
        dataArray[46] = ["r-47", "Yogi", "Bear", "Woods", "6123", "Yogi Bear"];
        dataArray[47] = ["r-48", "Ranger", "Smith", "Field Research", "6100", "Yogi Bear"];
        dataArray[48] = ["r-49", "Boo-Boo", "Bear", "Woods", "6122", "Yogi Bear"];
        dataArray[49] = ["r-50", "Cindy", "Bear", "Jellystone", "6200", "Yogi Bear"];
        dataArray[50] = ["r-51", "Ben",  "Cartwright", "Rancher", "800", "Bonanza"];
        dataArray[51] = ["r-52", "Adam", "Cartwright", "Rancher", "800", "Bonanza"];
        dataArray[52] = ["r-53", "Joe", "Cartwright", "Rancher", "800", "Bonanza"];
        dataArray[53] = ["r-54", "Hoss", "Cartwright", "Rancher", "800", "Bonanza"];
        dataArray[54] = ["r-55", "Hop", "Sing", "Kitchen", "801", "Bonanza"];
        dataArray[55] = ["r-56", "Roy", "Coffee", "Sheriff's Office", "810", "Bonanza"];
        dataArray[56] = ["r-57", "Clem", "Foster", "Sheriff's Office", "810", "Bonanza"];
        dataArray[57] = ["r-58", "Will", "Cartwright", "Field Research", "812", "Bonanza"];
        dataArray[58] = ["r-59", "Candy", "Canaday", "Foreman", "820", "Bonanza"];
        dataArray[59] = ["r-60", "Jamie", "Hunter", "Rainmaker", "821", "Bonanza"];
        dataArray[60] = ["r-61", "Griff", "King", "Ranch Hand", "822", "Bonanza"];
 
            //fillTable(dataArea, dataArray);
        /*    var dataArea = document.getElementById('data-area');
            if(dataArea===null){
                dataArea = $('#data-area');
            }
            fillTable('data-area', dataArray);

        */




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