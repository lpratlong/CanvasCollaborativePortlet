/**
 * CanvasImageEditorPortlet
 * (C) 2012, Leo Pratlong
 *
 * CanvasImageEditorPortlet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * CanvasImageEditorPortlet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details: <http://www.gnu.org/licenses/>. 
 */
var EventTool = {
	getPosition: function(event, parent) {
		var e = event || window.event;
		if (parent.getBoundingClientRect) {
			var lefttop = parent.getBoundingClientRect();
			x = e.clientX - Math.floor(lefttop.left);
			y = e.clientY - Math.floor(lefttop.top);
			return { x: x, y: y };
		}
	},
	
	getEventFunction: function(b, c) {
		return function() { c(event, b); };
	}
};

function Drawer(canvasId) {
	this.canvas = null;
	this.ctx = null;
	this.previousPoint = null;
	this.url = null;
	this.currentFigure = [];
	
	this.init = function(canvasId, url) {
		this.canvas = document.getElementById(canvasId);
		if (this.canvas.getContext) {
			this.url = url;
			this.ctx = this.canvas.getContext("2d");
			this.canvas.onselectstart = function () { return false; };
			this.canvas.onmousedown = EventTool.getEventFunction(this, this.startDrawing);
		} else {
			// ERROR: NO HTML5
		}
	};
	
	this.startDrawing = function(event, elem) {
		elem.previousPoint = EventTool.getPosition(event, elem.canvas);
		elem.canvas.onmousemove = EventTool.getEventFunction(elem, elem.drawLine);
		document.body.onmouseup = EventTool.getEventFunction(elem, elem.stopDrawing);
		elem.canvas.onmouseup = null;
		elem.currentFigure = [];
		elem.currentFigure.push(elem.previousPoint);
	};
	
	this.drawLine = function(event, elem) {
		var point = EventTool.getPosition(event, elem.canvas);
		elem.ctx.beginPath();	
		elem.ctx.moveTo(elem.previousPoint.x, elem.previousPoint.y);
		elem.ctx.lineTo(point.x, point.y);
		// ctx.strokeStyle = couleur;
		elem.ctx.stroke();
		elem.previousPoint = point;
		elem.currentFigure.push(point);
	};
	
	this.stopDrawing = function(event, elem) {
		elem.canvas.onmousemove = null;
		AUI().use('aui-io-request', function(A) {  
			A.io.request(elem.url, { 
				method: 'POST', 
				data: { cmd: "add_figure", new_figure: A.JSON.stringify(elem.currentFigure) }
			});
		});
	};
	
	this.drawFigures = function(figures) {
		if (figures && (figures.length > 0)) {
			this.ctx.beginPath();
			for (var i = 0; i < figures.length; i++) {
				var fig = eval(figures[i]);
				if (fig && (fig.length > 0)) {
					this.ctx.moveTo(fig[0].x, fig[0].y);
					for (var j = 1; j < fig.length; j++) {
						this.ctx.lineTo(fig[j].x, fig[j].y);
					}
				}
			}
			this.ctx.strokeStyle = "red";
			this.ctx.stroke();
			this.ctx.strokeStyle = "black";
		}
	};
}