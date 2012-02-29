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
package org.canvasimageeditor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.canvasimageeditor.draw.util.DrawerUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class CanvasImageEditorAction
 */
public class CanvasImageEditorAction extends MVCPortlet {
	
	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		
		final String resourceId = resourceRequest.getResourceID();

		if ("json".equals(resourceId)) {
			// final JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			
			final String portletId = PortalUtil.getPortletId(resourceRequest);
			final Long userId = PortalUtil.getUserId(resourceRequest);
			
			final List<String> figures = DrawerUtil.getUserNewFigures(portletId, userId);
			System.out.println("FOR JSON ==================> " + figures.size());
			
			final JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			final String json = jsonSerializer.serialize(figures);
			final JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			jsonObj.put("newFigures", json);
			
			final PrintWriter writer = resourceResponse.getWriter();
			writer.write(jsonObj.toString());
		}
	}
	
	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		
		final String cmd = actionRequest.getParameter(Constants.CMD);
		final String portletId = PortalUtil.getPortletId(actionRequest);
		final Long userId = PortalUtil.getUserId(actionRequest);
		if ("polling".equals(cmd)) {
			
		} else if ("add_figure".equals(cmd)) {
			final String newFigure = actionRequest.getParameter("new_figure");
			System.out.println("==========> new : " + newFigure);
			if (newFigure != null) {
				DrawerUtil.addFigure(portletId, newFigure, userId);
			}
		}
		
		super.processAction(actionRequest, actionResponse);
	}
	
}
