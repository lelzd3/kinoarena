
<!DOCTYPE html>
<%@page import="dao.MovieDao"%>
<%@page import="pojos.Movie"%>
<%@page import="dao.HallDao"%>
<%@page import="pojos.Hall"%>
<%@page import="dao.CinemaDao"%>
<%@page import="pojos.Cinema"%>
<%@page import="dao.BroadcastDao"%>
<%@page import="pojos.Broadcast"%>
<html>
<head>
<title>Movie Ticket Booking Widget Flat Responsive Widget Template :: w3layouts</title>
<!-- for-mobile-apps -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Movie Ticket Booking Widget Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- //for-mobile-apps -->
<link href='//fonts.googleapis.com/css?family=Kotta+One' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/jquery.seat-charts.js"></script>

<%
	int broadcastId = (Integer)request.getSession().getAttribute("session_broadcast_id");
	Broadcast broadcast = BroadcastDao.getInstance().getBroadcastById(broadcastId);
	Cinema cinema = CinemaDao.getInstance().getCinemaById(broadcast.getCinemaId());
	Movie movie = MovieDao.getInstance().getMovieById(broadcast.getMovieId());
%>
</head>
<body>
<div class="content">
	<h1>Reservation Hall</h1>
	<div class="main">
		<h2><%=cinema.getName()+" , hall: "+broadcast.getHallId() %></h2>
		<div class="demo">
			<div id="seat-map">
				<div class="front">SCREEN</div>					
			</div>
			<div class="booking-details">
				<ul class="book-left">
					<li>Movie </li>
					<li>Time </li>
					<li>Tickets</li>
					<li>Total</li>
					<li>Seats :</li>
				</ul>
				<ul class="book-right">
					<li>: <%=movie.getTitle() %></li>
					<li>: <%=broadcast.getProjectionTime() %></li>
					<li>: <span id="counter">0</span></li>
					<li>: <b><i>$</i><span id="total">0</span></b></li>
				</ul>
				<div class="clear"></div>
				<ul id="selected-seats" class="scrollbar scrollbar1"></ul>
			
				<form action="reserve" method="post">
					<input id="hiddenSeats" name="hiddenSeats" type="hidden" value="1">
					<input id="submitButton" type="submit" class="checkout-button" value="Book now">
				</form>		
				<div id="legend"></div>
			</div>
			<div style="clear:both"></div>
	    </div>

			<script type="text/javascript">
			
				var price = parseFloat(<%=broadcast.getPrice()%>);
				//var price = 10;
				$(document).ready(function() {
					var $cart = $('#selected-seats'), //Sitting Area
					$counter = $('#counter'), //Votes
					$total = $('#total'); //Total money
					
					var sc = $('#seat-map').seatCharts({
						map: [  //Seating chart
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa',
							'aaaaaaaa'
						],
						naming : {
							top : false,
							getLabel : function (character, row, column) {
								return column;
							}
						},
						legend : { //Definition legend
							node : $('#legend'),
							items : [
								[ 'a', 'available',   'Available' ],
								[ 'a', 'unavailable', 'Sold'],
								[ 'a', 'selected', 'Selected']
							]					
						},
						click: function () { //Click event
							if (this.status() == 'available') { //optional seat
								$('<li value="1">Row'+(this.settings.row+1)+' Seat'+this.settings.label+'</li>')
									.attr('id', 'cart-item-'+this.settings.id)
									.data('seatId', this.settings.id)
									.appendTo($cart);
								console.log('<li>Row'+(this.settings.row+1)+' Seat'+this.settings.label+'</li>');
								$counter.text(sc.find('selected').length+1);
								$total.text(recalculateTotal(sc)+price);
											
								return 'selected';
							} else if (this.status() == 'selected') { //Checked
									//Update Number
									$counter.text(sc.find('selected').length-1);
									//update totalnum
									$total.text(recalculateTotal(sc)-price);
										
									//Delete reservation
									$('#cart-item-'+this.settings.id).remove();
									//optional
									return 'available';
							} else if (this.status() == 'unavailable') { //sold
								return 'unavailable';
							} else {
								return this.style();
							}
						}
					});
					document.getElementById("submitButton").onclick= function(){
						var arrayOfSeats = [];
						$("li").each(function(index){
							if(this.getAttribute("value") == "1"){
								arrayOfSeats.push($(this).text());
							}
						});
						document.getElementById("hiddenSeats").value = arrayOfSeats.join();
						console.log(document.getElementById("hiddenSeats").value);
					}
					//sold seats
					$(document).ready(function (){
						var xmlHttp = new XMLHttpRequest();
						xmlHttp.onreadystatechange = function(){
							if(xmlHttp.readyState == 4 && xmlHttp.status == 200){

								var x = xmlHttp.responseText;
								sc.get(x.split(", ")).status('unavailable');
							}
						}
						xmlHttp.open("GET","reserve");
						xmlHttp.send(null);
					});
					//sc.get(['1_3', '4_4','4_5','6_6','6_7','8_5','8_6','8_7','8_8', '10_1', '10_2']).status('unavailable');
				//	sc.get().status('unavailable');
						
				});
				//sum total money
				function recalculateTotal(sc) {
					var total = 0;
					sc.find('selected').each(function () {
						total += price;
					});
							
					return total;
				}
				
				
				
			</script>
	</div>
	
</div>
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>
