var first = true;

function fileSelectBox() {
    var selectBox = $('#idgcm');
    var url = "http://192.168.43.138:8080/RestWSDB/webresources/com.atos.restfull.gcm";
    var options = retrieveFrom(url);

    for(var i = 0, l = options.length; i < l; i++){
        var option = options[i];
        selectBox[0].options.add( new Option(option.code, option.idGcm, option.selected) );
    }
}


function retrieveFrom(url){
    //retrieves the data from the url passed in parameters
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

function setViewPanel(id,htmlfile) {
    $('#'+id).load(htmlfile);
}


var idrr = 0;
function getSearch() {
    var val = $('#idrr');
    idrr = val.val();
    
}

function setSearch() {
    alert("ok");
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