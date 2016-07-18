package org.fao.fenix.web.modules.core.client.control.menu;

import org.fao.fenix.web.modules.birt.client.countrybrief.CountryBriefController;
import org.fao.fenix.web.modules.birt.client.view.ISFP.ISFPController;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.birt.client.view.cropcalendar.CropCalendarController;
import org.fao.fenix.web.modules.birt.client.view.report.crisis.CrisisView;
import org.fao.fenix.web.modules.birt.client.view.report.giewsMap.GiewsMaps;
import org.fao.fenix.web.modules.birt.client.view.report.wizard.ReportWizard;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.ccbs.client.view.CCBSWindow;
import org.fao.fenix.web.modules.cfs.client.view.CFSWindow;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorWindow;
import org.fao.fenix.web.modules.coding.client.view.hierachy.CodingHierarchySelection;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchWindow;
import org.fao.fenix.web.modules.coding.common.services.CodingServiceEntry;
import org.fao.fenix.web.modules.codinghierarchyupload.client.view.CodingHierarchyUploaderWindow;
import org.fao.fenix.web.modules.codingmappingupload.client.view.CodingMappingUploaderWindow;
import org.fao.fenix.web.modules.codingupload.client.view.CodingUploaderWindow;
import org.fao.fenix.web.modules.communication.client.view.CommunicationModuleWindow;
import org.fao.fenix.web.modules.communication.client.view.CommunicationParameterWindow;
import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.console.client.view.ConsoleWindow;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.security.LoginController;
import org.fao.fenix.web.modules.core.client.security.groupmanager.GroupManagerController;
import org.fao.fenix.web.modules.core.client.security.permissionmanager.PermissionManagerController;
import org.fao.fenix.web.modules.core.client.security.usermanager.UserManagerController;
import org.fao.fenix.web.modules.core.client.toolbox.FenixToolbox;
import org.fao.fenix.web.modules.core.client.uploader.control.UploaderControllerImage;
import org.fao.fenix.web.modules.core.client.utils.About;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.datasetupload.client.view.DatasetUploaderWindow;
import org.fao.fenix.web.modules.fpi.client.view.index.FPIIndexWindow;
import org.fao.fenix.web.modules.geotiffupload.client.view.GeotiffUploader;
import org.fao.fenix.web.modules.giews.client.view.GIEWSWindow;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCReport;
import org.fao.fenix.web.modules.ipc.client.view.old.ShowIPCWorkflow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.action.CreateMap;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.pm.client.view.ProjectManager;
import org.fao.fenix.web.modules.rainfall.client.view.RainfallWindow;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.codingsystem.ResourceExplorerCodingSystem;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.map.ResourceExplorerMap;
import org.fao.fenix.web.modules.re.client.view.project.ResourceExplorerProject;
import org.fao.fenix.web.modules.re.client.view.report.ResourceExplorerReport;
import org.fao.fenix.web.modules.re.client.view.search.ResourceExplorerSearch;
import org.fao.fenix.web.modules.re.client.view.table.ResourceExplorerTable;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;
import org.fao.fenix.web.modules.shapefileupload.client.view.ShapeFileUploader;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.welcome.client.view.SplashWindow;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FenixCommandPool {

	public boolean isFpiPopulated = false;

	// private final Command defaultCmd = new Command() {
	// public void execute() {
	// Info.display("USER INFO",
	// "Nothing has been associated the item '{0}' yet.", "");
	// }
	// };

	/**
	 * This class builds and returns a command mapped into an XML file.
	 * 
	 * @param commandName
	 *            This name is into the XML.
	 * @param windowName
	 *            If the command has to create a window, this set up the frame
	 *            name.
	 * @return The choosen command.
	 */
	public Command getCommand(final String commandName, String windowName) {
		Command cmd;

		if (commandName.equals("uploadGWTDataset"))
			cmd = uploadGWTDataset();
		// else if (commandName.equals("uploadLayer"))
		// cmd = uploadLayer();
		else if (commandName.equals("chartWizard"))
			cmd = chartWizard();
		else if (commandName.equals("tableWizard"))
			cmd = tableWizard();
		else if (commandName.equals("reportWizard"))
			cmd = reportWizard();
		else if (commandName.equals("ISFP"))
			cmd = ISFP();
		else if (commandName.equals("textEditor"))
			cmd = textEditor();
		else if (commandName.equals("uploadGWTShapeFile"))
			cmd = uploadGWTShapeFile();
		else if (commandName.equals("uploadGWTGeoTiff"))
			cmd = uploadGWTGeoTiff();
		else if (commandName.equals("uploadImage"))
			cmd = uploadImage();
		else if (commandName.equals("testGetListAll"))
			cmd = testGetList("all"); // testGetList(ResourceType.ALL);
		else if (commandName.equals("testGetListDataset"))
			cmd = testGetList("dataset");
		else if (commandName.equals("testGetListChart"))
			cmd = testGetList("chart");
		else if (commandName.equals("testGetListMap"))
			cmd = testGetList("map");
		else if (commandName.equals("testGetListTable"))
			cmd = testGetList("table");
		else if (commandName.equals("testGetListLayer"))
			cmd = testGetList("layer");

		// else if (commandName.equals("createRandomMapView"))
		// cmd = createRandomMapView();
		// else if (commandName.equals("dumpRandomMapView"))
		// cmd = dumpRandomMapView();
		// else if (commandName.equals("runWMSHarvest"))
		// cmd = testWMSHarvest();
		else if (commandName.equals("runLayersImporter"))
			cmd = runLayersImporter();
		else if (commandName.equals("runGSSynch"))
			cmd = runGSSynch();
		else if (commandName.equals("runGeotiffHarvester"))
			cmd = runGeotiffHarvester();
		// else if (commandName.equals("createRandomProjectedData"))
		// cmd = createRandomProjectedData();

		else if (commandName.equals("console"))
			cmd = console();

		else if (commandName.equals("testGetTreeModel"))
			cmd = testGetTreeModel();

		else if (commandName.equals("testGetRecords"))
			cmd = testGetRecords();

		// Resource Explorer
		else if (commandName.equals("openProject"))
			cmd = openResource("project");
		else if (commandName.equals("openMap"))
			cmd = openResource("map");
		else if (commandName.equals("openText"))
			cmd = openResource("text");
		else if (commandName.equals("openChart"))
			cmd = openResource("chart");
		else if (commandName.equals("openCodingSystem"))
			cmd = openResource("codingSystem");
		else if (commandName.equals("openTable"))
			cmd = openResource("table");
		else if (commandName.equals("openReport"))
			cmd = openResource("report");
		else if (commandName.equals("openDataset"))
			cmd = openResource("dataset");
		else if (commandName.equals("openAll"))
			cmd = openResource("all");

		else if (commandName.equals("viewProjectManager"))
			cmd = viewProjectManager();
		else if (commandName.equals("viewToolbox"))
			cmd = viewToolbox();
		else if (commandName.equals("viewWorkspace"))
			cmd = viewWorkspace();
		else if (commandName.equals("viewWorkspaceManager"))
			cmd = viewWorkspaceManager();
		else if (commandName.equals("showReport"))
			cmd = showReport();
		else if (commandName.equals("createReport"))
			cmd = createReport();
		else if (commandName.equals("giewsMaps"))
			cmd = giewsMaps();
		else if (commandName.equals("communicationModule"))
			cmd = communicationModule();
		else if (commandName.equals("rainfall"))
			cmd = rainfall();
		else if (commandName.equals("textEditor"))
			cmd = createTextEditor();
		else if (commandName.equals("login"))
			cmd = login();
		else if (commandName.equals("userManager"))
			cmd = userManager();
		else if (commandName.equals("groupManager"))
			cmd = groupManager();
		else if (commandName.equals("permissionManager"))
			cmd = permissionManager();
		else if (commandName.equals("message"))
			cmd = message();
		else if (commandName.equals("welcome"))
			cmd = welcome();
		else if (commandName.equals("about"))
			cmd = about();
		else if (commandName.equals("openWiki"))
			cmd = openWiki();
		else if (commandName.equals("openForum"))
			cmd = openForum();
		else if (commandName.equals("openGettingStarted"))
			cmd = openGettingStarted();
		else if (commandName.equals("openHelpOnline"))
			cmd = openHelpOnline();
		else if (commandName.equals("openTutorials"))
			cmd = openTutorials();
		else if (commandName.equals("openProject"))
			cmd = openProject();
		else if (commandName.equals("newProject"))
			cmd = newProject();
		else if (commandName.equals("newMap"))
			cmd = newMap();
		else if (commandName.equals("cfs"))
			cmd = CFS();

		/* COMMUNICATION */
		else if (commandName.equals("ping"))
			cmd = ping();
		else if (commandName.equals("getCommunicationResourceList"))
			cmd = getCommunicationResourceList();
		else if (commandName.equals("getPeerList"))
			cmd = getPeerList();
		else if (commandName.equals("getActivePeerList"))
			cmd = getActivePeerList();
		else if (commandName.equals("getFenixServicesUrl"))
			cmd = getFenixServicesUrl();
		else if (commandName.equals("communicationModuleSettings"))
			cmd = communicationModuleSettingsWindow();
		else if (commandName.equals("communicationModuleUrl"))
			cmd = communicationModuleUrl();
		else if (commandName.equals("communicationModule"))
			cmd = communicationModule();
		else if (commandName.equals("metadataEditor"))
			cmd = me();
		else if (commandName.equals("olap"))
			cmd = olap();
		else if (commandName.equals("giewsCountryProjects"))
			cmd = giewsCountryProjects();

		/* Release populate */
		else if (commandName.equals("releasePopulate")) {
			cmd = releasePopulate();
		}

		/* EST Price Database */
		else if (commandName.equals("loadESTPriceDatabase")) {
			cmd = loadESTPriceDatabase();
		}

		/* IPC */
		else if (commandName.startsWith("showIPCWorkflows"))
			cmd = showIPCWorkflows();
		else if (commandName.startsWith("createIPCWorkflow"))
			cmd = createIPCWorkflow();
		else if (commandName.startsWith("createIPCReport"))
			cmd = createIPCReport();
		else if (commandName.startsWith("ISFP"))
			cmd = ISFP();
		else if (commandName.startsWith("fpiIndex"))
			cmd = fpiIndex();

		/* CCBS */
		else if (commandName.startsWith("ccbsTool"))
			cmd = ccbsTool();
		
		/* GIEWS Country Briefs */
		else if (commandName.startsWith("countryBriefs"))
			cmd = countryBriefs();

		/* GIEWS Crop Calendars */
		else if (commandName.startsWith("cropCalendars"))
			cmd = cropCalendars();
		
		/* GIEWS Open Table showing Latest Data */
		else if (commandName.startsWith("showUnfavourableTable"))
			cmd = showUnfavourableTable();
		else if (commandName.startsWith("showCrisisTable"))
			cmd = showCrisisTable();
		
		

		/* CODING DCMT */
		else if (commandName.equals("codingUploader"))
			cmd = codingUploader();
		else if (commandName.equals("codingMappingUploader"))
			cmd = codingMappingUploader();
		else if (commandName.equals("codingHierarchyUploader"))
			cmd = codingHierarchyUploader();
		else if (commandName.equals("codingFindConvert"))
			cmd = codingFindConvert();
		else if (commandName.equals("codingCreator"))
			cmd = codingCreator();
		else if (commandName.equals("populateCoding"))
			cmd = populateCoding();
		else if (commandName.equals("codingHierarchy"))
			cmd = codingHierarchy();
		else if (commandName.equals("importDefaultCodeLists"))
			cmd = importDefaultCodeLists();

		/* FPI */
		else if (commandName.equals("fpiIndex"))
			cmd = fpiIndex();

		/* BACKUP */
		else if (commandName.equals("backup"))
			cmd = backup();
		else if (commandName.equals("restore"))
			cmd = restore();
		else if (commandName.equals("fixerRegionDataset"))
			cmd = restore();

		/* DEFAULT */
		else
			cmd = new Command() {
				public void execute() {
					Info.display("USER INFO", "Nothing has been associated the item '{0}' yet.", commandName);
				}
			};

		return cmd;
	}

	private Command userManager() {
		return new Command() {
			public void execute() {
				UserManagerController.getInstance();
			}
		};
	}

	private Command groupManager() {
		return new Command() {
			public void execute() {
				GroupManagerController.getInstance();
			}
		};
	}

	private Command permissionManager() {
		return new Command() {
			public void execute() {
				PermissionManagerController.getInstance();
			}
		};
	}

	private Command ping() {
		return new Command() {
			public void execute() {
				/*
				 * Fenix.communicationService.ping("http://localhost:8080/fenix-services/services/CommunicationModuleService"
				 * , new AsyncCallback() { public void onSuccess(Object
				 * userList) { if (!((String) userList).equals("false")) new
				 * FenixDebugWindow("FENIX COMMUNICATION: " + (String)
				 * userList); else new FenixDebugWindow("Peer is off"); } public
				 * void onFailure(Throwable caught) { new
				 * FenixDebugWindow("FENIX COMMUNICATION: " +
				 * caught.getMessage()); } });
				 */
			}
		};
	}

	private Command getCommunicationResourceList() {
		return new Command() {
			public void execute() {
				// new CommunicationResourceListWindow().build();
			}
		};
	}

	private Command getFenixServicesUrl() {
		return new Command() {
			public void execute() {
				// new FenixDebugWindow("<ul><b>FENIX SERVICES URL</b></ul><ul>"
				// + Fenix.fenixServicesUrl + "</ul>");
			}
		};
	}

	private Command communicationModuleSettingsWindow() {
		return new Command() {
			public void execute() {
				new CommunicationParameterWindow().build();
			}
		};
	}

	private Command communicationModuleUrl() {
		return new Command() {
			public void execute() {
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

	private Command releasePopulate() {
		return new Command() {
			public void execute() {
				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				CodingServiceEntry.getInstance().loadReleaseData(new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroy();
						FenixAlert.info("Loaded release data", "OK");
					}

					public void onFailure(Throwable caught) {
						loading.destroy();
						FenixAlert.info("ERROR", "Failed");
					}
				});
			}
		};
	}

	private Command loadESTPriceDatabase() {
		return new Command() {
			public void execute() {
				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				BirtServiceEntry.getInstance().loadESTPriceDatabase(new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroy();
						FenixAlert.info("Load EST Price Database", "OK");
					}

					public void onFailure(Throwable caught) {
						loading.destroy();
						FenixAlert.info("Load EST Price Database", "Failed");
					}
				});
			}
		};
	}

	private Command getPeerList() {
		return new Command() {
			public void execute() {
				/*
				 * Fenix.communicationService.getPeerList(Fenix.fenixServicesUrl,
				 * new AsyncCallback() { public void onSuccess(Object userList)
				 * { String message = ""; List list = (List)userList; for (int i
				 * = 0 ; i < list.size() ; i++) { CommunicationResource rsrc =
				 * (CommunicationResource)list.get(i); message += "<ul>HOST: " +
				 * rsrc.getHost() + " LABEL: " + rsrc.getHostLabel() + "</ul>";
				 * } new FenixDebugWindow(message); } public void
				 * onFailure(Throwable caught) { new
				 * FenixDebugWindow("FENIX COMMUNICATION: " +
				 * caught.getMessage()); } });
				 */
			}
		};
	}

	private Command getActivePeerList() {
		return new Command() {
			public void execute() {
				/*
				 * Fenix.communicationService.getActivePeerList(Fenix.fenixServicesUrl
				 * , new AsyncCallback() { public void onSuccess(Object
				 * userList) { String message = ""; List list = (List)userList;
				 * for (int i = 0 ; i < list.size() ; i++) {
				 * CommunicationResource rsrc =
				 * (CommunicationResource)list.get(i); message += "<ul>HOST: " +
				 * rsrc.getHost() + " LABEL: " + rsrc.getHostLabel() + "</ul>";
				 * } new FenixDebugWindow(message); } public void
				 * onFailure(Throwable caught) { new
				 * FenixDebugWindow("FENIX COMMUNICATION: " +
				 * caught.getMessage()); } });
				 */
			}
		};
	}

	private Command newMap() {
		return new Command() {
			public void execute() {
				CreateMap.run();
			}
		};
	}

	private Command message() {
		return new Command() {
			public void execute() {
				// new FenixDebugWindow("Under construction");
			}
		};
	}

	private Command openProject() {
		return new Command() {
			public void execute() {
				// new ResourceExplorer().build(FenixConstants.TOOLBOX,
				// "project");
			}
		};
	}

	private Command newProject() {
		return new Command() {
			public void execute() {
				MetadataEditorWindow meWindow = new MetadataEditorWindow();
				meWindow.build(false, true, false, MESaver.getSaveProjectListener(meWindow, null));
			}
		};
	}

	private Command about() {
		return new Command() {
			public void execute() {
				new About().build();
			}
		};
	}

	private Command openWiki() {
		return new Command() {
			public void execute() {
				Window.open("http://ldvapp07.fao.org:8031/FenixUserWiki/index.php/Main_Page", "_blank", "");
			}
		};
	}

	private Command openForum() {
		return new Command() {
			public void execute() {
				Window.open("http://ldvapp07.fao.org:8031/phpBB3/", "_blank", "");
			}
		};
	}

	private Command openGettingStarted() {
		return new Command() {
			public void execute() {
				Window.open("http://ldvapp07.fao.org:8030/downloads/WS_QuickStartGuide.pdf", "_blank", "");
			}
		};
	}

	private Command openTutorials() {
		return new Command() {
			public void execute() {
				Window.open("http://ldvapp07.fao.org:8031/FenixUserWiki/index.php/Main_Page#Workstation_Help", "_blank", "");
			}
		};
	}
	
	private Command openHelpOnline() {
		return new Command() {
			public void execute() {
				Window.open("http://ldvapp07.fao.org:8031/FenixUserWiki/index.php/Main_Page#Workstation_Help", "_blank", "");
			}
		};
	}

	private Command welcome() {
		return new Command() {
			public void execute() {
//				new Welcome().build();
				new SplashWindow().build();
			}
		};
	}

	private Command showReport() {
		return new Command() {
			public void execute() {
				new CrisisView();
			}
		};
	}

	private Command createReport() {
		return new Command() {
			public void execute() {
				// new CreateReport().build();
			}
		};
	}

	private Command giewsMaps() {
		return new Command() {
			public void execute() {
				new GiewsMaps();
			}
		};
	}
	
	private Command createTextEditor() {
		return new Command() {
			public void execute() {
				// new FenixTextEditor().build();
			}
		};
	}

	private Command uploadGWTDataset() {
		return new Command() {
			public void execute() {
				// new DatasetUploaderController();
				new DatasetUploaderWindow().build();
			}
		};
	}

	private Command uploadGWTShapeFile() {
		return new Command() {
			public void execute() {
				new ShapeFileUploader().build();
			}
		};
	}

	private Command uploadGWTGeoTiff() {
		return new Command() {
			public void execute() {
				new GeotiffUploader().build();
			}
		};
	}

	private Command uploadImage() {
		return new Command() {
			public void execute() {
				new UploaderControllerImage();
			}
		};
	}
	
	private Command uploadLayer() {
		return new Command() {
			public void execute() {
				// new FenixLayerUploader().build();
			}
		};
	}

	private Command testGetList(final String type /* final ResourceType type */) {
		return new Command() {
			public void execute() {
				// new TestGetList(type).run();
			}
		};
	}

	private Command testWMSHarvest() {
		return new Command() {
			public void execute() {
				// new TestWMSHarvest().run();
			}
		};
	}

	private Command runLayersImporter() {
		return new Command() {
			public void execute() {
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

	private Command runGSSynch() {
		return new Command() {
			public void execute() {
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

	private Command runGeotiffHarvester() {
		return new Command() {
			public void execute() {
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

	private Command testGetTreeModel() {
		return new Command() {
			public void execute() {
				// new TestGetTreeModel().test();
			}
		};
	}

	private Command testGetRecords() {
		return new Command() {
			public void execute() {
				// new TestGetRecords().run();
			}
		};
	}

	// Show Workstation main objects
	private Command viewProjectManager() {
		return new Command() {
			public void execute() {
				if (!PMModel.isProjectManagerOpen() || PMModel.getProjectManager() == null) {
					new ProjectManager().build();
				} else {
					PMModel.getProjectManager().show();
				}
			}
		};
	}

	private Command viewToolbox() {
		return new Command() {
			public void execute() {
				if (!FenixToolbox.toolboxIsopen) {
					new FenixToolbox();
				}
			}
		};
	}

	private Command viewWorkspaceManager() {
		return new Command() {
			public void execute() {
				// Fenix.workspaceManager.window.show();
			}
		};
	}

	private Command viewWorkspace() {
		return new Command() {
			public void execute() {
				// Fenix.workspace.window.show();
			}
		};
	}

	private Command chartWizard() {
		return new Command() {
			public void execute() {

				new ChartWizard();

			}
		};
	}

	private Command reportWizard() {
		return new Command() {
			public void execute() {
				new ReportWizard();

			}
		};
	}

	private Command ISFP() {
		return new Command() {
			public void execute() {
				new ISFPController();
			}
		};
	}

	private Command countryBriefs() {
		return new Command() {
			public void execute() {
				new CountryBriefController();
			}
		};
	}

	private Command cropCalendars() {
		return new Command() {
			public void execute() {
				new CropCalendarController();
			}
		};
	}
	
	private Command showCrisisTable() {
		return new Command() {
			public void execute() {				
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

	private Command showUnfavourableTable() {
		return new Command() {
			public void execute() {
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

	private Command tableWizard() {
		return new Command() {
			public void execute() {
				// Fenix.tableWizard = new FenixTableWizard(new ArrayList());
			}
		};
	}

	private Command textEditor() {
		return new Command() {
			public void execute() {
				new TextEditor().build();
			}
		};
	}

	private Command communicationModule() {
		return new Command() {
			public void execute() {
				CommunicationModuleWindow cmw = new CommunicationModuleWindow();
				// cmw.getLoadingWindow().create();
				cmw.build();
			}
		};
	}

	private Command me() {
		return new Command() {
			public void execute() {
				MetadataEditorWindow me = new MetadataEditorWindow();
				me.build(true, true, false);
				me.getTabPanel().getTabPanel().setSelection(me.getTabPanel().getGeneralInfo());
			}
		};
	}

	private Command olap() {
		return new Command() {
			public void execute() {
				OlapWindow olap = new OlapWindow();
				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
					olap.build(true, true);
				} else {
					olap.build(true, false);
				}
			}
		};
	}
	
	private Command giewsCountryProjects() {
		return new Command() {
			public void execute() {
				GIEWSWindow giews = new GIEWSWindow();
				giews.build();
			}
		};
	}
	
	private Command CFS() {
		return new Command() {
			public void execute() {
				CFSWindow cfs = new CFSWindow();
				cfs.build();
			}
		};
	}

	private Command rainfall() {
		return new Command() {
			public void execute() {
				RainfallWindow rainfall = new RainfallWindow();
				rainfall.build();
			}
		};
	}

	private Command login() {
		return new Command() {
			public void execute() {
				LoginController.getInstance();
			}
		};
	}

	private Command openResource(final String resourceType) {
		return new Command() {
			public void execute() {
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
				} else if (resourceType.equals("chart")) {
					new ResourceExplorerChart();
				} else if (resourceType.equals("table")) {
					new ResourceExplorerTable();
				} else if (resourceType.equals("report")) {
					new ResourceExplorerReport();
				} else if (resourceType.equals("all")) {
					new ResourceExplorerSearch();
				}

			}
		};
	}

	private Command showIPCWorkflows() {
		return new Command() {
			public void execute() {
				new ShowIPCWorkflow().build();
			}
		};
	}

	private Command console() {
		return new Command() {
			public void execute() {
				new ConsoleWindow().build();
			}
		};
	}

	private Command createIPCWorkflow() {
		return new Command() {
			public void execute() {
//				new CreateIPCWorkflowTab().build();
			}
		};
	}
	
	private Command createIPCReport() {
		return new Command() {
			public void execute() {
				new CreateIPCReport().build();
			}
		};
	}

	private Command codingUploader() {
		return new Command() {
			public void execute() {
				new CodingUploaderWindow().build();
			}
		};
	}

	private Command codingMappingUploader() {
		return new Command() {
			public void execute() {
				new CodingMappingUploaderWindow().build();
			}
		};
	}
	
	private Command codingHierarchyUploader() {
		return new Command() {
			public void execute() {
				new CodingHierarchyUploaderWindow().build();
			}
		};
	}
	
	private Command fixerRegionDataset() {
		return new Command() {
			public void execute() {
				/** fixing region of the coding systems **/
				return;
//				new FixerRegionDS();
			}
		};
	}

	private Command codingFindConvert() {
		return new Command() {
			public void execute() {
				new CodingSearchWindow().build();
			}
		};
	}

	private Command codingCreator() {
		return new Command() {
			public void execute() {
				new DcmtCodingCreatorWindow().build();
			}
		};
	}

	private Command populateCoding() {
		return new Command() {
			public void execute() {
				new CodingSearchWindow().build();
			}
		};
	}
	
	private Command codingHierarchy() {
		return new Command() {
			public void execute() {
				new CodingHierarchySelection().build();
			}
		};
	}

	@SuppressWarnings("unchecked")
	private Command backup() {
		return new Command() {
			public void execute() {
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

	
	@SuppressWarnings("unchecked")
	private Command importDefaultCodeLists() {
		return new Command() {
			public void execute() {
				final LoadingWindow loading = new LoadingWindow();
				loading.create("Import Default Code Lists.");
				CodingServiceEntry.getInstance().importDefaultCodeLists(new AsyncCallback() {
					public void onSuccess(Object result) {
						loading.destroy();
						FenixAlert.info("INFO", "Import Code Lists complete!");
					}

					public void onFailure(Throwable e) {
						loading.destroy();
						FenixAlert.alert("RPC Failed", e.getMessage());
					}
				});
			}
		};
	}

	@SuppressWarnings("unchecked")
	private Command restore() {
		return new Command() {
			public void execute() {
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

	private Command fpiIndex() {
		return new Command() {
			public void execute() {
				new FPIIndexWindow().build();
			}
		};
	}

	private Command ccbsTool() {
		return new Command() {
			public void execute() {
				CCBSWindow w = new CCBSWindow();
				w.build();
				w.show();
			}
		};
	}
	
}
