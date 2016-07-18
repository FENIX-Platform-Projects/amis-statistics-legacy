package org.fao.fenix.web.modules.core.client.utils;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;


/*
 * This class extends EXT GWT ListStore class, the returned property field values are now
 * filtered based upon whether the property field value "contains" the entered 'user' text,
 * as opposed just "starting with" the entered text.
 *
 * e.g If the user types "area", the List Store will now be filtered to contain all the following items:
 * "Area Harvested", "Agricultural Area", "Arable Land Area"
 * as opposed to just "Area Harvested".
 */

public class CustomListStore<M extends ModelData> extends ListStore<M> {


	 protected String filterBegins;

	/**
	   * Creates a new store.
	   */
	  public CustomListStore() {

	  }

	  /**
	   * Creates a new store.
	   *
	   * @param loader the loader instance
	   */
	  @SuppressWarnings("unchecked")
	  public CustomListStore(ListLoader loader) {
	     this.loader = loader;

	    loader.addLoadListener(new LoadListener() {

	      public void loaderBeforeLoad(LoadEvent le) {
	        onBeforeLoad(le);
	      }

	      public void loaderLoad(LoadEvent le) {
	        onLoad(le);
	      }

	      public void loaderLoadException(LoadEvent le) {
	        onLoadException(le);
	      }

	    });
	  }


    /**
     * Applies the current filters to the store.
     *
     * @param property
     *            the optional active property
     */
    @Override
     public void applyFilters(String property) {
        filterProperty = property;
        if (!filtersEnabled) {
            snapshot = all;
        }

        filtersEnabled = true;
        filtered = new ArrayList<M>();
        
        //mackx
        String filterBeginsWith=null;
        
        
        System.out.println("CustomListStore: filterBeginsWith "+filterBeginsWith);
        
        for (M items : snapshot) {
           if ((filterBeginsWith != null) && (property != null)) {
                Object o = items.get(property);
                
                
                System.out.println("CustomListStore: o "+o.toString());
                if (o != null) {
                    if (!o.toString().toLowerCase().contains(filterBeginsWith.toLowerCase())) {
                        continue;
                    }
                }
            }
            if (!isFiltered(items, property)) {
                filtered.add(items);
            }
        }
        all = filtered;

        if (storeSorter != null) {
            applySort(false);
        }

        fireEvent(Filter, createStoreEvent());
    }
}