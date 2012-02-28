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
package org.canvasimageeditor.draw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.canvasimageeditor.draw.Draw;

public class DrawerUtil {

	public static final Map<String, Draw> DRAWS = new HashMap<String, Draw>();
	public static final Map<Long, Set<Integer>> DRAWERS = new HashMap<Long, Set<Integer>>();
	
	public static void addFigure(final String portletId, final String figure, final Long userId) {
		final Draw draw;
		if (DRAWS.containsKey(portletId)) {
			draw = DRAWS.get(portletId);
		} else {
			draw = new Draw();
			DRAWS.put(portletId, draw);
		}
		final Integer id = draw.put(figure);
		final Set<Integer> s = getUserFigures(userId);
		s.add(id);
	}
	
	public static List<String> getUserNewFigures(final String portletId, final Long userId) {
		final List<String> newFigures = new ArrayList<String>();
		if (userId != null) {
			final Draw d = DRAWS.get(portletId);
			if (d != null) {
				final Set<Integer> s = getUserFigures(userId);		
				
				final List<Integer> diff = new ArrayList<Integer>(d.keySet());
				diff.removeAll(s);
				for (final Integer i : diff) {
					s.add(i);
					newFigures.add(d.get(i));
				}
			}
		}
		return newFigures;
	}
	
	private static Set<Integer> getUserFigures(final Long userId) {
		final Set<Integer> s;
		if (!DRAWERS.containsKey(userId)) {
			s = new HashSet<Integer>();
			DRAWERS.put(userId, s);
		} else {
			s = DRAWERS.get(userId);
		}
		return s;
	}
	
	/*
	private static List<Integer> getDiff(final List<Integer> s, final Set<Integer> d) {
		if ((d == null) || (d.isEmpty())) return new ArrayList<Integer>(s);
		if (s != null) {
			final List<Integer> diff = new ArrayList<Integer>();
			for (final Integer i : s) {
				if (!d.contains(i)) {
					diff.add(i);
				}
			}
			return diff;
		} else {
			return null;
		}
	} */
}
