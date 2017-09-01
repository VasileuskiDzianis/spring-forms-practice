function showCover() {
	var coverDiv = document.createElement('div');
	coverDiv.id = 'cover-div';
	document.body.appendChild(coverDiv);
}

function hideCover() {
	document.body.removeChild(document.getElementById('cover-div'));
}

function showPrompt(text) {
	showCover();
	var form = document.getElementById('prompt-form');
	var container = document.getElementById('prompt-form-container');
	document.getElementById('prompt-message').innerHTML = text;
	

	function complete(value) {
		hideCover();
		container.style.display = 'none';
		document.onkeydown = null;
	}

	form.elements.cancel.onclick = function() {
		complete(null);
	};

	document.onkeydown = function(e) {
		if (e.keyCode == 27) { // escape
			complete(null);
		}
	};

	var lastElem = form.elements[form.elements.length - 1];
	var firstElem = form.elements[0];

	lastElem.onkeydown = function(e) {
		if (e.keyCode == 9 && !e.shiftKey) {
			firstElem.focus();
			return false;
		}
	};

	firstElem.onkeydown = function(e) {
		if (e.keyCode == 9 && e.shiftKey) {
			lastElem.focus();
			return false;
		}
	};
	container.style.display = 'block';
	form.elements.text.focus();
}

function confirmDeletion(userId, userLogin) {
	var hiddenField = document.getElementById("deleting_user");
	hiddenField.setAttribute("value", userId);
	showPrompt("User &quot;" + userLogin + "&quot; will be deleted. Are you sure?");
}
