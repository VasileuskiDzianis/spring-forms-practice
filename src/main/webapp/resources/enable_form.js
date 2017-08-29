function enableForm(formId, submitButton) {

	var form = document.getElementById(formId);
	var elements = form.elements;

	for (var i = 0, len = elements.length; i < len; ++i) {
		
			elements[i].disabled = false;
		
	var updateButton = document.getElementById(submitButton);
	
		updateButton.style.visibility = 'visible';
	
}}