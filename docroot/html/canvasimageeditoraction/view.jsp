<%--
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
--%>

<%@page import="com.liferay.portal.kernel.util.ServerDetector"%>
<%@page import="com.liferay.util.Encryptor"%>
<%@page import="com.liferay.portal.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.model.User"%>
<%@ include file="init.jsp" %>

<portlet:defineObjects />

<portlet:actionURL var="drawerAction">
</portlet:actionURL>

<portlet:resourceURL var="jsonUrl" id="json" />

<aui:script>
var <portlet:namespace />draw = null;
Liferay.Portlet.ready(function() {
	<portlet:namespace />draw = new Drawer();
	<portlet:namespace />draw.init("<portlet:namespace />drawerCanvas", "<%= drawerAction %>");
	<portlet:namespace />polling();
});

function <portlet:namespace />polling() {
	AUI().use('aui-io-request', function(A) {  
		A.io.request("<%= jsonUrl %>", { 
			method: 'POST', 
			data: { cmd: "polling" },
			on: {
				success:  function() {
					<portlet:namespace />processResponse(this.get('responseData'));
				} 
			} 
		});
	});
	var tmr=setTimeout("<portlet:namespace />polling()",2000);
}

function <portlet:namespace />processResponse(json) {
	AUI().use("aui-io", function(A) {
		var response = A.JSON.parse(json);
		var newFigs = response.newFigures;
		if (newFigs != "[]") {
			var div = A.one("#<portlet:namespace />output");
			var figsArray = eval(newFigs);
			<portlet:namespace />draw.drawFigures(figsArray);
		}
	});
}
</aui:script>

<canvas id="<portlet:namespace />drawerCanvas" style="border:1px solid #000;">
PAS DE CANVAS
</canvas>