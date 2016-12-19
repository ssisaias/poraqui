
var DEF_MARKER_PATH = "M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z";

var cardContainer = $(".card-container");
var cardTemplate = $(".card-place-reference"); cardTemplate.remove();
var currentLocais = [];

$(document).ready(function() {
	
	$("#modal-avaliar .btn-ok").click(function() {
		
		var data = serializeForm($("#modal-avaliar form"));
		$("#modal-avaliar form")[0].reset();
		var localId = String(data.local_id);
		var data = JSON.stringify(data);
		console.log(data);
		$.ajax({
		    url: '/api/local/avaliar/' + localId, 
		    type: 'POST',
		    data: data,
		    contentType: 'application/json; charset=utf-8',
		    dataType: 'json',
		    success: function(response) {
		    	
		    	mf_base.doAlertSet(response.alertSet);
		    	var avaliacao = response.object;
		    	
		        atualizarLocais();
		    }
		});
	});
	
});

var initMap = function() {
	
	maps.doInit("#map", 
		function() {
			console.log("Mapa iniciado!");
		}, 
		function() {
			console.log("Erro ao iniciar mapa!");
		}
	);
	
	maps.doSetOnMapClickedListener(function(pos) {
		
		$('#modal-criar-local').openModal();
		$('#modal-criar-local input[name="latitude"]').val(pos.lat());
		$('#modal-criar-local input[name="longitude"]').val(pos.lng());
		
	});
	
	$('#modal-criar-local .btn-ok').click(function() {
				
		var local = serializeForm($("#modal-criar-local form"));
		$("#modal-criar-local form")[0].reset();
		var data = JSON.stringify(local);
		
		$.ajax({
		    url: '/api/local/',
		    type: 'POST',
		    data: data,
		    contentType: 'application/json; charset=utf-8',
		    dataType: 'json',
		    success: function(response) {
		    	
		    	mf_base.doAlertSet(response.alertSet);
		    	var local = response.object;
		    	
		        addLocal(local);
		    }
		});
	});
	
	atualizarLocais();
	
};

var atualizarLocais = function() {
	 
	cardContainer.text("");
	$.getJSON("/api/local/")
	.done(function(response) {
	
		mf_base.doAlertSet(response.alertSet);
		var locais = response.object;
		
		if(response.status == "DONE") {
			locais.map(function(l) {
				addLocal(l);
			});
		}
		
	})
	.fail(function() {
		console.log("Erro ao atualizar locais");
	});
}

var addLocal = function(l) {
	
	currentLocais.push(l);

	/* Criando marcador do mapa */
	var marker = maps.doSetMarker({
        id: l.id,
        title: l.nome, 
        dataType: "LOCAL",
        data: l,
        position: new google.maps.LatLng(l.latitude, l.longitude),
        animation: google.maps.Animation.DROP,
        icon: {
            path: DEF_MARKER_PATH,
            fillColor: "#3F51B5",
            fillColorNormal: "#3F51B5",
            fillColorSelected: "#1A237E",
            fillOpacity: 1,
            strokeWeight: 0,
            strokeColor: "#000",
            scale: 1.2,
            scaleDefault: 2,
            rotation: parseInt(0),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(10, 10),
        }
    });
	
	/* Criando card */
	var card = cardTemplate.clone();
	card.find(".type").text(l.acessibilidade || " - ");
	card.find(".name").text(l.nome || " - ");
	card.find(".description").text(l.descricao || " - ");
	
	if(!l.avaliacoes || l.avaliacoes.length == 0) {
		card.find(".rate-text").text("Sem avaliações!");
	}
	else {
		var rate = l.avaliacoes.map(function(a) { return a.value; }).reduce(function(a, b) { return (a + b) / 2.0; });
		card.find(".rate-text").text(parseInt(rate*10)/10.0);
		card.find(".rate").rateYo({
		    rating: rate, 
		    starWidth: "16px", 
		    normalFill: "#f48fb1", 
		    ratedFill: "#E91E63", 
		    readOnly: true
		});
	}
	
	card.find(".card-content").click(function() {
		maps.doSelectMarker(marker);
		$(".card-place-reference").removeClass("selected");
		card.addClass("selected");
	});
	
	google.maps.event.addListener(marker, 'click', function() {
		maps.doSelectMarker(marker);
		$(".card-place-reference").removeClass("selected");
		card.addClass("selected");
    }); 
	
	card.find(".btn-avaliar").click(function() {
		$("#modal-avaliar").openModal();
		$("#modal-avaliar").find(".rate-modal").rateYo({
		    rating: 5, 
		    fullStar: true, 
		    starWidth: "32px", 
		    normalFill: "#f48fb1", 
		    ratedFill: "#E91E63", 
		    onSet: function(val, _) { $("#modal-avaliar").find("input[name='value']").val(val); }
		});
		$("#modal-avaliar").find("input[name='local_id']").val(l.id);
	});
	
	card.find(".btn-remover").click(function() {
		$.ajax({
		    url: '/api/local/' + String(l.id),
		    type: 'DELETE',
		    contentType: 'application/json; charset=utf-8',
		    dataType: 'json',
		    success: function(response) {
		    	
		    	mf_base.doAlertSet(response.alertSet);
		    	var local = response.object;
		    	maps.doRemoveMarker(marker);
		        atualizarLocais();
		    }
		});
	});
	
	cardContainer.append(card);
}

var serializeForm = function(el) {
    var o = {};
    var a = el.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};