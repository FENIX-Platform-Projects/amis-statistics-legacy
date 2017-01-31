/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */


package org.fao.fenix.web.modules.text.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.Source;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.textinfo.Text;
import org.fao.fenix.core.domain.textinfo.TextGroup;
import org.fao.fenix.core.persistence.SaveUniqueDao;
import org.fao.fenix.core.persistence.perspective.TextDao;
import org.fao.fenix.core.persistence.perspective.TextGroupDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.persistence.security.ResourcePermission;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.text.common.services.TextService;
import org.fao.fenix.web.modules.text.common.vo.TextGroupVO;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TextServiceImpl extends RemoteServiceServlet implements TextService {

	private static final long serialVersionUID = 7503378722300849826L;
	TextDao textDao;	
	TextGroupDao textGroupDao;	
	FenixPermissionManager fenixPermissionManager;
	SaveUniqueDao saveUniqueDao;

	Logger LOGGER = Logger.getLogger(TextServiceImpl.class);

	/***
	 * TEXT and TEXT RESOURCE RESOURCE METHODS
	*/
	public Long saveText(String title, String content, String referenceDate) {

		TextView tv;

		// Format and parse the reference date
		Date refDate = null;

		if (referenceDate != null && !referenceDate.equals("null")) {
			refDate = FieldParser.parseDate(referenceDate);
		} else
			refDate = new Date(); /* today's date */

		LOGGER.info("ReferenceDate set as " + refDate);
		tv = new TextView();

		tv.setTitle(title);
		tv.setDateLastUpdate(new Date()); /* today's date */

		Text text = new Text();

		text.setContent(content);
		text.setReferenceDate(refDate); /* today's date */

		tv.setText(text);

		textDao.save(tv);

		return tv.getResourceId();
	}
	
	public Long saveTextGroup(String title, List<Long> textIds) {

		TextGroup textGroup= new TextGroup();
		textGroup.setTitle(title);
		textGroup.setDateLastUpdate(new Date());
		
		List<TextView> textViewList = new ArrayList<TextView>();
		
		if(textIds!=null){
			for(Long id: textIds){
				TextView tv = textDao.findById(id);
				textViewList.add(tv);
			}
			textGroup.setTextList(textViewList);
		}
			
		textGroupDao.save(textGroup);
		
		return textGroup.getResourceId();
	}
		
	public FenixResourceVo getNewTextResource(Long id) {

		TextView tv = textDao.findById(id);
		FenixResourceVo fenixResource = null;
		
		PermissionVo permissionVo = getPermissions(id);
		
		if (tv != null) {
			fenixResource = FenixResourceBuilder.build(textDao.findById(id), permissionVo);
		}

		return fenixResource;
	}
	
	public FenixResourceVo getNewTextGroupResource(Long id) {

		TextGroup tg = textGroupDao.findById(id);
		FenixResourceVo fenixResource = null;
		
		PermissionVo permissionVo = getPermissions(id);
		
		if (tg != null) {
			fenixResource = FenixResourceBuilder.build(textGroupDao.findById(id), permissionVo);
		}

		return fenixResource;
	}

	public Long updateText(Long textId, String content, String referenceDate) {
       Date refDate = null;
		
		if (referenceDate != null && !referenceDate.equals("null")) {
			refDate = FieldParser.parseDate(referenceDate);
		} else
			refDate = new Date(); /* today's date */

		System.out.println("refDate = " + refDate);
        LOGGER.info("Overwriting/Updating text ...");
		TextView tv = textDao.findById(textId);
		tv.setDateLastUpdate(new Date());
		Text text = tv.getText();
		text.setContent(content);

		text.setReferenceDate(refDate); 

		textDao.update(tv);
		return tv.getResourceId();
	}
	
	public Long updateTextGroup(Long textGroupId, List<Long> textIds) {
		TextGroup tg = textGroupDao.findById(textGroupId);
		tg.setDateLastUpdate(new Date());

        List<TextView> textViewList = new ArrayList<TextView>();
		
		for(Long id: textIds){
			TextView tv = textDao.findById(id);
			textViewList.add(tv);
		}
		
		tg.setTextList(textViewList);
		
		textGroupDao.update(tg);

		return tg.getResourceId();
	}

	public Long addTextToTextGroup(Long textGroupId, List<Long> textIds) {
		TextGroup tg = textGroupDao.findById(textGroupId);
		tg.setDateLastUpdate(new Date());
	
		for(Long id: textIds){
			TextView tv = textDao.findById(id);
			tg.addText(tv);
		}
			
		textGroupDao.update(tg);

		return tg.getResourceId();
	}
	
	public Long removeTextFromTextGroup(Long textGroupId, Long textId) {
		TextGroup tg = textGroupDao.findById(textGroupId);
		tg.setDateLastUpdate(new Date());
	
		TextView tv = textDao.findById(textId);
		tg.removeText(tv);
		
		textGroupDao.update(tg);

		return tg.getResourceId();
	}
	
	
	public List<String>/* <String> */getText(Long id) {
		List<String> dimensions = new ArrayList<String>();

		try {
			TextView textview = textDao.findById(id);
			if (textview != null) {
				// text's ID
				dimensions.add(String.valueOf(textview.getResourceId()));
				// text's title
				dimensions.add(textview.getTitle());
				// text's content
				dimensions.add((textview.getText()).getContent());
				// text's reference date
				dimensions.add(String.valueOf((textview.getText()).getReferenceDate()));
				// text's last updated date
				dimensions.add(String.valueOf(textview.getDateLastUpdate()));
			} else
				LOGGER.error("TextView is NULL");
		} catch (Exception ex) {
			System.out.println("getText(id) ERROR");
			ex.printStackTrace();
		}

		return dimensions;
	}
	
	private TextGroupVO createTextGroupVO(TextGroup textGroup) {
		TextGroupVO vo = new TextGroupVO();
		vo.setTitle(textGroup.getTitle());
		vo.setResourceId(textGroup.getResourceId());
		vo.setTextList(getTextsList(textGroup.getTextList()));
		
		vo.setResourceId(textGroup.getResourceId());
		vo.setTitle(textGroup.getTitle());
		if(textGroup.getDateLastUpdate()!=null)
			vo.setDateLastUpdate(textGroup.getDateLastUpdate());
		if(textGroup.getStartDate()!=null)
			vo.setStartDate(textGroup.getStartDate());
		if(textGroup.getEndDate()!=null)
			vo.setEndDate(textGroup.getEndDate());
		
		vo.setAbstractAbstract(textGroup.getAbstractAbstract());
		vo.setKeywords(textGroup.getKeywords());
		vo.setCode(textGroup.getCode());
		vo.setRegion(textGroup.getRegion());
		vo.setSharingCode(textGroup.getSharingCode());
		vo.setCategories(textGroup.getCategories());
		
		if(textGroup.getSource()!=null){
			LOGGER.info("textGroup source title = "+ textGroup.getProvider().getTitle() + ": textGroup contact = "+textGroup.getProvider().getContact() + ": textGroup region = "+textGroup.getProvider().getCountry());
			
			vo.setSource(textGroup.getSource().getTitle());
			vo.setSourceContact(textGroup.getSource().getContact());
			vo.setSourceRegion(textGroup.getSource().getCountry());
		}
		
		if(textGroup.getProvider()!=null){

			LOGGER.info("textGroup provider title = "+ textGroup.getProvider().getTitle() + ": textGroup contact = "+textGroup.getProvider().getContact() + ": textGroup region = "+textGroup.getProvider().getCountry());
		
			vo.setProvider(textGroup.getProvider().getTitle());
			vo.setProviderContact(textGroup.getProvider().getContact());
			vo.setProviderRegion(textGroup.getProvider().getCountry());
		}
		
		PermissionVo permissionVo = getPermissions(textGroup.getResourceId());
		
		LOGGER.info("textGroup write access = "+ permissionVo.canWrite());
		
		vo.setHasWritePermission(permissionVo.canWrite());
		vo.setHasDeletePermission(permissionVo.canDelete());
		vo.setHasDownloadPermission(permissionVo.canDownload());
		
		return vo;
	}
	
	private List<TextVO> getTextsList(List<TextView> textViewList) {
		List<TextVO> textVOList = new ArrayList<TextVO>();

		for (TextView textview: textViewList) {
			LOGGER.info("textview abstract = "+ textview.getAbstractAbstract() + ": textview categories = "+textview.getCategories() + ": textview region = "+textview.getRegion());
			
			PermissionVo permissions = getPermissions(textview.getResourceId());
			TextVO vo = createTextVO(textview, permissions);
			
			textVOList.add(vo);
		}

		System.out.println("getTextsList size = " + textVOList.size());

		return textVOList;
	}

	public TextGroupVO getTextGroup(Long id) {
		TextGroup textgroup = textGroupDao.findById(id);
		if (textgroup != null)
			return createTextGroupVO(textgroup);
		else
			return null;	
	}
	
	public List<TextVO> getTextsList(HashMap<Long, PermissionVo> textMap) {
		List<TextVO> textVOList = new ArrayList<TextVO>();

		Iterator<Map.Entry<Long, PermissionVo>> it = textMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Long,PermissionVo> entry = it.next();
			PermissionVo permissionVo = entry.getValue();
			TextView textview = textDao.findById(entry.getKey());
			if (textview != null) {
				LOGGER.info("textview abstract = "+ textview.getAbstractAbstract() + ": textview categories = "+textview.getCategories() + ": textview region = "+textview.getRegion());
				TextVO vo = createTextVO(textview, permissionVo);
				
				textVOList.add(vo);
			}
			else
				System.out.println("TextView is NULL");

		}

		System.out.println("getTextsList size = " + textVOList.size());

		return textVOList;
	}

	public FenixResourceVo cloneTextGroup(TextGroupVO textGroupVO, List<TextVO> list, String referenceDate){
		List<TextView> textViewList = new ArrayList<TextView>();
		
		LOGGER.info("textGroupVO title = "+ textGroupVO.getTitle() + ": referenceDate = "+referenceDate + " text list size: "+list.size());
		
		for(TextVO textVO: list){
			textViewList.add(createClonedTextView(textVO, referenceDate));			
		}
		
		Long newTextGroupId = createClonedTextGroup(textGroupVO, referenceDate, textViewList);
		
		return getNewTextGroupResource(newTextGroupId);
	}
	
	private TextVO createTextVO(TextView textview, PermissionVo permissionVo){
		LOGGER.info("textview abstract = "+ textview.getAbstractAbstract() + ": textview categories = "+textview.getCategories() + ": textview region = "+textview.getRegion());
		
		TextVO vo = new TextVO();
		vo.setResourceId(textview.getResourceId());
		vo.setTitle(textview.getTitle());
		vo.setContent(textview.getText().getContent());
		if(textview.getText().getReferenceDate()!=null)
			vo.setReferenceDate(textview.getText().getReferenceDate().toString());
		if(textview.getDateLastUpdate()!=null)
			vo.setDateLastUpdate(textview.getDateLastUpdate());
		if(textview.getStartDate()!=null)
			vo.setStartDate(textview.getStartDate());
		if(textview.getEndDate()!=null)
			vo.setEndDate(textview.getEndDate());
		
		vo.setAbstractAbstract(textview.getAbstractAbstract());
		vo.setKeywords(textview.getKeywords());
		vo.setCode(textview.getCode());
		vo.setRegion(textview.getRegion());
		vo.setSharingCode(textview.getSharingCode());
		vo.setCategories(textview.getCategories());
		if(textview.getSource()!=null){
			LOGGER.info("textview source title = "+ textview.getProvider().getTitle() + ": textview contact = "+textview.getProvider().getContact() + ": textview region = "+textview.getProvider().getCountry());
			
			vo.setSource(textview.getSource().getTitle());
			vo.setSourceContact(textview.getSource().getContact());
			vo.setSourceRegion(textview.getSource().getCountry());
		}
		if(textview.getProvider()!=null){

			LOGGER.info("textview provider title = "+ textview.getProvider().getTitle() + ": textview contact = "+textview.getProvider().getContact() + ": textview region = "+textview.getProvider().getCountry());
		
			vo.setProvider(textview.getProvider().getTitle());
			vo.setProviderContact(textview.getProvider().getContact());
			vo.setProviderRegion(textview.getProvider().getCountry());
		}
		
		LOGGER.info("textview write access = "+ permissionVo.canWrite());
		
		vo.setHasWritePermission(permissionVo.canWrite());
		vo.setHasDeletePermission(permissionVo.canDelete());
		vo.setHasDownloadPermission(permissionVo.canDownload());
		
		return vo;
	}
	
	private TextView createClonedTextView(TextVO textVO, String referenceDate){
		TextView view = new TextView();
		view.setTitle(textVO.getTitle() +" (" + referenceDate + ")");
		
		LOGGER.info("content = "+ textVO.getContent());
		Text text = new Text();
		
		text.setContent(textVO.getContent());
		if(referenceDate!=null)
			text.setReferenceDate(FieldParser.parseDate(referenceDate));
		else 
			text.setReferenceDate(new Date());
		
		view.setText(text);
		
		view.setDateLastUpdate(new Date());
		
		if(textVO.getStartDate()!=null)
			view.setStartDate(textVO.getStartDate());
		if(textVO.getEndDate()!=null)
			view.setEndDate(textVO.getEndDate());
		
		view.setAbstractAbstract(textVO.getAbstractAbstract());
		view.setKeywords(textVO.getKeywords());
		view.setCode(textVO.getCode());
		view.setRegion(textVO.getRegion());
		view.setSharingCode(textVO.getSharingCode());
		view.setCategories(textVO.getCategories());
		if(textVO.getSource()!=null){
			view.setSource(saveUniqueDao.saveUnique(new Source(textVO.getSource(), textVO.getSourceRegion(), textVO.getSourceContact())));
		}
		
		if(textVO.getProvider()!=null){
			view.setProvider(saveUniqueDao.saveUnique(new Source(textVO.getProvider(), textVO.getProviderRegion(), textVO.getProviderContact())));
		}
		
		LOGGER.info("original abstract = "+ textVO.getAbstractAbstract() + ": original categories = "+textVO.getCategories() + ": original region = "+textVO.getRegion());
		
		textDao.save(view);
		
		return view;
	}
	
	private Long createClonedTextGroup(TextGroupVO textGroupVO, String referenceDate, List<TextView> textViewList){
		TextGroup view = new TextGroup();
		view.setTitle(textGroupVO.getTitle() +" (" + referenceDate + ")");
		view.setTextList(textViewList);
		view.setDateLastUpdate(new Date());
		
		if(textGroupVO.getStartDate()!=null)
			view.setStartDate(textGroupVO.getStartDate());
		if(textGroupVO.getEndDate()!=null)
			view.setEndDate(textGroupVO.getEndDate());
		
		view.setAbstractAbstract(textGroupVO.getAbstractAbstract());
		view.setKeywords(textGroupVO.getKeywords());
		view.setCode(textGroupVO.getCode());
		view.setRegion(textGroupVO.getRegion());
		view.setSharingCode(textGroupVO.getSharingCode());
		view.setCategories(textGroupVO.getCategories());
		
		if(textGroupVO.getSource()!=null){
			LOGGER.info("original BEFORE: source = "+ textGroupVO.getSource() + ": contact = "+textGroupVO.getSourceContact() + ": region = "+textGroupVO.getSourceRegion());
			
			view.setSource(saveUniqueDao.saveUnique(new Source(textGroupVO.getSource(), textGroupVO.getSourceRegion(), textGroupVO.getSourceContact())));
			
			LOGGER.info("view AFTER: source = "+ view.getSource().getTitle() + ": contact = "+view.getSource().getContact() + ": region = "+view.getSource().getCountry());
			
		}
		
		if(textGroupVO.getProvider()!=null){
			view.setProvider(saveUniqueDao.saveUnique(new Source(textGroupVO.getProvider(), textGroupVO.getProviderRegion(), textGroupVO.getProviderContact())));
		}
		
		
		textGroupDao.save(view);
		
		return view.getResourceId();
	}
	
	private PermissionVo getPermissions(long resourceId) {
		ResourcePermission permission = fenixPermissionManager.getPermissions(resourceId);

		PermissionVo vo = new PermissionVo();
		vo.setCanRead(permission.isHasReadPermission());
		vo.setCanWrite(permission.isHasWritePermission());
		vo.setCanDelete(permission.isHasDeletePermission());
		vo.setCanDownload(permission.isHasDownloadPermission());

		return vo;
	}
	
	public void setTextDao(TextDao textDao) {
		this.textDao = textDao;
	}
	
	public void setTextGroupDao(TextGroupDao textGroupDao) {
		this.textGroupDao = textGroupDao;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}
	
	public SaveUniqueDao getSaveUniqueDao() {
		return saveUniqueDao;
	}

	public void setSaveUniqueDao(SaveUniqueDao saveUniqueDao) {
		this.saveUniqueDao = saveUniqueDao;
	}
}
