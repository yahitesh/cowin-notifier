<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link href="/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
		<form id="notify" action="/cowin/notify" method="post">

			<h2 class="h3 mb-3 fw-normal">${message}</h2>
			<div class="form-floating">
				<input type="email" class="form-control" id="email"
					placeholder="name@example.com" name="email"> <label
					for="floatingInput">Email address</label>
			</div>
			<div class="form-floating">
				<input type="text" class="form-control" id="pincode"
					placeholder="111111" name="pincode"> <label for="pincode">PinCode</label>
			</div>



			<button class="w-100 btn btn-lg btn-primary" type="submit">Notify</button>
			<p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
		</form>
	</main>



</body>
</html>
