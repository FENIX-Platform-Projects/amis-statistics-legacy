package org.fao.fenix.web.modules.adam.client.control;

import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;


public class ADAMTableController extends ADAMController {
	
	public static void addTable(final String position, final String color, final ADAMQueryVO q, final long objID, final boolean addRemoveTableIcon, final SelectionListener<ButtonEvent> removeListener, final String removeLabel) {		
		
		//addTable(position, color, q, objID);
		
		RootPanel.get(position).setStyleName("small-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				
				if ( !q.getSwitchResources().isEmpty()) {
					vo.setSwitchResources(q.getSwitchResources());
				}
				
				
				//VerticalPanel panel = ADAMTableMaker.buildSmallTable(vo, position, color, fullScreenTable(position, color, q.getTitle()), addRemoveTableIcon, removeListener, removeLabel);
				VerticalPanel panel = ADAMTableMaker.buildBigTable(vo);
				
				System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position);
				
				tableMap.put(position, vo);
				currentUsedObjects.put(position, ADAMBoxContent.TABLE);
				
				
				if ( objID == objectWindowId ) {
					System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position + " ADDED ");
//					tableMap.put(position, vo);
//					currentUsedObjects.put(position, ADAMBoxContent.TABLE);
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					RootPanel.get(position).setStyleName("big-box content");
					RootPanel.get(position).add(panel);
				}
				
			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	public static void addTable(final String position, final String color, final ADAMQueryVO q, final long objID) {
		RootPanel.get(position).setStyleName("small-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				
				if ( !q.getSwitchResources().isEmpty()) {
					vo.setSwitchResources(q.getSwitchResources());
				}
				
				
				//VerticalPanel panel = ADAMTableMaker.buildSmallTable(vo, position, color, fullScreenTable(position, color, q.getTitle()), false, null, null);
				VerticalPanel panel = ADAMTableMaker.buildBigTable(vo);
				System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position);
				
				tableMap.put(position, vo);
				currentUsedObjects.put(position, ADAMBoxContent.TABLE);
				
				
				if ( objID == objectWindowId ) {
					System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position + " ADDED ");
//					tableMap.put(position, vo);
//					currentUsedObjects.put(position, ADAMBoxContent.TABLE);
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					RootPanel.get(position).setStyleName("big-box content");
					RootPanel.get(position).add(panel);
				}
				
			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	
	public static void addBigTable(final String position, final String color, final ADAMQueryVO q, final long objID) {
		RootPanel.get(position).setStyleName("small-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				if ( !q.getSwitchResources().isEmpty()) {
					vo.setSwitchResources(q.getSwitchResources());
				}
				
				
				
				VerticalPanel panel = ADAMTableMaker.buildBigTable(vo, position, color);
				
				
				System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position);
				
				tableMap.put(position, vo);
				currentUsedObjects.put(position, ADAMBoxContent.TABLE);
				
				
				if ( objID == objectWindowId ) {
					System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position + " ADDED ");
//					tableMap.put(position, vo);
//					currentUsedObjects.put(position, ADAMBoxContent.TABLE);
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					RootPanel.get(position).setStyleName("small-box content");
					RootPanel.get(position).add(panel);
				}
			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	public static SelectionListener<ButtonEvent> fullScreenTable(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeCenter();
//				ADAMController.removeAllContents(false);
//				objectWindowId = objectWindow.getNext();
				ADAMVisibilityController.restoreAdamViewVisibility();
//				ADAMController.removeBoxes();
				ADAMResultVO vo = tableMap.get(position);
				RootPanel.get("CENTER").setStyleName("big-box border  content");
				RootPanel.get("CENTER").add(ADAMTableMaker.buildBigTable(vo, position, color, smallScreenTable(position, color, title), false));
			}
		};
	}
	
	/*public static SelectionListener<ButtonEvent> fullScreenQuestionTable(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("fullScreenQuestionTable");
				ADAMController.removeAllContents(false);
				
//				ADAMController.removeBoxes();
				ADAMResultVO vo = questionsMap.get(position);
				
				System.out.println("vo: " + vo.getTitle() + " | " + vo);
				RootPanel.get("CENTER").setStyleName("big-box border  content");
				RootPanel.get("CENTER").add(ADAMTableMaker.buildBigTable(vo, position, color, smallScreenQuestionTable(position, color, title), false));
			}
		};
	}*/
	
//	public static SelectionListener<ButtonEvent> fullScreenFavouritePurposesTable(final String position, final String color, final String title) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				ADAMController.removeQuestions();
//				ADAMResultVO vo = questionsMap.get(position);
//				RootPanel.get("CENTER").setStyleName("big-" + color + "-box border  content");
//				RootPanel.get("CENTER").add(ADAMTableMaker.buildBigFavouritePurposesTable(vo, smallScreenFavouritePurposesTable(position, color, title)));
//			}
//		};
//	}
	
	public static SelectionListener<ButtonEvent> smallScreenTable(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMController.removeCenter();
//				ADAMController.removeBoxes();
//				ADAMVisibilityController.restoreAdamViewVisibility();
//				RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//				ADAMController.restoreBoxesContent(title);
			}
		};
	}
	
/*	public static SelectionListener<ButtonEvent> smallScreenQuestionTable(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMController.removeAllContents(false);
				RootPanel.get(position).setStyleName("small-box content");
				ADAMController.restoreQuestionsContent();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> smallScreenFavouritePurposesTable(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMController.removeBoxes();
				ADAMVisibilityController.restoreAdamViewVisibility();
				RootPanel.get(position).setStyleName("question-" + color + "-box content");
				ADAMController.restoreQuestionsContent();
			}
		};
	}*/
	
//	public static SelectionListener<ButtonEvent> fullScreenTopSectorsDonorViewTable(final String position, final String color, final String title) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				ADAMController.removeBoxes();
//				ADAMResultVO vo = tableMap.get(position);
//				RootPanel.get("CENTER").setStyleName("big-" + color + "-box border  content");
//				RootPanel.get("CENTER").add(ADAMTableMaker.buildBigTopSectorsDonorViewTable(vo, smallScreenTable(position, color, title)));
//			}
//		};
//	}
	
//	public static void addTopSectorsDonorViewTable(final String position, final String color, final ADAMQueryVO q) {
//		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
//			public void onSuccess(ADAMResultVO vo) {
//				tableMap.put(position, vo);
//				if (RootPanel.get(position).getWidgetCount() > 0)
//					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//				RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//				RootPanel.get(position).add(ADAMTableMaker.buildSmallTopSectorsDonorViewTable(vo, fullScreenTopSectorsDonorViewTable(position, color, q.getTitle())));
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
	
//	public static void addTopAgriculturalSectorsDonorViewTable(final String position, final String color, final ADAMQueryVO q) {
//		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
//			public void onSuccess(ADAMResultVO vo) {
//				tableMap.put(position, vo);
//				if (RootPanel.get(position).getWidgetCount() > 0)
//					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//				RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//				RootPanel.get(position).add(ADAMTableMaker.buildSmallTopSectorsDonorViewTable(vo, fullScreenTopSectorsDonorViewTable(position, color, q.getTitle())));
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
	
}
