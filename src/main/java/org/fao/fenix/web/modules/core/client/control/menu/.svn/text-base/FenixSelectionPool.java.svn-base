package org.fao.fenix.web.modules.core.client.control.menu;

import  org.fao.fenix.web.modules.core.client.Fenix;
import java.util.List;

import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.aquastat.common.services.AquastatServiceEntry;
import org.fao.fenix.web.modules.bangladesh.client.view.BangladeshWindow;
import org.fao.fenix.web.modules.birt.client.countrybrief.CountryBriefController;
import org.fao.fenix.web.modules.birt.client.view.ISFP.ISFPController;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.client.view.cropcalendar.CropCalendarController;
import org.fao.fenix.web.modules.birt.client.view.report.crisis.CrisisView;
import org.fao.fenix.web.modules.birt.client.view.report.giewsMap.GiewsMaps;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.ccbs.client.view.CCBSWindow;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.common.services.ChartDesignerServiceEntry;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorWindow;
import org.fao.fenix.web.modules.coding.client.view.hierachy.CodingHierarchySelection;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchWindow;
import org.fao.fenix.web.modules.coding.common.services.CodingServiceEntry;
import org.fao.fenix.web.modules.codinghierarchyupload.client.view.CodingHierarchyUploaderWindow;
import org.fao.fenix.web.modules.codingmappingupload.client.view.CodingMappingUploaderWindow;
import org.fao.fenix.web.modules.communication.client.view.CommunicationModuleWindow;
import org.fao.fenix.web.modules.communication.client.view.CommunicationParameterWindow;
import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.console.client.view.ConsoleWindow;
import org.fao.fenix.web.modules.core.client.banner.BannerChangeWindow;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.security.LoginController;
import org.fao.fenix.web.modules.core.client.security.LoginView;
import org.fao.fenix.web.modules.core.client.security.UserPropertiesWindow;
import org.fao.fenix.web.modules.core.client.security.groupmanager.GroupManagerController;
import org.fao.fenix.web.modules.core.client.security.permissionmanager.PermissionManagerController;
import org.fao.fenix.web.modules.core.client.security.usermanager.UserManagerController;
import org.fao.fenix.web.modules.core.client.toolbox.FenixToolbox;
import org.fao.fenix.web.modules.core.client.utils.About;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.datasetupload.client.view.DatasetUploaderWindow;
import org.fao.fenix.web.modules.designer.client.view.DesignerWindow;
import org.fao.fenix.web.modules.ec.client.view.ECWindow;
import org.fao.fenix.web.modules.ec.common.services.ECServiceEntry;
import org.fao.fenix.web.modules.edi.client.view.faostat.FAOStatWindow;
import org.fao.fenix.web.modules.edi.client.view.fewsnet.FEWSNETWindow;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;
import org.fao.fenix.web.modules.esoko.client.view.EsokoWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListImporterWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ExcelImporterWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterWindow;
import org.fao.fenix.web.modules.fieldclimate.client.view.FieldClimateWindow;
import org.fao.fenix.web.modules.fpi.client.view.index.FPIIndexWindow;
import org.fao.fenix.web.modules.geotiffupload.client.view.GeotiffUploader;
import org.fao.fenix.web.modules.geotiffupload.client.view.SLDUploader;
import org.fao.fenix.web.modules.giews.client.view.GIEWSWindow;
import org.fao.fenix.web.modules.ipc.client.view.IPCWorkflowWindow;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCReport;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.old.ShowIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.reports.IPCReportsWindow;
import org.fao.fenix.web.modules.ipc.client.view.viewer.IPCViewerWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.latex.client.view.LatexWindow;
import org.fao.fenix.web.modules.map.client.control.action.CreateMap;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.CodeListMetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcChartWizard;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.pm.client.view.ProjectManager;
import org.fao.fenix.web.modules.qb.client.view.QueryBuilder;
import org.fao.fenix.web.modules.rainfall.client.view.RainfallWindow;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.codingsystem.ResourceExplorerCodingSystem;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.image.ResourceExplorerImage;
import org.fao.fenix.web.modules.re.client.view.latexreport.ResourceExplorerLatexReport;
import org.fao.fenix.web.modules.re.client.view.map.ResourceExplorerMap;
import org.fao.fenix.web.modules.re.client.view.olap.ResourceExplorerOlap;
import org.fao.fenix.web.modules.re.client.view.project.ResourceExplorerProject;
import org.fao.fenix.web.modules.re.client.view.search.ResourceExplorerSearch;
import org.fao.fenix.web.modules.re.client.view.tableview.ResourceExplorerTableView;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;
import org.fao.fenix.web.modules.re.client.view.textgroup.ResourceExplorerTextGroup;
import org.fao.fenix.web.modules.shapefileupload.client.view.ShapeFileUploader;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorWindow;
import org.fao.fenix.web.modules.statistics.client.view.StatisticsWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;
import org.fao.fenix.web.modules.welcome.client.view.SplashWindow;
import org.fao.fenix.web.modules.x.client.view.XExplorerWindow;
import org.fao.fenix.web.modules.x.client.view.XImporterWindow;
import org.fao.fenix.web.modules.x.common.services.XServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class FenixSelectionPool {

	public static SelectionListener<MenuEvent> getSelectionListener(final String commandName) {
		Runnable runnable = getRunnable(commandName);
		if (runnable == null)
			runnable = getUndefRunnable(commandName);
		ListenerEmbedder<MenuEvent> embedder = new ListenerEmbedder<MenuEvent>();
		return embedder.embed(runnable);
	}
	
	public static SelectionListener<ButtonEvent> getSelectionListenerForButtons(final String commandName) {
		Runnable runnable = getRunnable(commandName);
		if (runnable == null)
			runnable = getUndefRunnable(commandName);
		ListenerEmbedder<ButtonEvent> embedder = new ListenerEmbedder<ButtonEvent>();
		return embedder.embed(runnable);
	}

	public static SelectionListener<ButtonEvent> getButtonSelectionListener(final String commandName) {
		Runnable runnable = getRunnable(commandName);
		if (runnable == null)
			runnable = getUndefRunnable(commandName);
		ListenerEmbedder<ButtonEvent> embedder = new ListenerEmbedder<ButtonEvent>();
		return embedder.embed(runnable);
	}

	static class ListenerEmbedder<E extends ComponentEvent> {
		public SelectionListener<E> embed(final Runnable runnable) {
			return new SelectionListener<E>() {
				public void componentSelected(E ce) {
					runnable.run();
				}
			};
		}
	}

	public static Runnable getUndefRunnable(final String command) {
		return new Runnable() {
			public void run() {
				FenixAlert.info("USER INFO", "No command bound to key '{0}'", command);
			}
		};
	}

	public static Runnable getRunnable(final String commandName) {

		/* ***************************************************************************************** */
		/* 
		 * **************************************** DEFAULT
		 * ****************************************
		 */
		/* ***************************************************************************************** */

		Runnable runnable = null;

		// System.out.println("commandName: " + commandName);

		/* ************************************************************************************* */
		/* 
		 * **************************************** NEW
		 * ****************************************
		 */
		/* ************************************************************************************* */
		if (commandName.equals("updateChartTest"))
			runnable = updateChartTest();
		if (commandName.equals("newProject"))
			runnable = newProject();
		else if (commandName.equals("newMap"))
			runnable = newMap();
		else if (commandName.equals("textEditor"))
			runnable = newText();
		else if (commandName.equals("chartWizard"))
			runnable = chartWizard();
		else if (commandName.equals("tableWizard"))
			runnable = tableWizard();
		else if (commandName.equals("reportWizard"))
			runnable = reportWizard();
		else if (commandName.equals("uploadGWTDataset"))
			runnable = uploadGWTDataset();
		else if (commandName.equals("newTextGroup"))
			runnable = newTextGroup();
		else if (commandName.equals("newOlap"))
			runnable = olap();
		else if (commandName.equals("latex"))
			runnable = latex();
		else if (commandName.equals("newImage"))
			runnable = newImage();

		/* ************************************************************************************** */
		/* 
		 * **************************************** OPEN
		 * ****************************************
		 */
		/* ************************************************************************************** */

		else if (commandName.equals("openCodingSystem"))
			runnable = openResource("codingSystem");
		else if (commandName.equals("openChart"))
			runnable = openResource("chart");
		else if (commandName.equals("openDataset"))
			runnable = openResource("dataset");
		else if (commandName.equals("openMap"))
			runnable = openResource("map");
		else if (commandName.equals("openProject"))
			runnable = openResource("project");
		else if (commandName.equals("openTable"))
			runnable = openResource("table");
		else if (commandName.equals("openText"))
			runnable = openResource("text");
		else if (commandName.equals("openTextGroup"))
			runnable = openResource("textGroup");
		else if (commandName.equals("openReport"))
			runnable = openResource("report");
		else if (commandName.equals("openOlap"))
			runnable = openResource("olap");
		else if (commandName.equals("openImage"))
			runnable = openResource("image");

		/* ************************************************************************************** */
		/* 
		 * **************************************** VIEW
		 * ****************************************
		 */
		/* ************************************************************************************** */

		else if (commandName.equals("viewProjectManager"))
			runnable = viewProjectManager();
		else if (commandName.equals("openAll"))
			runnable = openResource("all");
		else if (commandName.equals("viewToolbox"))
			runnable = viewToolbox();
		else if (commandName.equals("welcome"))
			runnable = welcome();

		/* *************************************************************************************** */
		/* 
		 * **************************************** TOOLS
		 * ****************************************
		 */
		/* *************************************************************************************** */

		else if (commandName.equals("communicationModule"))
			runnable = communicationModule();
		else if (commandName.equals("fpiIndex"))
			runnable = fpiIndex();

		else if (commandName.equals("createIPCWorkflow"))
			runnable = createIPCWorkflow();
		else if (commandName.equals("showIPCWorkflows"))
			runnable = showIPCWorkflows();
		else if (commandName.equals("createIPCReport"))
			runnable = createIPCReport();
		else if (commandName.equals("IPCWorkflowWindow"))
			runnable = IPCWorkflowWindow();
		else if (commandName.equals("IPCReports"))
			runnable = IPCReports();
		else if (commandName.equals("esoko"))
			runnable = esoko();
		else if (commandName.equals("fieldClimate"))
			runnable = fieldClimate();
		else if (commandName.equals("statisticalTool"))
			runnable = statisticalTool();

		else if (commandName.equals("ccbsTool"))
			runnable = ccbsTool();

		else if (commandName.equals("ISFP"))
			runnable = ISFP();
		else if (commandName.equals("countryBriefs"))
			runnable = countryBriefs();
		else if (commandName.equals("cropCalendars"))
			runnable = cropCalendars();

		else if (commandName.equals("showUnfavourableTable"))
			runnable = showUnfavourableTable();
		else if (commandName.equals("showCrisisTable"))
			runnable = showCrisisTable();
		else if (commandName.equals("showReport"))
			runnable = showReport();

		else if (commandName.equals("codingFindConvert"))
			runnable = codingFindConvert();
		else if (commandName.equals("codingCreator"))
			runnable = codingCreator();

		else if (commandName.equals("rainfall"))
			runnable = rainfall();
		else if (commandName.equals("olap"))
			runnable = olap();
		else if (commandName.equals("codingHierarchy"))
			runnable = codingHierarchy();

		else if (commandName.equals("uploadImage"))
			runnable = uploadImage();
		else if (commandName.equals("ofcgwtTest"))
			runnable = ofcgwtTest();
		else if (commandName.equals("ipcxmlTest"))
			runnable = ipcxmlTest();
		else if (commandName.equals("ecReports"))
			runnable = ecReports();
		else if (commandName.equals("ecHaitiReports"))
			runnable = ecHaitiReports();
		else if (commandName.equals("ecNews"))
			runnable = ecNews();

		else if (commandName.equals("aquastat"))
			runnable = aquastat();

		else if (commandName.equals("queryBuilder"))
			runnable = queryBuilder();

		else if (commandName.equals("excelImporter"))
			runnable = excelImporter();
		else if (commandName.equals("designer"))
			runnable = designer();
		else if (commandName.equals("chartDesigner"))
			runnable = chartDesigner();	
		else if (commandName.equals("tinyMCEReport")) 
			runnable = tinyMCEReport();	
		else if (commandName.equals("sldEditorShapefile"))
			runnable = sldEditorShapefile();
		else if (commandName.equals("sldEditorRaster"))
			runnable = sldEditorRaster();
		else if (commandName.equals("sldEditorImportSld"))
			runnable = sldEditorImportSld();
		else if (commandName.equals("faostatImporter"))
			runnable = faostatImporter();
		else if (commandName.equals("fewsnetImporter"))
			runnable = fewsnetImporter();

		else if (commandName.equals("bangladeshReport"))
			runnable = bangladeshReport();
		
		/* ************************************************************************************************* */
		/* 
		 * **************************************** DATA MANAGEMENT
		 * ****************************************
		 */
		/* ************************************************************************************************* */

		else if (commandName.equals("uploadGWTDataset"))
			runnable = uploadGWTDataset();
		else if (commandName.equals("xImporter"))
			runnable = xImporter();
		else if (commandName.equals("codingUploader"))
			runnable = codingUploader();
		else if (commandName.equals("codingMappingUploader"))
			runnable = codingMappingUploader();
		else if (commandName.equals("codingHierarchyUploader"))
			runnable = codingHierarchyUploader();
		else if (commandName.equals("uploadGWTShapeFile"))
			runnable = uploadGWTShapeFile();
		else if (commandName.equals("uploadGWTGeoTiff"))
			runnable = uploadGWTGeoTiff();
		else if (commandName.equals("uploadGWTSLD"))
			runnable = uploadGWTSLD();
		else if (commandName.equals("runGeotiffHarvester"))
			runnable = runGeotiffHarvester();
		else if (commandName.equals("metadataEditor"))
			runnable = metadataEditor();
		else if (commandName.equals("codingSystemMetadataEditor"))
			runnable = codingSystemMetadataEditor();

		/* ************************************************************************************************ */
		/* 
		 * **************************************** ADMINISTRATION
		 * ****************************************
		 */
		/* ************************************************************************************************ */

		else if (commandName.equals("userManager"))
			runnable = userManager();
		else if (commandName.equals("groupManager"))
			runnable = groupManager();
		else if (commandName.equals("permissionManager"))
			runnable = permissionManager();
		else if (commandName.equals("userProperties"))
			runnable = userProperties();

		else if (commandName.equals("communicationModuleUrl"))
			runnable = communicationModuleUrl();
		else if (commandName.equals("communicationModuleSettings"))
			runnable = communicationModuleSettings();

		else if (commandName.equals("runLayersImporter"))
			runnable = runLayersImporter();
		else if (commandName.equals("runGeotiffHarvester"))
			runnable = runGeotiffHarvester();
		else if (commandName.equals("giewsMaps"))
			runnable = giewsMaps();
		else if (commandName.equals("fsatmis"))
			runnable = fsatmis();
		else if (commandName.equals("giewsCountryProjects"))
			runnable = giewsCountryProjects();
		else if (commandName.equals("runGSSynch"))
			runnable = runGSSynch();

		else if (commandName.equals("backup"))
			runnable = backup();
		else if (commandName.equals("restore"))
			runnable = restore();
		else if (commandName.equals("console"))
			runnable = console();
		else if (commandName.equals("createGhostTables"))
			runnable = createGhostTables();
		else if (commandName.equals("importDefaultCodeLists"))
			runnable = importDefaultCodeLists();
		else if (commandName.equals("chartBooleanFixer"))
			runnable = chartBooleanFixer();
		else if (commandName.equals("harvestGiewsCharts"))
			runnable = harvestGiewsCharts();
		else if (commandName.equals("changeBanner"))
			runnable = changeBanner();
		else if (commandName.equals("recreateCharts"))
			runnable = recreateCharts();

		/* ************************************************************************************* */
		/* 
		 * **************************************** RSS
		 * ****************************************
		 */
		/* ************************************************************************************* */

		else if (commandName.equals("createDatasetRSS"))
			runnable = createDatasetRSS();
		else if (commandName.equals("createDatasetRSSWithUniqueValues"))
			runnable = createDatasetRSSWithUniqueValues();
		else if (commandName.equals("createTextRSS"))
			runnable = createTextRSS();
		else if (commandName.equals("createRasterRSS"))
			runnable = createRasterRSS();
		else if (commandName.equals("createVectorRSS"))
			runnable = createVectorRSS();
		else if (commandName.equals("xExplorerWindow"))
			runnable = xExplorerWindow();
		else if (commandName.equals("exportShapefile"))
			runnable = exportShapefile();
		else if (commandName.equals("email"))
			runnable = email();

		/* *************************************************************************************** */
		/* 
		 * **************************************** HELP!
		 * ****************************************
		 */
		/* *************************************************************************************** */

		else if (commandName.equals("openGettingStarted"))
			runnable = openGettingStarted();
		else if (commandName.equals("openHelpOnline"))
			runnable = openHelpOnline();
		else if (commandName.equals("openForum"))
			runnable = openForum();
		else if (commandName.equals("openWiki"))
			runnable = openWiki();
		else if (commandName.equals("openPortal"))
			runnable = openPortal();

		/* ****************************************************************************************** */
		/* 
		 * **************************************** LANGUAGE
		 * ****************************************
		 */
		/* ****************************************************************************************** */

		else if (commandName.equals("english"))
			runnable = changeLanguage(BabelFish.print().english());
		else if (commandName.equals("french"))
			runnable = changeLanguage(BabelFish.print().french());
		else if (commandName.equals("spanish"))
			runnable = changeLanguage(BabelFish.print().spanish());
		else if (commandName.equals("chinese"))
			runnable = changeLanguage(BabelFish.print().chinese());
		else if (commandName.equals("russian"))
			runnable = changeLanguage(BabelFish.print().russian());
		else if (commandName.equals("bengali"))
			runnable = changeLanguage(BabelFish.print().bengali());
		else if (commandName.equals("armenian"))
			runnable = changeLanguage(BabelFish.print().armenian());
		else if (commandName.equals("arabic"))
			runnable = changeLanguage(BabelFish.print().arabic());
		else if (commandName.equals("maori"))
			runnable = changeLanguage(BabelFish.print().maori());

		/* *************************************************************************************** */
		/* 
		 * **************************************** LOGIN
		 * ****************************************
		 */
		/* *************************************************************************************** */

		else if (commandName.equals("about"))
			runnable = about();

		else if (commandName.equals("login")) {
			runnable = login();
		} else if (commandName.equals("logout")) {
			runnable = logout();
		}

		/* *************************************************************************************** */
		/* 
		 * **************************************** ADAM Admin
		 * ****************************************
		 */
		/* *************************************************************************************** */

		else if (commandName.equals("createADAMDonorMatrixViewsandResource"))
			runnable = createADAMDonorMatrixViewsandResource();

		return runnable;
	}

	private static Runnable login() {
		return new Runnable() {
			public void run() {
				loginAction();
			}
		};
	}

	public static void loginAction() {
		LoginView v = new LoginView();
		v.build();
		v.getLoginButton().addSelectionListener(LoginController.loginListener(v));
		v.getPasswordTextField().addKeyListener(LoginController.loginEnterKeyListener(v));
		v.show();
		// HorizontalPanel menuPanel =
		// (HorizontalPanel)RootPanel.get("menu").getWidget(0);
		LayoutContainer menuPanel = (LayoutContainer) RootPanel.get("topMenu").getWidget(0);

		ToolBar secondaryToolbar = (ToolBar) menuPanel.getData("secondaryToolbar");
		Button loginButton = (Button) secondaryToolbar.getData("login");
		loginButton.setText(BabelFish.print().getString("logout"));
		loginButton.setIconStyle("logout");
		loginButton.removeAllListeners();
		loginButton.addSelectionListener(logoutListener());
	}

	private static SelectionListener<ButtonEvent> loginListener() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				loginAction();
			}
		};
	}

	private static SelectionListener<ButtonEvent> logoutListener() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				logoutAction();
			}
		};
	}

	private static Runnable logout() {
		return new Runnable() {
			public void run() {
				logoutAction();
			}
		};
	}

	private static void logoutAction() {
		RootPanel.get("loginMessage").clear();
		// HorizontalPanel menuPanel =
		// (HorizontalPanel)RootPanel.get("menu").getWidget(0);
		// ToolBar secondaryToolbar =
		// (ToolBar)menuPanel.getData("secondaryToolbar");
		// Button loginButton = (Button)secondaryToolbar.getData("login");
		// loginButton.setText(I18N.print().getString("login"));
		// loginButton.setIconStyle("key");
		// loginButton.removeAllListeners();
		// loginButton.addSelectionListener(loginListener());
		doLogout();
	}

	private static Runnable uploadImage() {
		return new Runnable() {
			public void run() {
//				new UploaderControllerImage();
				new ImageImporterWindow(false).build();
			}
		};
	}

	private static void doLogout() {
		UserServiceEntry.getInstance().logout(new AsyncCallback<String>() {
			public void onSuccess(String result) {
				FenixUser.giveAnonymousRole();
				Fenix.buildMenu(true, true);
			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
				RootPanel.get("loginMessage").clear();
			}
		});
	}

	private static void doLogin(ToolBar secondaryToolBar) {
		LoginController.getInstance();
		FenixAlert.info("INFO", "null? " + (secondaryToolBar == null));
		Button item = (Button) secondaryToolBar.getData("login");
		item.setText(BabelFish.print().getString("logout"));
		item.setIconStyle("logout");
		item.removeAllListeners();
		item.addSelectionListener(logout(secondaryToolBar));
	}

	private static SelectionListener<ButtonEvent> login(final ToolBar secondaryToolBar) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				doLogin(secondaryToolBar);
			}
		};
	}

	private static Runnable loginCE(final ToolBar secondaryToolBar) {
		return new Runnable() {
			public void run() {
				doLogin(secondaryToolBar);
			}
		};
	}

	private static SelectionListener<ButtonEvent> logout(final ToolBar secondaryToolBar) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				doLogout();
			}
		};
	}

	private static Runnable logoutCE(final ToolBar secondaryToolBar) {
		return new Runnable() {
			public void run() {
				doLogout();
			}
		};
	}

	private static Runnable openGettingStarted() {
		return new Runnable() {
			public void run() {
				Window.open("http://ldvapp07.fao.org:8030/downloads/WS_QuickStartGuide.pdf", "_blank", "");
			}
		};
	}

	private static Runnable openHelpOnline() {
		return new Runnable() {
			public void run() {
				Window.open("http://www.foodsec.org/workstation/64921/en/", "_blank", "");
			}
		};
	}

	private static Runnable openForum() {
		return new Runnable() {
			public void run() {
				// Window.open("http://ldvapp07.fao.org:8031/phpBB3/", "_blank",
				// "");
				Window.open("http://groups.google.com/group/gwug-global-workstation-users-group?pli=1", "_blank", "");
			}
		};
	}

	private static Runnable openWiki() {
		return new Runnable() {
			public void run() {
				Window.open("http://ldvapp07.fao.org:8031/FenixUserWiki/index.php/Main_Page", "_blank", "");
			}
		};
	}

	private static Runnable openPortal() {
		return new Runnable() {
			public void run() {
				Window.open("http://www.foodsec.org/workstation/en/", "_blank", "");
			}
		};
	}

	private static Runnable console() {
		return new Runnable() {
			public void run() {
				new ConsoleWindow().build();
			}
		};
	}

	private static Runnable runGSSynch() {
		return new Runnable() {
			public void run() {
				MapServiceEntry.getInstance().runGSSynch(new AsyncCallback<Void>() {
					public void onSuccess(Void _) {
						Info.display("Synch layers", "Layer synch'er run in background.");
					}

					public void onFailure(Throwable caught) {
						Info.display("Error", "Layer synch'er could not be run:<BR>{0}", caught.getLocalizedMessage());
					}
				});
			}
		};
	}

	private static Runnable createGhostTables() {
		return new Runnable() {
			public void run() {
				final LoadingWindow loading = new LoadingWindow();
				loading.create("Creating ghost tables  ... ");
				TableServiceEntry.getInstance().createGhostTables(new AsyncCallback<Void>() {

					public void onSuccess(Void _) {
						loading.destroy();
					}

					public void onFailure(Throwable caught) {
						Info.display("Error", "createGhostTables", caught.getLocalizedMessage());
						loading.destroy();
					}
				});
			}
		};
	}

	private static Runnable giewsCountryProjects() {
		return new Runnable() {
			public void run() {
				GIEWSWindow giews = new GIEWSWindow();
				giews.build();
			}
		};
	}

	private static Runnable giewsMaps() {
		return new Runnable() {
			public void run() {
				new GiewsMaps();
			}
		};
	}

	private static Runnable fsatmis() {
		return new Runnable() {
			public void run() {

				final LoadingWindow loading = new LoadingWindow();
				loading.create("Loading fs-atmis report ... ");

				BirtServiceEntry.getInstance().getFsatmisTemplate("1", new AsyncCallback<String>() {

					public void onSuccess(String result) {
						loading.destroy();

						// -------------- temporary behavior
						com.extjs.gxt.ui.client.widget.Window window = new com.extjs.gxt.ui.client.widget.Window();
						window.setHeading("FS-ATMIS Report");
						ContentPanel cont = new ContentPanel();
						cont.setHeaderVisible(false);
						cont.setLayout(new FitLayout());
						window.setLayout(new FitLayout());
						window.setSize(800, 600);
						HTML content = new HTML(result);
						cont.add(content);
						window.add(cont);
						window.show();
						// ---------------------

					}

					public void onFailure(Throwable e) {
						loading.destroy();
						FenixAlert.alert("getFsatmisTemplate", e.getMessage());
					}

				});
			}
		};
	}

	private static Runnable backup() {
		return new Runnable() {
			public void run() {
				final LoadingWindow loading = new LoadingWindow();
				loading.create("Backup DB.");
				MetadataServiceEntry.getInstance().backup(new AsyncCallback() {
					public void onSuccess(Object result) {
						loading.destroy();
						FenixAlert.info("INFO", "Backup complete!");
					}

					public void onFailure(Throwable e) {
						loading.destroy();
						FenixAlert.alert("RPC Failed", e.getMessage());
					}
				});
			}
		};
	}

	private static Runnable restore() {
		return new Runnable() {
			public void run() {
				final LoadingWindow loading = new LoadingWindow();
				loading.create("Restore DB.");
				MetadataServiceEntry.getInstance().restore(new AsyncCallback() {
					public void onSuccess(Object result) {
						loading.destroy();
						FenixAlert.info("INFO", "Restore complete!");
					}

					public void onFailure(Throwable e) {
						loading.destroy();
						FenixAlert.error("RPC Failed", e.getMessage());
					}
				});
			}
		};
	}

	private static Runnable importDefaultCodeLists() {
		return new Runnable() {
			public void run() {
				final LoadingWindow loading = new LoadingWindow();
				loading.create("Importing Default Code Lists ....");
				CodingServiceEntry.getInstance().importDefaultCodeLists(new AsyncCallback() {
					public void onSuccess(Object result) {
						loading.destroy();
						FenixAlert.info("INFO", "Importing Default Code Lists complete!");
					}

					public void onFailure(Throwable e) {
						loading.destroy();
						FenixAlert.alert("RPC Failed", e.getMessage());
					}
				});
			}
		};
	}

	private static Runnable runLayersImporter() {
		return new Runnable() {
			public void run() {
				MapServiceEntry.getInstance().runLayerImporter(new AsyncCallback<Void>() {
					public void onSuccess(Void _) {
						Info.display("Import layers", "Layer importer run in background.");
					}

					public void onFailure(Throwable caught) {
						Info.display("Error", "Layer importer could not be run:<BR>{0}", caught.getLocalizedMessage());
					}
				});
			}
		};
	}

	private static Runnable runGeotiffHarvester() {
		return new Runnable() {
			public void run() {
				MapServiceEntry.getInstance().runGeotiffHarvester(new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean b) {
						if (b)
							Info.display("Geotiff Harvester", "Harvester run in background.");
						else
							Info.display("Warning", "Harvester could not be run.");
					}

					public void onFailure(Throwable caught) {
						Info.display("Error", "Harvester could not be run:<BR>{0}", caught.getLocalizedMessage());
					}
				});
			}
		};
	}

	private static Runnable communicationModuleUrl() {
		return new Runnable() {
			public void run() {
				try {
					CommunicationServiceEntry.getInstance().findServicesUrl(new AsyncCallback<String>() {
						public void onSuccess(String url) {
							FenixAlert.info(BabelFish.print().info(), url);
						}

						public void onFailure(Throwable caught) {
							FenixAlert.alert("RPC Failed", "showUrl @ CommunicationUrlWindow");
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	private static Runnable communicationModuleSettings() {
		return new Runnable() {
			public void run() {
				new CommunicationParameterWindow().build();
			}
		};
	}

	private static Runnable userManager() {
		return new Runnable() {
			public void run() {
				UserManagerController.getInstance();
			}
		};
	}

	private static Runnable groupManager() {
		return new Runnable() {
			public void run() {
				GroupManagerController.getInstance();
			}
		};
	}

	private static Runnable permissionManager() {
		return new Runnable() {
			public void run() {
				PermissionManagerController.getInstance();
			}
		};
	}

	private static Runnable userProperties() {
		return new Runnable() {
			public void run() {
				UserPropertiesWindow w = new UserPropertiesWindow();
				w.build();
			}
		};
	}

	private static Runnable codingHierarchyUploader() {
		return new Runnable() {
			public void run() {
				new CodingHierarchyUploaderWindow().build();
			}
		};
	}

	private static Runnable uploadGWTShapeFile() {
		return new Runnable() {
			public void run() {
				new ShapeFileUploader().build();
			}
		};
	}

	private static Runnable uploadGWTGeoTiff() {
		return new Runnable() {
			public void run() {
				new GeotiffUploader().build();
			}
		};
	}

	private static Runnable uploadGWTSLD() {
		return new Runnable() {
			public void run() {
				new SLDUploader().build();
			}
		};
	}

	private static Runnable metadataEditor() {
		return new Runnable() {
			public void run() {
				MetadataEditorWindow me = new MetadataEditorWindow();
				me.build(true, true, false);
				me.getTabPanel().getTabPanel().setSelection(me.getTabPanel().getGeneralInfo());
			}
		};
	}

	private static Runnable codingSystemMetadataEditor() {
		return new Runnable() {
			public void run() {
				CodeListMetadataEditorWindow clme = new CodeListMetadataEditorWindow();
				clme.build();
			}
		};
	}

	private static Runnable codingUploader() {
		return new Runnable() {
			public void run() {
				// new CodingUploaderWindow().build();
				new CodeListImporterWindow().build();
			}
		};
	}

	private static Runnable codingMappingUploader() {
		return new Runnable() {
			public void run() {
				new CodingMappingUploaderWindow().build();
			}
		};
	}

	private static Runnable rainfall() {
		return new Runnable() {
			public void run() {
				RainfallWindow rainfall = new RainfallWindow();
				rainfall.build();
			}
		};
	}

	private static Runnable olap() {
		return new Runnable() {
			public void run() {
				new MTWindow().build();
//				OlapWindow olap = new OlapWindow();
//				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
//					olap.build(true, true);
//				} else {
//					olap.build(true, false);
//				}
			}
		};
	}

	private static Runnable latex() {
		return new Runnable() {
			public void run() {
				LatexWindow w = new LatexWindow();
				w.build();
			}
		};
	}

	private static Runnable codingHierarchy() {
		return new Runnable() {
			public void run() {
				new CodingHierarchySelection().build();
			}
		};
	}

	private static Runnable codingFindConvert() {
		return new Runnable() {
			public void run() {
				new CodingSearchWindow().build();
			}
		};
	}

	private static Runnable codingCreator() {
		return new Runnable() {
			public void run() {
				new DcmtCodingCreatorWindow().build();
			}
		};
	}

	private static Runnable showUnfavourableTable() {
		return new Runnable() {
			public void run() {
				TableServiceEntry.getInstance().getUnfavourableProspectsDatasetID(new AsyncCallback<Long>() {
					public void onSuccess(Long result) {
						TableWindow window = new TableWindow();
						new TableWindow().buildLatestData(result, true, false, "Countries with unfavourable prospects");
					}

					public void onFailure(Throwable caught) {
					}
				});
			}
		};
	}

	private static Runnable showCrisisTable() {
		return new Runnable() {
			public void run() {
				TableServiceEntry.getInstance().getCountriesCrisisDatasetID(new AsyncCallback<Long>() {
					public void onSuccess(Long result) {
						new TableWindow().buildLatestData(result, true, false, "Countries in Crisis");
					}

					public void onFailure(Throwable caught) {
					}
				});
			}
		};
	}

	private static Runnable showReport() {
		return new Runnable() {
			public void run() {
				new CrisisView();
			}
		};
	}

	private static Runnable ISFP() {
		return new Runnable() {
			public void run() {
				new ISFPController();
			}
		};
	}

	private static Runnable countryBriefs() {
		return new Runnable() {
			public void run() {
				new CountryBriefController();
			}
		};
	}

	private static Runnable cropCalendars() {
		return new Runnable() {
			public void run() {
				new CropCalendarController();
			}
		};
	}

	private static Runnable createIPCWorkflow() {
		return new Runnable() {
			public void run() {
				// new CreateIPCWorkflowTab().build();
				new CreateIPCWorkflow().build();
			}
		};
	}

	private static Runnable IPCWorkflowWindow() {
		return new Runnable() {
			public void run() {
				new IPCWorkflowWindow().build();
			}
		};
	}

	private static Runnable IPCReports() {
		return new Runnable() {
			public void run() {
				new IPCReportsWindow().build();
			}
		};
	}

	private static Runnable showIPCWorkflows() {
		return new Runnable() {
			public void run() {
				new ShowIPCWorkflow().build();
			}
		};
	}

	private static Runnable createIPCReport() {
		return new Runnable() {
			public void run() {
				new CreateIPCReport().build();
			}
		};
	}

	private static Runnable fpiIndex() {
		return new Runnable() {
			public void run() {
				new FPIIndexWindow().build();
			}
		};
	}

	private static Runnable esoko() {
		return new Runnable() {
			public void run() {
				new EsokoWindow().build();
			}
		};
	}

	private static Runnable fieldClimate() {
		return new Runnable() {
			public void run() {
				new FieldClimateWindow().build();
			}
		};
	}
	
	private static Runnable statisticalTool() {
		return new Runnable() {
			public void run() {
				new StatisticsWindow().build();
			}
		};
	}

	private static Runnable ccbsTool() {
		return new Runnable() {
			public void run() {
				CCBSWindow w = new CCBSWindow();
				w.build();
				w.show();
			}
		};
	}

	private static Runnable communicationModule() {
		return new Runnable() {
			public void run() {
				CommunicationModuleWindow cmw = new CommunicationModuleWindow();
				cmw.build();
			}
		};
	}

	private static Runnable welcome() {
		return new Runnable() {
			public void run() {
//				new Welcome().build();
				new SplashWindow().build();
			}
		};
	}

	private static Runnable viewToolbox() {
		return new Runnable() {
			public void run() {
				if (!FenixToolbox.toolboxIsopen)
					new FenixToolbox();
			}
		};
	}

	private static Runnable viewProjectManager() {
		return new Runnable() {
			public void run() {
				if (!PMModel.isProjectManagerOpen() || PMModel.getProjectManager() == null)
					new ProjectManager().build();
				else
					PMModel.getProjectManager().show();
			}
		};
	}

	private static Runnable openResource(final String resourceType) {
		return new Runnable() {
			public void run() {
				if (resourceType.equals("codingSystem")) {
					new ResourceExplorerCodingSystem();
				} else if (resourceType.equals("dataset")) {
					new ResourceExplorerDataset();
				} else if (resourceType.equals("project")) {
					new ResourceExplorerProject();
				} else if (resourceType.equals("map")) {
					new ResourceExplorerMap();
				} else if (resourceType.equals("text")) {
					new ResourceExplorerText();
				} else if (resourceType.equals("textGroup")) {
					new ResourceExplorerTextGroup();
				} else if (resourceType.equals("chart")) {
					new ResourceExplorerChart();
				} else if (resourceType.equals("table")) {
					new ResourceExplorerTableView();
				} else if (resourceType.equals("report")) {
					new ResourceExplorerLatexReport();
				} else if (resourceType.equals("olap")) {
					new ResourceExplorerOlap();
				} else if (resourceType.equals("image")) {
					new ResourceExplorerImage();
				} else if (resourceType.equals("all")) {
					new ResourceExplorerSearch();
				}
			}
		};
	}

	private static Runnable newProject() {
		return new Runnable() {
			public void run() {
				MetadataEditorWindow meWindow = new MetadataEditorWindow();
				meWindow.build(false, true, false, MESaver.getSaveProjectListener(meWindow, null));
			}
		};
	}

	private static Runnable newMap() {
		return new Runnable() {
			public void run() {
				CreateMap.run();
			}
		};
	}

	private static Runnable newText() {
		return new Runnable() {
			public void run() {
				new TextEditor().build();
			}
		};
	}

	private static Runnable newTextGroup() {
		return new Runnable() {
			public void run() {
				MetadataEditorWindow meWindow = new MetadataEditorWindow();
				meWindow.build(false, true, false, MESaver.getSaveTextGroupListener(meWindow, null, null));
			}
		};
	}

	private static Runnable about() {
		return new Runnable() {
			public void run() {
				new About().build();
			}
		};
	}

	private static Runnable changeLanguage(final String language) {
		return new Runnable() {
			public void run() {
				try {
					XServiceEntry.getInstance().getFenixURL(new AsyncCallback<String>() {
						public void onSuccess(String result) {
							if (language.equalsIgnoreCase(BabelFish.print().english()))
								Window.open(result + "?locale=en", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							else if (language.equalsIgnoreCase(BabelFish.print().french()))
								Window.open(result + "?locale=fr", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							else if (language.equalsIgnoreCase(BabelFish.print().spanish()))
								Window.open(result + "?locale=es", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							else if (language.equalsIgnoreCase(BabelFish.print().chinese()))
								Window.open(result + "?locale=cn", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							else if (language.equalsIgnoreCase(BabelFish.print().russian()))
								Window.open(result + "?locale=ru", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							else if (language.equalsIgnoreCase(BabelFish.print().armenian()))
								Window.open(result + "?locale=am", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							else if (language.equalsIgnoreCase(BabelFish.print().bengali()))
								Window.open(result + "?locale=bd", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							else if (language.equalsIgnoreCase(BabelFish.print().arabic()))
								Window.open(result + "?locale=ar", "_blank", "toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
						}

						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}

	private static Runnable chartWizard() {
		return new Runnable() {
			public void run() {
//				new ChartWizard();
				new ChartDesignerWindow().build();
			}
		};
	}
	
	private static Runnable newImage() {
		return new Runnable() {
			public void run() {
				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
					new ImageImporterWindow(false).build();
				} else {
					FenixAlert.alert("Message", "You need to sign in before importing a new image");
				}
			}
		};
	}

	private static Runnable tableWizard() {
		return new Runnable() {
			public void run() {
				new ResourceExplorerDataset();
			}
		};
	}

	private static Runnable reportWizard() {
		return new Runnable() {
			public void run() {
//				new ReportWizard();
				new DesignerWindow().build();
			}
		};
	}

	private static Runnable uploadGWTDataset() {
		return new Runnable() {
			public void run() {
				new DatasetUploaderWindow().build();
			}
		};
	}

	private static Runnable xImporter() {
		return new Runnable() {
			public void run() {
				new XImporterWindow().build();
			}
		};
	}

	private static Runnable ofcgwtTest() {
		return new Runnable() {
			public void run() {
				// OfcChartWindow ofcChartWindow = new OfcChartWindow();
				// ofcChartWindow.build();
				new OfcChartWizard();
			}
		};
	}

	private static Runnable bangladeshReport() {
		return new Runnable() {
			public void run() {
				BangladeshWindow w = new BangladeshWindow();
				w.build();
			}
		};
	}

	private static Runnable ecReports() {
		return new Runnable() {
			public void run() {
				ECWindow window = new ECWindow();
				window.build(false);
			}
		};
	}

	private static Runnable ecHaitiReports() {
		return new Runnable() {
			public void run() {
				ECWindow window = new ECWindow();
				window.build(true);
			}
		};
	}

	private static Runnable ecNews() {
		return new Runnable() {
			public void run() {
				ECServiceEntry.getInstance().getLatestIrinNews("40764", 5, new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> result) {
						StringBuilder sb = new StringBuilder();
						for (String news : result)
							sb.append(news);
						FenixAlert.info("IRIN News", sb.toString());
					};

					public void onFailure(Throwable e) {
						FenixAlert.error("Error", e.getMessage());
					}
				});
			}
		};
	}

	private static Runnable ipcxmlTest() {
		return new Runnable() {
			public void run() {
				IPCViewerWindow v = new IPCViewerWindow();
				v.build("IPC.xml", true, "F.Grita, A.Vezzani - Bengo, Benguela, Cabinda - May To August 2009");
			}
		};
	}

	private static Runnable createDatasetRSS() {
		return new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				final LoadingWindow loading = new LoadingWindow("RSS", "GIEWS Workstation is creating the RSS channel.", "Loading...");
				try {
					XServiceEntry.getInstance().createDatasetRSS(false, new AsyncCallback() {
						public void onSuccess(Object result) {
							loading.destroyLoadingBox();
							FenixAlert.info("RSS", "Dataset RSS created.");
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							FenixAlert.error("Error", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("Error", e.getMessage());
				}
			}
		};
	}

	private static Runnable createDatasetRSSWithUniqueValues() {
		return new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				final LoadingWindow loading = new LoadingWindow("RSS", "GIEWS Workstation is creating the RSS channel.", "Loading...");
				try {
					XServiceEntry.getInstance().createDatasetRSS(true, new AsyncCallback() {
						public void onSuccess(Object result) {
							loading.destroyLoadingBox();
							FenixAlert.info("RSS", "Dataset RSS created.");
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							FenixAlert.error("Error", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("Error", e.getMessage());
				}
			}
		};
	}

	private static Runnable createTextRSS() {
		return new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				final LoadingWindow loading = new LoadingWindow("RSS", "GIEWS Workstation is creating the RSS channel.", "Loading...");
				try {
					XServiceEntry.getInstance().createTextRSS(new AsyncCallback() {
						public void onSuccess(Object result) {
							loading.destroyLoadingBox();
							FenixAlert.info("RSS", "Text RSS created.");
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							FenixAlert.error("Error", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("Error", e.getMessage());
				}
			}
		};
	}

	private static Runnable createRasterRSS() {
		return new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				final LoadingWindow loading = new LoadingWindow("RSS", "GIEWS Workstation is creating the RSS channel.", "Loading...");
				try {
					XServiceEntry.getInstance().createRasterRSS(new AsyncCallback() {
						public void onSuccess(Object result) {
							loading.destroyLoadingBox();
							FenixAlert.info("RSS", "Raster RSS created.");
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							FenixAlert.error("Error", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("Error", e.getMessage());
				}
			}
		};
	}

	private static Runnable createVectorRSS() {
		return new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				final LoadingWindow loading = new LoadingWindow("RSS", "GIEWS Workstation is creating the RSS channel.", "Loading...");
				try {
					XServiceEntry.getInstance().createVectorRSS(new AsyncCallback() {
						public void onSuccess(Object result) {
							loading.destroyLoadingBox();
							FenixAlert.info("RSS", "Raster RSS created.");
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							FenixAlert.error("Error", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("Error", e.getMessage());
				}
			}
		};
	}

	private static Runnable xExplorerWindow() {
		return new Runnable() {
			public void run() {
				XExplorerWindow w = new XExplorerWindow();
				w.build();
			}
		};
	}

	private static Runnable exportShapefile() {
		return new Runnable() {
			public void run() {
				try {
					XServiceEntry.getInstance().exportShapefile(new Long(30733188), new AsyncCallback<List<String>>() {
						public void onSuccess(List<String> result) {
							String text = "";
							for (String s : result)
								text += s + ", ";
							FenixAlert.info("INFO", "Succesfull! " + text);

						}

						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}

	private static Runnable email() {
		return new Runnable() {
			public void run() {
				try {
					final LoadingWindow l = new LoadingWindow("E-Mail Receiver", "FENIX is checking the e-mail account for attachments.", "Please wait");
					XServiceEntry.getInstance().checkEMail(new AsyncCallback<List<Long>>() {
						public void onSuccess(List<Long> ids) {
							l.destroyLoadingBox();
							FenixAlert.info("INFO", ids.size() + " resource(s) have been successfully imported.");
							l.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							l.destroyLoadingBox();
							FenixAlert.error("ERROR", e.getMessage());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.info("INFO", e.getMessage());
				}
			}
		};
	}

	@SuppressWarnings("unchecked")
	private static Runnable aquastat() {
		return new Runnable() {
			public void run() {
				AquastatServiceEntry.getInstance().createCharts(new AsyncCallback() {
					public void onSuccess(Object result) {
						FenixAlert.info("Info", "Aquastat Datasets have been synchronized.");
					};

					public void onFailure(Throwable e) {
						FenixAlert.error("Error", e.getMessage());
					}
				});
			}
		};
	}

	private static Runnable updateChartTest() {
		return new Runnable() {
			public void run() {

				// new ChartViewer("265730791", true);
				// new ChartViewer("355326431", true);
				// new ChartViewer("355336980", true);
				// 195117783
				// new ChartViewer("195117783", true); //Afganistan
				// new ChartViewer("355339581", true); //000TESTCHARTTTT
				// new ChartViewer("259853310", true); //Maize prices in kenya
				new ChartViewer("355349140", true); // OOTESTSTACK

			}

		};
	}

	private static Runnable chartBooleanFixer() {
		return new Runnable() {
			public void run() {
				BirtServiceEntry.getInstance().chartBooleanFixer(new AsyncCallback() {
					public void onSuccess(Object result) {
						FenixAlert.info("Info", "The charts are been fixed");
					};

					public void onFailure(Throwable e) {
						FenixAlert.error("Error", e.getMessage());
					}
				});
			}
		};
	}

	private static Runnable harvestGiewsCharts() {
		return new Runnable() {
			public void run() {
				ECServiceEntry.getInstance().harverstGiewsCharts(new AsyncCallback() {
					public void onSuccess(Object result) {
						FenixAlert.info("Info", "The charts are been Harvested");
					};

					public void onFailure(Throwable e) {
						FenixAlert.error("Error", e.getMessage());
					}
				});
			}
		};
	}

	private static Runnable queryBuilder() {
		return new Runnable() {
			public void run() {
				new QueryBuilder().build();
			}
		};
	}

	private static Runnable excelImporter() {
		return new Runnable() {
			public void run() {
				new ExcelImporterWindow().build();
			}
		};
	}
	
	private static Runnable designer() {
		return new Runnable() {
			public void run() {
//				new DesignerGridWindow().build();
				new DesignerWindow().build();
			}
		};
	}
	
	private static Runnable chartDesigner() {
		return new Runnable() {
			public void run() {
				new ChartDesignerWindow().build(); 
			}
		};
	}
	
	private static Runnable tinyMCEReport() {
		return new Runnable() {
			public void run() {
				new TinyMCEReportWindow().build(null); 
			}
		};
	}
	
	private static Runnable sldEditorShapefile() {
		return new Runnable() {
			public void run() {
				new SLDEditorWindow().build(null);
			}
		};
	}

	private static Runnable sldEditorRaster() {
		return new Runnable() {
			public void run() {
				new SLDEditorWindow("raster").build(null);
			}
		};
	}
	
	private static Runnable sldEditorImportSld() {
		return new Runnable() {
			public void run() {
				new SLDEditorWindow().importSld();
			}
		};
	}
	
	private static Runnable faostatImporter() {
		return new Runnable() {
			public void run() {
				new FAOStatWindow().build(EDISettings.FAOSTAT);
			}
		};
	}
	
	private static Runnable fewsnetImporter() {
		return new Runnable() {
			public void run() {
				new FEWSNETWindow().build(EDISettings.FEWSNET);
			}
		};
	}

	private static Runnable changeBanner() {
		return new Runnable() {
			public void run() {
				System.out.println("here");
				BannerChangeWindow window = new BannerChangeWindow();
				window.build();
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	private static Runnable recreateCharts() {
		return new Runnable() {
			public void run() {
				final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Re-creating Charts.", BabelFish.print().pleaseWait());
				try {
					ChartDesignerServiceEntry.getInstance().recreateCharts(new AsyncCallback() {
						public void onSuccess(Object arg0) {
							l.destroyLoadingBox();
							FenixAlert.info(BabelFish.print().info(), "Charts re-created.");
							l.destroyLoadingBox();
						};
						public void onFailure(Throwable arg0) {
							l.destroyLoadingBox();
							FenixAlert.error(BabelFish.print().error(), arg0.getMessage());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
					l.destroyLoadingBox();
				}
			}
		};
	}

	private static Runnable createADAMDonorMatrixViewsandResource() {
		return new Runnable() {
			public void run() {
				ADAMServiceEntry.getInstance().createDonorMatrixViews(new AsyncCallback<Long>() {
					public void onSuccess(Long result) {
						Info.display("New Resource View: Success", "created resourceId = " + result);
					}
					public void onFailure(Throwable caught) {
						Info.display("New Resource View: Failed", "Error in creating new resource view", caught.getLocalizedMessage());
					}
				});
			}
		};
	}
	
}