
COLORS = [
          '#f00', '#0f0', '#00f', '#ff0', '#f0f', '#0ff'
          ];
H=90;
W=60;

$(document).ready(function() {
	
	function height(index) {
		return (index <= 10 || (index >= 20 && index <= 30)) ? H : W;
	}
	
	function width(index) {
		return ((index > 0 && index < 10) || (index > 20 && index < 30)) ? W : H;
	}
	
	function build_board() {
		$.getJSON("board", function(data) {
			var board = $("#board");
			var x=100, y=100;
			console.log('board', data);
			$.each(data, function(key, val) {
				var s = $('<div id="square' + key + '" class="square"><span>' + val.name + '</span></div>')
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
				
				s.addClass($(val.class.split('.')).last().get(0));
				
				if (val.rent) {
					s.attr("title", "cost: " + val.cost + "; rent: " + val.rent);
					s.data('cost', val.cost);
					s.data('rent', val.rent);
					s.append("<span class='cost'>cost:&nbsp;" + val.cost + "<br/>rent:&nbsp;" + val.rent + "</span>")
				} else if (val.cost) {
					s.attr("title", "cost: " + val.cost);
					s.data('cost', val.cost);
				}
				if (key % 10 == 0) {
					s.addClass("corner");
				}
				
			    board.append(s);
			});
			
			$('.square').hover(function() {
				$(this).find('.cost').fadeIn();
			}, function() {
				$(this).find('.cost').fadeOut();
			});

		});
	}

	// call callback(player, index) for each registered player
	function with_players(callback) {
		$.getJSON('players', function(data) {
			$.each(data, function(key, val) {
				callback(val, key);
			});
		});
	}


	// Set up players
	function place_players() {
		var ul = $('#players>ul');
		ul.empty();
		$('.player').remove();

		// Create elements for all players (with color?) Place them in the element with the name.
		with_players(function(player, key) {
			var p = $('<li style="color:' + COLORS[key % COLORS.length] + '">' + player.name + ': money: ' + player.money+ '; possessions: ' + $.map(player.possessions, function(a) { return a.name }).join(', ') + '</li>');
			if (!player.finishedTurn) {
				p.addClass('active');
			}
			ul.append(p);

			//console.log($(".square:has(span:contains('" + player.currentPosition.name + "'))"))
			var p = $('<div id="player' + key + '" class="player" rel="' + player.name + '"></div>');
			p.css('background-color', COLORS[key % COLORS.length]);
			if (!player.finishedTurn) {
				p.addClass('active');
			}
			console.log('place on board', player, p);
			$('#square' + (player.currentPosition.position || 0)).append(p);
		});
	}
	

	//////// Set up:
	
	build_board();
	place_players();
	
	
	//////// Event handlers:
	
	$('button[name=addplayer]').click(function(event) {
		var name = $(this).parent().find('[name=name]');
		if (name.val()) {
			$.post('player', { name: name.val() }, function() {
				place_players();
				name.val('');
			});
		} else {
			alert('please provide a name');
		}
		return false;
	});
	
	
	$('button[name=startgame]').click(function() {
		if ($('#players>ul>li').size() < 2) {
			alert('Need more players');
			return false;
		}
		// No new players can be added
		$('#players>form').hide();
		$('dice').show();

		$.post("startgame", function() {
			place_players();
		});
		return false;
	});
	
	// Roll dice
	// move player
	$('button[name=roll]').click(function() {
		$.post('rolldice/' + $('input[name=d1]').val() + '/' + $('input[name=d2]').val(), function(data) {
			place_players();
			// on 'turnAction' and canBuy: enable buy button
		});
		return false;
	});
	
	// TODO: button for buy
	$('button[name=buy]').click(function() {
		$.post('player/buy', function(data) {
			place_players();
		});
		return false;
	});

	$('button[name=finish]').click(function() {
		$.getJSON("player/next", function() {
			place_players();
		});
	});

});