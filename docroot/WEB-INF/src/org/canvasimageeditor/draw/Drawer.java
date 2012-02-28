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

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Drawer implements Entry<Long, Set<Integer>> {

	private final Long userId;
	private Set<Integer> figures;
	
	public Drawer(final Long userId) {
		this.userId = userId;
	}

	public void addFigureId(final Integer figureId) {
		figures.add(figureId);
	}

	public Long getKey() {
		return userId;
	}

	public Set<Integer> getValue() {
		return figures;
	}

	public Set<Integer> setValue(Set<Integer> value) {
		this.figures = value;
		return this.figures;
	}
	
	public List<Integer> getDiff(final List<Integer> ids) {
		final List<Integer> diff = new ArrayList<Integer>();
		for (final Integer i : ids) {
			if (!figures.contains(i)) {
				diff.add(i);
			}
		}
		return diff;
	}
}
