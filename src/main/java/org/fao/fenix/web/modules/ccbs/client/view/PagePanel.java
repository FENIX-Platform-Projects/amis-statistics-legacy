package org.fao.fenix.web.modules.ccbs.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.ccbs.common.vo.Book;
import org.fao.fenix.web.modules.ccbs.common.vo.Cell;
import org.fao.fenix.web.modules.ccbs.common.vo.Page;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;

public class PagePanel extends Composite implements Book.UpdateListener
{
	private PageDecoration decoration;
	private Page           page;
	private CCBSStore      store;
	private ColumnModel    cm;
	private Grid           grid;

	public PagePanel(PageDecoration decoration)
	{
		this.decoration = decoration;

		store = new CCBSStore(decoration);
		cm    = makeColumnModel();
		grid  = makeGrid();

		initComponent(grid);
		grid.mask();
	}

	public void load(Page page)
	{
		this.page = page;

		store.loadPage(page);
		page.getBook().addBookUpdateListener(this);
		grid.unmask();
	}

	public void unload()
	{
		store.unloadPage();
		grid.mask();
	}

	public void updateView()
	{
		store.updatePage();
	}

	public PageDecoration getDecoration() { return decoration; }

	public Grid makeGrid()
	{
		EditorGrid  grid = new EditorGrid<CCBSModel>(store, cm);

		grid.addListener(Events.BeforeEdit, new Listener<GridEvent>()
		{
			public void handleEvent(GridEvent gridEvent)
			{
				if (!isCellEditable(gridEvent.getRowIndex(), gridEvent.getColIndex())) gridEvent.setCancelled(true);
			}
		});
		grid.addListener(Events.AfterEdit, new Listener<GridEvent>()
		{
			public void handleEvent(GridEvent gridEvent)
			{
				String value  = gridEvent.getValue().toString();
				int    rowNum = gridEvent.getRowIndex();
				int    colNum = gridEvent.getColIndex() - 1;

				Cell cell = page.getCell(rowNum, colNum);
				cell.setText(value);
				cell.setValid(false);

				try
				{
					page.getBook().update();
				}
				catch (Exception e)
				{
					FenixAlert.error("Error", "Recalculation failed");

					e.printStackTrace();
				}
			}
		});
		return grid;
	}

	private boolean isCellEditable(int rowIndex, int colIndex)
	{
		return decoration.isRowEditable(rowIndex) && colIndex != 0 && decoration.isColEditable(colIndex - 1);
	}

	public void bookUpdated()
	{
		// maybe faster using this
	}

	public void cellUpdated(int pageNum, int rowNum, int colNum)
	{
		if (pageNum == page.getPageNum())
		{
			String value = page.getCell(rowNum, colNum).getText();

			int rowIndex = rowNum;
			int colIndex = colNum + 1;

			String    property = cm.getColumnId(colIndex);
			CCBSModel model    = store.getAt(rowIndex);

			model.set(property, value);
			store.update(model);
		}
	}

	class PageCellRenderer implements GridCellRenderer<CCBSModel>
	{
		public String render(CCBSModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<CCBSModel> store)
		{
			if (colIndex == 0)
			{
				if (!decoration.isRowEditable(rowIndex))  config.style += "background-color: lightgray;";
			}
			else if (!isCellEditable(rowIndex, colIndex)) config.style += "background-color: lightgray;";

			return model.get(property, "");
		}

		public Object render(CCBSModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<CCBSModel> store, Grid<CCBSModel> grid)
		{
				return render(model, property, config, rowIndex, colIndex, store);
		}
	}

	private ColumnModel makeColumnModel()
	{
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig headersColumn = new ColumnConfig();
		headersColumn.setId("header");
		headersColumn.setHeader(decoration.getTitle());
		headersColumn.setAlignment(Style.HorizontalAlignment.RIGHT);
		headersColumn.setWidth(decoration.getRowHeadersWidth());
		headersColumn.setMenuDisabled(true);
		headersColumn.setSortable(false);
		headersColumn.setRenderer(new PageCellRenderer());
		configs.add(headersColumn);

		for (int colNum = 0; colNum < decoration.numCols() ; colNum++)
		{
			ColumnConfig column = new ColumnConfig();
			column.setId("col_" + colNum);
			column.setHeader(decoration.getColHeader(colNum));
			column.setAlignment(Style.HorizontalAlignment.RIGHT);
			column.setWidth(decoration.getColHeaderWidth(colNum));
			column.setMenuDisabled(true);
			column.setSortable(false);
			column.setEditor(new CellEditor(new TextField()));
			column.setRenderer(new PageCellRenderer());
			configs.add(column);
		}
		return new ColumnModel(configs);
	}



	private class CCBSStore extends ListStore<CCBSModel>
	{
		private PageDecoration decoration;

		public CCBSStore(PageDecoration decoration)
		{
			this.decoration = decoration;

			for (int rowNum = 0; rowNum < decoration.numRows(); rowNum++)
			{
				CCBSModel model = new CCBSModel(decoration, rowNum);
				add(model);
			}
		}

		public void loadPage(Page page)
		{
			this.rejectChanges(); // FIXME: should possibly save first
			for (int rowNum = 0; rowNum < page.numRows(); rowNum++)
			{
				CCBSModel model = getAt(rowNum);
				model.loadRow(page);
				update(model);
			}
		}

		public void unloadPage()
		{
			this.rejectChanges(); // FIXME: should possibly save first
			for (int rowNum = 0; rowNum < page.numRows(); rowNum++)
			{
				CCBSModel model = getAt(rowNum);
				model.unloadRow();
				update(model);
			}
		}

		public void updatePage()
		{
			for (int rowNum = 0; rowNum < decoration.numRows(); rowNum++)
			{
				CCBSModel model = getAt(rowNum);
				update(model);
			}
		}
	}

	private class CCBSModel extends BaseModel
	{
		private PageDecoration decoration;
		private int            rowNum;

		private Page           page;

		public CCBSModel(PageDecoration decoration, int rowNum)
		{
			this.decoration = decoration;
			this.rowNum     = rowNum;

			set("header", decoration.getRowHeader(rowNum));
			for (int colNum = 0; colNum < decoration.numCols() ; colNum++)
				set("col_" + colNum, "");
		}

		public void loadRow(Page page)
		{
			this.page = page;

			for (int colNum = 0; colNum < decoration.numCols() ; colNum++)
				set("col_" + colNum, page.getCell(rowNum, colNum).getText());
		}

		public void unloadRow()
		{
			for (int colNum = 0; colNum < decoration.numCols() ; colNum++)
				set("col_" + colNum, "");
		}
	}
}
