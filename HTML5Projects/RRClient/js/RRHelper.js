function loadTemplate(){
	//alert("Hello World");
}


function formIsValid(form){
	//Vefifies if the form is valid according to values entered: May not be necessary with html5
	var isvalid = false;
	if(form.gcmlevel.value == 4){
		isvalid= true;
	}
	return isvalid;
}


function lookup(form){
	// Fetch for the data with html5 
	if (formIsValid(form)){
		/*
			If valid form then call the corresponding web service according to the values entered in the filter
			Load a charging... page to make the user wait
			fetch for the results then print them

			make a conditional statement here to choose wich url to pass to the retrieveFrom() function

			the url should be set in a parameterized external file
		*/
		var url = "http://10.255.241.168:8080/RestWSDB/webresources/com.atos.restfull.rr";
		var allrr = retrieveFrom(url);
		console.log(allrr);
	}
	else{
		/*
			If form is not valid then print a generic page with the corresponding error
		*/

		var url = "http://10.255.241.168:8080/RestWSDB/webresources/com.atos.restfull.rr";
		var allrr = retrieveFrom(url);
		console.log(allrr);
		//alert("The form is not valid");
	}
}


function retrieveFrom(url){
	//retrieves the data from the url passed in parameters
	var results;
	$.ajax({
        type: "GET",
        url: url,
        async: false,
        dataType: "text",
        success: function(data) {
          results = data;
        },
        error : function(){
        	console.log("Failed!!");

        },
        //beforeSend: setHeader
      });
	return results;


	function setHeader(){

	}
}