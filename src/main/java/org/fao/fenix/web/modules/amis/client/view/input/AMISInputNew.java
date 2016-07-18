package org.fao.fenix.web.modules.amis.client.view.input;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;


public class AMISInputNew extends LayoutContainer{

    ContentPanel panel;

    AMISMainMenu mainMenu;

    //To check if the tool is open ....
    public static boolean openedCbsTool;


    public static String action = new String();


    // New AMISInput Constructor
    // AMISInput is no longer passed to AMISLoginRegisterPanel, as now the AMISLoginRegisterPanel and log in handling
    // is at a more higher level
    // i.e. The same login panel is accessed in AMISInput as well as the external 'Log In' link
    public AMISInputNew(AMISMainMenu mainMenu) {
        System.out.println(" Amisinput Constructor AMISController.currentDatasetView = *"+ AMISController.currentDatasetView+ "*");
        panel = new ContentPanel();
        panel.setBodyBorder(false);
        panel.setBorders(false);
        panel.setHeaderVisible(false);
        this.mainMenu = mainMenu;
        openedCbsTool = false;

        System.out.println("Class: AmisInput Function: AMISInput(AMISMainMenu mainMenu)  FenixUser.hasAdminRole() "+FenixUser.hasAdminRole());
    }


    public ContentPanel buildInputUpload() {

        panel.removeAll();
        panel.add(buildInputButtons());

        return panel;
    }

    private void buildMainPanelCCbsTool(){

        AMISServiceEntry.getInstance().getCBSInputToolUrl(new AsyncCallback<String>() {

            @Override
            public void onSuccess(String url) {
                String iframe = "<iframe src=\"" + url + "\" marginwidth=\"75px\" frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 1500px;\">Your browser does not support iframes.</iframe>";
                System.out.println(" iframe " + iframe);
                Html html = new Html(iframe);
                openedCbsTool = true;
                if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                    RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));

                html.setStyleAttribute("background", "#FFFFFF");

                RootPanel.get("MAIN_CONTENT").add(html);
            }

            @Override
            public void onFailure(Throwable arg0) {
                System.out.println("Cbs get url Exception!!!!");
            }
        });
    }

    public ContentPanel buildInputButtons(){

        ContentPanel p = new ContentPanel();
        p.setBodyBorder(false);
        p.setBorders(false);
        p.setHeaderVisible(false);
        p.setHeight("500px");
        String introText = "The Commodity Balance Sheet (CBS) data management tool allows authorized AMIS users to enter and manage national databases in accordance with AMIS standards.";
        p.add(buildInputPanel("INPUT DATA ONLINE", introText));
        p.add(buildInputPanel_cbsTool("Open the CBS Input Tool", "CBS_TOOL"));

        return p;

    }

    public VerticalPanel buildInputPanel(String title, String introduction){

        VerticalPanel p = new VerticalPanel();
        p.setSpacing(10);
        p.add(buildHeader(title));
        Html intro = new Html("<div class='input_panel_text'>"+introduction+"</div>");
        p.add(intro);
        return p;
    }


    //This function is used by the cbs input tool
    public VerticalPanel buildInputPanel_cbsTool(String onClickString, String id){
        VerticalPanel p = new VerticalPanel();
        p.setSpacing(10);
        HorizontalPanel hpGo = buildGo(onClickString, id);
        p.add(hpGo);
        return p;
    }

    public ContentPanel getPanel() {
        return panel;
    }

    public void setPanel(ContentPanel panel) {
        this.panel = panel;
    }


    private HorizontalPanel buildHeader(String title) {
        HorizontalPanel p = new HorizontalPanel();
        Html header = new Html(title+" <img src='amis-images/import_data_arrows.png'>");
        header.addStyleName("input_panel_header");
        p.add(header);
        p.add(FormattingUtils.addHSpace(10));

        return p;
    }

    private HorizontalPanel buildGo(String title, String id) {
        HorizontalPanel p = new HorizontalPanel();
        p.addStyleName("input_panel_go");
        ClickHtml go = new ClickHtml();
        go.setHtml("<img src='amis-images/start_arrows.png'> "+title);
        go.addStyleName("input_panel_go");
        go.addListener(Events.OnClick, openInput(id));
        p.add(go);
        p.add(FormattingUtils.addHSpace(10));

        return p;
    }


    private Listener<ComponentEvent> openInput(final String action) {
        return new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent ce) {
                if(action.equals("CBS_TOOL"))
                {
                    System.out.println(" in CBS_TOOL");
                    buildMainPanelCCbsTool();
                }
            }
        };
    }


    public String getUsername() {
        return CCBS.USERNAME;
    }

    public void setUsername(String username) {
        CCBS.USERNAME = username;
    }

}