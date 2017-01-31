package org.fao.fenix.web.modules.r.server;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.r.FenixMenuItem;
import org.fao.fenix.core.r.RFunction;
import org.fao.fenix.core.r.ROption;
import org.fao.fenix.core.r.ROutput;
import org.fao.fenix.core.r.RPlot;
import org.fao.fenix.core.r.RResult;
import org.fao.fenix.core.r.RUserSettings;
import org.fao.fenix.core.r.RUtils;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.r.common.services.RService;
import org.fao.fenix.web.modules.r.common.vo.RFunctionVO;
import org.fao.fenix.web.modules.r.common.vo.ROptionVO;
import org.fao.fenix.web.modules.r.common.vo.ROutputVO;
import org.fao.fenix.web.modules.r.common.vo.RPlotVO;
import org.fao.fenix.web.modules.r.common.vo.RResultVO;
import org.fao.fenix.web.modules.r.common.vo.RUserSettingsVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RServiceImpl extends RemoteServiceServlet implements RService {

	private RUtils rUtils;
	
	public RResultVO calculate(RUserSettingsVO usvo) throws FenixGWTException {
		RUserSettings us = vo2us(usvo);
		RResult r = null;
		try {
			r = rUtils.calculate(us);
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
		RResultVO rvo = rr2vo(r);
		return rvo;
	}
	
	public RFunctionVO parseFunction(String command) throws FenixGWTException {
		RFunction f = rUtils.parseFunction(command);
		RFunctionVO vo = f2vo(f);
		return vo;
	}
	
	public List<FenixMenuItemVo> parseMenu() throws FenixGWTException {
		List<FenixMenuItemVo> vos = new ArrayList<FenixMenuItemVo>();
		try {
			List<FenixMenuItem> mis = rUtils.parseMenu();
			for (FenixMenuItem mi : mis)
				vos.add(mi2vo(mi));
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return vos;
	}
	
	private RFunctionVO f2vo(RFunction f) {
		RFunctionVO vo = new RFunctionVO();
		vo.setCommand(f.getCommand());
		vo.setDescription(f.getDescription());
		vo.setInputs(f.getInputs());
		vo.setName(f.getName());
		vo.setrFormula(f.getrFormula());
		vo.setRepeat(f.isRepeat());
		vo.setRepeatRows(f.getRepeatRows());
		for (ROption o : f.getOptions())
			vo.addOption(o2vo(o));
		for (ROutput o : f.getOutputs())
			vo.addOutput(out2vo(o));
		for (RPlot p : f.getPlots())
			vo.addPlot(p2vo(p));
		return vo;
	}
	
	private ROutputVO out2vo(ROutput o) {
		ROutputVO ovo = new ROutputVO();
		ovo.setOutputIndex(o.getOutputIndex());
		ovo.setOutputName(o.getOutputName());
		ovo.setOutputType(o.getOutputType());
		ovo.setSquareMatrix(o.isSquareMatrix());
		ovo.setRepeatRows(o.getRepeatRows());
		return ovo;
	}
	
	private RFunction vo2f(RFunctionVO vo) {
		RFunction f = new RFunction();
		f.setCommand(vo.getCommand());
		f.setDescription(vo.getDescription());
		f.setInputs(vo.getInputs());
		f.setName(vo.getName());
		f.setrFormula(vo.getrFormula());
		f.setRepeat(vo.isRepeat());
		f.setRepeatRows(vo.getRepeatRows());
		for (ROptionVO ovo : vo.getOptions())
			f.addOption(vo2o(ovo));
		for (ROutputVO ovo : vo.getOutputs())
			f.addOutput(vo2out(ovo));
		for (RPlotVO pvo : vo.getPlots())
			f.addPlot(vo2p(pvo));
		return f;
	}
	
	private ROutput vo2out(ROutputVO vo) {
		ROutput o = new ROutput();
		o.setOutputIndex(vo.getOutputIndex());
		o.setOutputName(vo.getOutputName());
		o.setOutputType(vo.getOutputType());
		o.setSquareMatrix(vo.isSquareMatrix());
		o.setRepeatRows(vo.getRepeatRows());
		return o;
	}
	
	private ROptionVO o2vo(ROption o) {
		ROptionVO vo = new ROptionVO();
		vo.setDefaultValue(o.getDefaultValue());
		vo.setOptionCode(o.getOptionCode());
		vo.setOptionName(o.getOptionName());
		vo.setOptionDescription(o.getOptionDescription());
		for (String optionValue : o.getOptionValues())
			vo.addOptionValue(optionValue);
		return vo;
	}
	
	private ROption vo2o(ROptionVO vo) {
		ROption o = new ROption();
		o.setDefaultValue(vo.getDefaultValue());
		o.setOptionCode(vo.getOptionCode());
		o.setOptionDescription(vo.getOptionDescription());
		o.setOptionName(vo.getOptionName());
		for (String optionValue : vo.getOptionValues())
			o.addOptionValue(optionValue);
		return o;
	}
	
	private FenixMenuItemVo mi2vo(FenixMenuItem mi) {
		FenixMenuItemVo vo = new FenixMenuItemVo();
		vo.setChildren(mi.isChildren());
		vo.setCommand(mi.getCommand());
		vo.setIconStyle(mi.getIconStyle());
		vo.setLevel(mi.getLevel());
		vo.setName(mi.getName());
		vo.setParent(mi.getParent());
		vo.setTooltip(mi.getTooltip());
		return vo;
	}
	
	private RUserSettings vo2us(RUserSettingsVO vo) {
		RUserSettings us = new RUserSettings();
		us.setMainDimension(vo.getMainDimension());
		us.setMainDimensionValues(vo.getMainDimensionValues());
		us.setOptions(vo.getOptions());
		us.setOtherDimensions(vo.getOtherDimensions());
		us.setOtherDimensionsValues(vo.getOtherDimensionsValues());
		us.setDatasetID(vo.getDatasetID());
		us.setrFunction(vo2f(vo.getrFunctionVO()));
		return us;
	}
	
	private RResultVO rr2vo(RResult r) {
		RResultVO vo = new RResultVO();
		vo.setPlots(r.getPlots());
		vo.setResults(r.getResults());
		vo.setResultType(r.getResultType());
		return vo;
	}
	
	private RPlot vo2p(RPlotVO vo) {
		RPlot p = new RPlot();
		p.setDescription(vo.getDescription());
		p.setName(vo.getName());
		p.setRepeat(vo.isRepeat());
		p.setrFormula(vo.getrFormula());
		for (ROptionVO ovo : vo.getOptions())
			p.addOption(vo2o(ovo));
		return p;
	}
	
	private RPlotVO p2vo(RPlot p) {
		RPlotVO vo = new RPlotVO();
		vo.setDescription(p.getDescription());
		vo.setName(p.getName());
		vo.setRepeat(p.isRepeat());
		vo.setrFormula(p.getrFormula());
		for (ROption o : p.getOptions())
			vo.addOption(o2vo(o));
		return vo;
	}

	public void setrUtils(RUtils rUtils) {
		this.rUtils = rUtils;
	}
	
}