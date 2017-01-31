package org.fao.fenix.web.modules.re.client.control;

import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class CataloguePagerController {

	@SuppressWarnings("deprecation")
	public static SelectionListener<ButtonEvent> deselectedResources(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				resourceExplorer.getWindow().close();
			}
		};
	}

	public static SelectionListener<ButtonEvent> goToNextPage(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				if (resourceExplorer.getCataloguePager().getFenixPager().goToNextPage()) {
					int from = resourceExplorer.getCataloguePager().getFenixPager().calculateIndexFrom();
					int to = resourceExplorer.getCataloguePager().getFenixPager().calculateIndexTo();
					CatalogueController.fillGridUpdate(resourceExplorer.getCatalogue().getGrid(), resourceExplorer.getCatalogue().getStore(), resourceExplorer.getSearchButtons().getFenixSearchParameters(), resourceExplorer.getCataloguePager(), from, to);
				}

			}
		};
	}

	public static SelectionListener<ButtonEvent> goToPreviousPage(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				if (resourceExplorer.getCataloguePager().getFenixPager().goToPreviousPage()) {
					int from = resourceExplorer.getCataloguePager().getFenixPager().calculateIndexFrom();
					int to = resourceExplorer.getCataloguePager().getFenixPager().calculateIndexTo();
					CatalogueController.fillGridUpdate(resourceExplorer.getCatalogue().getGrid(), resourceExplorer.getCatalogue().getStore(), resourceExplorer.getSearchButtons().getFenixSearchParameters(), resourceExplorer.getCataloguePager(), from,
							to);
				}

			}
		};
	}

	public static SelectionListener<ButtonEvent> goFirstPage(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), resourceExplorer.getSearchButtons().getFenixSearchParameters(), resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());
			}
		};
	}

	public static SelectionListener<ButtonEvent> goToLastPage(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				resourceExplorer.getCataloguePager().getFenixPager().setActualPage(resourceExplorer.getCataloguePager().getFenixPager().calculateNumberOfPages());

				int from = resourceExplorer.getCataloguePager().getFenixPager().calculateIndexFrom();
				int to = resourceExplorer.getCataloguePager().getFenixPager().calculateIndexTo();
				CatalogueController.fillGridUpdate(resourceExplorer.getCatalogue().getGrid(), resourceExplorer.getCatalogue().getStore(), resourceExplorer.getSearchButtons().getFenixSearchParameters(), resourceExplorer.getCataloguePager(), from, to);

			}
		};
	}

}
