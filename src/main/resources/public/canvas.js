window.onload = function () {
var grafTorta = new CanvasJS.Chart("torta", {
	animationEnabled: true,
	title:{
		text: "Huella de Carbono",
		horizontalAlign: "left"
	},
	data: [{
		type: "doughnut",
		startAngle: 60,
		//innerRadius: 60,
		indexLabelFontSize: 17,
		indexLabel: "{label} - #percent%",
		toolTipContent: "<b>{label}:</b> {y} (#percent%)",
		dataPoints: [
			{ y: 67, label: "organizacion 1 " },
			{ y: 28, label: "organizacion 2" },
			{ y: 10, label: "organizacion 3" },
			{ y: 7, label: "organizacion 4"},
			{ y: 15, label: "organizacion 5"},
			{ y: 6, label: "organizacion 6"}
		]
	}]
});
grafTorta.render();

var grafBarra = new CanvasJS.Chart("barra", {
	animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "Huella de Carbono"
	},
	axisY: {
		title: "Reserves(MMbbl)"
	},
	data: [{
		type: "column",
		showInLegend: true,
		legendMarkerColor: "grey",
		legendText: "MMbbl = one million barrels",
		dataPoints: [
			{ y: 300878, label: "organizacion 1" },
			{ y: 266455,  label: "organizacion 2" },
			{ y: 169709,  label: "organizacion 3" },
			{ y: 158400,  label: "organizacion 4" },
			{ y: 142503,  label: "organizacion 5" },
			{ y: 101500, label: "organizacion 6" },
			{ y: 97800,  label: "organizacion 7" },
			{ y: 80000,  label: "organizacion 8" }
		]
	}]
});
grafBarra.render();
}