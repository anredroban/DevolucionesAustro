function generateRecallList(e){if($("#recall-list").is(":visible")){$("#recall-list").hide().html("")}else{$("#recall-list").show().html("");$.each(e,function(e,t){$("#recall-list").append('<a class="recall-link" href="'+baseUrl+"/gestion/gestionCliente/"+t.id+'">'+t.nombre+"</a><br>")})}}var message_timeout=3e3;$(document).ready(function(){var e=false;var t=false;$min=$("#extension");$("#tabladedatos").DataTable({order:[]});$("#menu-catalogos").click(function(){$(".desplegable").not($("ul",this)).slideUp("fast").next();$(this).find("ul").slideToggle("fast").end()});if($(".nav-campaign").hasClass("subnav-expanded")){$("#sub-campaign").attr("aria-expanded",true).addClass("in")}if($(".nav-reports").hasClass("subnav-expanded")){$("#sub-reportes").attr("aria-expanded",true).addClass("in")}if($(".nav-security").hasClass("subnav-expanded")){$("#sub-security").attr("aria-expanded",true).addClass("in")}if($(".nav-simulador").hasClass("subnav-expanded")){$("#sub-simuladores").attr("aria-expanded",true).addClass("in")}if($(".nav-catalogos").hasClass("subnav-expanded")){$("#sub-catalogos").attr("aria-expanded",true).addClass("in")}if($(".nav-licence").hasClass("subnav-expanded")){$("#sub-licence").attr("aria-expanded",true).addClass("in")}$("#cancel-call, #btn-close-operator-call").click(function(){$("#operator-btn-call").show();$("#operator-btn-hangout").hide()});$(".delete-item").click(function(e){var t=$(this).attr("id").split("-");var n=$("#modal-delete-item");n.modal();$("#btn-delete-item").click(function(){$.get(baseUrl+"/"+t[0]+"/deleteItem",{id:t[1]},function(e){if(e){$("#modal-delete-item .close").click();$("#row-item-"+t[1]).css("background-color","orange").delay(1e3).slideUp()}})})});$(".menu-header").click(function(){if(!e){$("#page-wrapper").css("padding-left","77px");$("#sidebar-links").css({"overflow-y":"auto",width:"68px"});$(".menu-name").hide();$(".navbar-nav > li > a").css("padding","15px");e=true}else{$("#page-wrapper").css("padding-left","260px");$("#sidebar-links").css({width:"235px"});$(".menu-name").show();e=false}});(function n(){$(".recallNotification").hide();$.ajax({url:baseUrl+"/FuncionesAjax/rellamadas",success:function(e){if(e.length){$(".recallNotification").show();$("#number-recalls").text(e.length);$(".recallNotification").click(function(){generateRecallList(e)})}},complete:function(){setTimeout(n,6e4)}})})()})