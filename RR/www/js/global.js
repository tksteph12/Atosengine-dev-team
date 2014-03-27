
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
function setViewPanel(id,htmlfile) {
    $('#'+id).load(htmlfile);
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
            cell[text] = dataRow[k];
        }
   }
 }