/*
 */

package org.fao.fenix.web.modules.map.common.vo;

/**
 *
 * @author etj
 */
public class Counter {

	private long cnt;

	public Counter() {
		this(0);
	}

	public Counter(long firstValue) {
		this.cnt = firstValue;
	}

	public synchronized long getNext() {
		return cnt++;
	}

}
