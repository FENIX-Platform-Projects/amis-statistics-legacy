package org.fao.fenix.web.modules.pm.server;

import java.util.Comparator;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;

public class FenixResourceVoComparator implements Comparator<FenixResourceVo> {

	@Override
	public int compare(FenixResourceVo voOne, FenixResourceVo voTwo) {
		return voOne.getType().compareToIgnoreCase(voTwo.getType());
	}
	
}