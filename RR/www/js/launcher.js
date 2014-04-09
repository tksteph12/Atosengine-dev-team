//Point d'entrée de l'application
$(document).ready(function() {
	var params = {
		header: "Ressource Requests",
		footer:" @Copyright Atos TS The greatest place to work!"
	};

	setViewPanel('body','welcome',params);
	setViewPanel('viewpanel','form',null);	
 });
