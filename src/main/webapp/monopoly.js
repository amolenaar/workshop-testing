
H=90;
W=60;

$(document).ready(function() {
	
	function height(index) {
		return (index <= 10 || (index >= 20 && index <= 30)) ? H : W;
	}
	
	function width(index) {
		return ((index > 0 && index < 10) || (index > 20 && index < 30)) ? W : H;
	}
	
	$.getJSON("board", function(data) {
		var board = $("#board");
		var x=0, y=100;

		$.each(data, function(key, val) {
			console.log(key, val);
			var rent = "";
			var extra_classes = "";
			var s = $('<div id="square' + key + '" class="square" ' + rent + '><span>' + val.name + '</span></div>')
			var w = width(key), h = height(key);
			s.css({ 'width': w - 2, 'height': h - 2, 'left': x, 'top': y});
			if (key < 10) {
				x += w;
				s.addClass("top");
			} else if (key < 20) {
				s.addClass("right");
				y += h;
			} else if (key < 30) {
				s.addClass("bottom");
				x -= w;
				if (key == 20) x += H - W;
				if (key == 29) x -= H - W;
			} else {
				s.addClass("left");
				y -= h;
				if (key == 30) y += H - W;
			}
			if (val.rent) {
				s.attr("title", "rent " + val.rent);
			}
			if (key % 10 == 0) {
				s.addClass("corner");
			}
			
		    board.append(s);
		  });
	})
});