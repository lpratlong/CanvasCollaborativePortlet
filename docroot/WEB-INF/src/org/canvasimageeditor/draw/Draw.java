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

import java.util.Collections;
import java.util.HashMap;

public class Draw extends HashMap<Integer, String> {

	public Draw() {
		super();
	}
	
	public Integer put(String value) {
		final Integer maxKey = getMaxKey();
		super.put(maxKey, value);
		return maxKey;
	}
	
	@Override
	public String put(Integer key, String value) {
		if (containsKey(key)) {
			return super.put(key, value);
		} else {
			return put(getMaxKey(), value);
		}
	}
	
	private Integer getMaxKey() {
		if (size() == 0) return 1;
		return Collections.max(keySet()) + 1;
	}
}
