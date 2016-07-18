package org.fao.fenix.web.modules.re.client.view;

import java.util.List;

import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.Grid;

/**
 * 
 * CatalogueSimple: TreeTable whose child items are loaded at once i.e. For resource's with no associated child resources. 
 *
 */
public class CatalogueSimple extends Catalogue {

	public CatalogueSimple(final ResourceExplorer resourceExplorer) {
		super(resourceExplorer);
	
		List<ColumnConfig> standardColConfig = getStandardColumnConfigurations();
	    setStandardColumnModel(standardColConfig);
		grid = new Grid<ResourceChildModel>(getStore(), getStandardColumnModel());
		
		setGridProperties();

	}

}