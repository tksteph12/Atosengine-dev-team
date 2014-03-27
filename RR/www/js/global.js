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

function setViewPanel(htmlfile) {
    $('#viewpanel').load(htmlfile);
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