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
package org.canvasimageeditor.draw;

public class Figure {

	private int id;
	private String clientRepresentation;
	
	public Figure(final int id, final String clientRepresentation) {
		this.id = id;
		this.clientRepresentation = clientRepresentation;
	}
	
	public String getClientRepresentation() {
		return clientRepresentation;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setClientRepresentation(String clientRepresentation) {
		this.clientRepresentation = clientRepresentation;
	}
}
