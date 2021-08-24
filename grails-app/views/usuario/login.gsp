<%--<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=${encoding}"/>
<title>Diferido Especial</title>
<asset:stylesheet src="usuario/login.css" />
<asset:stylesheet src="usuario/login/style.css" />
<link rel="shortcut icon" href="${assetPath(src: 'favicon.png')}"
	type="image/png">
</head>
<body>
<div id="contenedor">
  <div class="wrap">
  		<g:form action="login">
  		<br><br>
  		<div>
  			<g:if test="${flash.errorMessage}">
  				<div style="color: red">${flash.errorMessage}</div>
  			</g:if>
  		</div>
  		<div>
			<center style="font-size: 24px; font-family: Poppins-Bold;">Diferido Especial</center> <br>
			<center><img src="${assetPath(src: 'login.png')}" alt="AVATAR"></center>
			<br>
			<span style="color:snow; font-size: 15px;">Usuario</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style="font-size: 12px" type="text" placeholder="usuario" name="usuario" required="" autofocus id="usuario" value="${usuario?.usuario}"><br><br>
	  		<span style="color: snow; font-size: 15px;">Password</span>&nbsp;&nbsp;<input style="font-size: 12px" type="password" placeholder="password" name="password" required="" id="password"><br><br>
	  		<div class="shake-horizontalx">
	  			<input style="font-size: 16px; font-weight: bold" type="submit" value="INGRESAR" id="boton">
	  		</div>
  		</div>
  		</g:form>
  	</div>
  </div>
</body>
</html>--%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Devoluciones</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--===============================================================================================-->
	<!--===============================================================================================-->
	<asset:stylesheet src="vendor/bootstrap/css/bootstrap.min.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="fonts/Linearicons-Free-v1.0.0/icon-font.min.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="vendor/animate/animate.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="vendor/css-hamburgers/hamburgers.min.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="vendor/animsition/css/animsition.min.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="vendor/select2/select2.min.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="vendor/daterangepicker/daterangepicker.css"/>
	<!--===============================================================================================-->
	<asset:stylesheet src="css/main.css"/>
	<asset:stylesheet src="css/util.css"/>
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.png')}"
		  type="image/png">
	<!--===============================================================================================-->
</head>
<body>

<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100 p-t-50 p-b-90">
			<g:form action="login" class="login100-form validate-form flex-sb flex-w">
				<div class="login100-form-title2 fs-16">
					<g:if test="${flash.errorMessage}">
						<div style="color: #f90000; text-align: center">${flash.errorMessage}</div>
					</g:if>
				</div>
				<span class="login100-form-title p-b-14">
					DEVOLUCIONES AUSTRO
				</span>
				<span class="login100-form-avatar m-b-16">
					<img src="${assetPath(src: 'login.png')}" alt="AVATAR">
				</span><br><br>
				<div class="wrap-input100 validate-input m-b-16" data-validate = "Usuario es requirido">
					<input class="input100" type="text" name="usuario" id="usuario" placeholder="Usuario">
					<span class="focus-input100"></span>
				</div>


				<div class="wrap-input100 validate-input m-b-16" data-validate = "Contraseña es requirida">
					<input class="input100" type="password" name="password" id="password" placeholder="Contraseña">
					<span class="focus-input100"></span>
				</div>

				<div class="flex-sb-m w-full p-t-3 p-b-5">
					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1">
							Recordar
						</label>
					</div>
				</div>

				<div class="container-login100-form-btn m-t-17">
					<button class="login100-form-btn" id="boton">
						INGRESAR
					</button>
					<%--<input type="submit" class="login100-form-btn" value="Ingresar" id="boton">--%>
				</div>
				<div id="copyright" class="text-white fs-13 ab-b-r">&copy;Copyright 2020 Plus Wireless - Todos los derechos reservados</div>

			</g:form>
		</div>
	</div>
</div>


<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<asset:javascript src="vendor/jquery/jquery-3.2.1.min.js" />
<!--===============================================================================================-->
<asset:javascript src="vendor/animsition/js/animsition.min.js" />
<!--===============================================================================================-->
<asset:javascript src="vendor/bootstrap/js/popper.js" />
<asset:javascript src="vendor/bootstrap/js/bootstrap.min.js" />
<!--===============================================================================================-->
<asset:javascript src="vendor/select2/select2.min.js" />
<!--===============================================================================================-->
<asset:javascript src="vendor/daterangepicker/moment.min.js" />
<asset:javascript src="vendor/daterangepicker/daterangepicker.js" />
<!--===============================================================================================-->
<asset:javascript src="vendor/countdowntime/countdowntime.js" />
<!--===============================================================================================-->
<asset:javascript src="js/main.js" />
</body>
</html>