/*
 * 
 */
package org.fao.fenix.web.modules.core.server.utils;

import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.ResourceVisitor;
import org.fao.fenix.core.domain.chartdesigner.ChartDesign;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.layer.ExternalWMSLayer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.domain.perspective.ChartView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.perspective.ReportView;
import org.fao.fenix.core.domain.perspective.TableView;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.project.Project;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.textinfo.TextGroup;
import org.fao.fenix.core.domain.tinymce.TinyMCEReport;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

/**
 *
 * @author etj
 */
public class FenixResourceBuilder {

	public static FenixResourceVo build(GeoView geoView) {
		final FenixResourceVo fenixResource = new FenixResourceVo();
		fenixResource.setId(geoView.getId().toString());
		fenixResource.setTitle(geoView.getTitle());
		fenixResource.setName(geoView.getWmsMapProvider().getLayerName());
		fenixResource.setProject("==Unbindable==");
		fenixResource.setUrl(geoView.getWmsMapProvider().getGetMapUrl());
		fenixResource.setType(ResourceType.LAYER); // fixme
		return fenixResource;
	}

	public static FenixResourceVo build(Resource resource, PermissionVo permissionVo) {
		final FenixResourceVo fenixResource = new FenixResourceVo();
		fenixResource.setId(String.valueOf(resource.getResourceId()));
		fenixResource.setTitle(resource.getTitle());
		fenixResource.setType(resource.getClass().getSimpleName());
		fenixResource.setRegion(resource.getRegion());
		if (resource.getDateLastUpdate() != null) {
			fenixResource.setLastUpdated(resource.getDateLastUpdate().toString());
		} else {
			fenixResource.setLastUpdated("");
		}
		fenixResource.setProject("==PRJ TODO==");
		fenixResource.setUrl("==URL TODO==");

        fenixResource.setHasWritePermission(permissionVo.canWrite());
        fenixResource.setHasDeletePermission(permissionVo.canDelete());
        fenixResource.setHasDownloadPermission(permissionVo.canDownload());
		
		
		if (resource.getCategories() != null) {
			fenixResource.setCategory(resource.getCategories());
		} else {
			fenixResource.setCategory("");		//final FenixResource fenixResource = buildResource(res);
		}
		resource.accept(new ResourceVisitor() {

			@Override
			public void visit(Project r) {
				fenixResource.setType(ResourceType.PROJECT);
			}

//			@Override
//			public void visit(DataList r) {
//				fenixResource.setType(ResourceType.DATASET); // FIXME: this resource could also be a filterresult: is it ok?
//			}

			@Override
			public void visit(TableView r) {
				fenixResource.setType(ResourceType.TABLEVIEW);
			}

			@Override
			public void visit(ChartView r) {
				fenixResource.setType(ResourceType.CHARTVIEW);
			}
			
			@Override
			public void visit(ChartDesign r) {
				fenixResource.setType(ResourceType.CHARTVIEW);
			}

			@Override
			public void visit(ReportView r) {
				fenixResource.setType(ResourceType.REPORT);
			}
			
			@Override
			public void visit(TinyMCEReport r) {
				fenixResource.setType(ResourceType.REPORT);
			}

			@Override
			public void visit(MapView r) {
				fenixResource.setType(ResourceType.MAPVIEW);
			}

			@Override
			public void visit(InternalWMSLayer r) {
				fenixResource.setType(ResourceType.LAYER);
				fenixResource.setName(r.getLayerName());
				fenixResource.setUrl(r.getGetMapUrl());
			}

			@Override
			public void visit(ExternalWMSLayer r) {
				fenixResource.setType(ResourceType.LAYER);
				fenixResource.setName(r.getLayerName());
				fenixResource.setUrl(r.getGetMapUrl());
			}

			@Override
			public void visit(TextView r) {
				fenixResource.setType(ResourceType.TEXTVIEW);
			}
			
			@Override
			public void visit(TextGroup r) {
				fenixResource.setType(ResourceType.TEXTGROUP);
			}
			
			@Override
			public void visit(ResourceView r) {
				fenixResource.setType(ResourceType.OLAP);
			}

			private void deny(Resource r) {
				throw new FenixException("Can not pack a '" + r.getClass().getSimpleName() + "' into a FenixResource");
			}

//			@Override
//			public void visit(Pattern pattern) {
//				fenixResource.setType(ResourceType.PATTERN);
//			}
			
		});

		return fenixResource;
	}


public static FenixResourceVo build(Resource resource) {
	final FenixResourceVo fenixResource = new FenixResourceVo();
	fenixResource.setId(String.valueOf(resource.getResourceId()));
	fenixResource.setTitle(resource.getTitle());
	fenixResource.setType(resource.getClass().getSimpleName());
	fenixResource.setRegion(resource.getRegion());
	if (resource.getDateLastUpdate() != null) {
		fenixResource.setLastUpdated(resource.getDateLastUpdate().toString());
	} else {
		fenixResource.setLastUpdated("");
	}
	fenixResource.setProject("==PRJ TODO==");
	fenixResource.setUrl("==URL TODO==");

	if (resource.getCategories() != null) {
		fenixResource.setCategory(resource.getCategories());
	} else {
		fenixResource.setCategory("");		//final FenixResource fenixResource = buildResource(res);
	}
	resource.accept(new ResourceVisitor() {

		@Override
		public void visit(Project r) {
			fenixResource.setType(ResourceType.PROJECT);
		}

//		@Override
//		public void visit(DataList r) {
//			fenixResource.setType(ResourceType.DATASET); // FIXME: this resource could also be a filterresult: is it ok?
//		}

		@Override
		public void visit(TableView r) {
			fenixResource.setType(ResourceType.TABLEVIEW);
		}

		@Override
		public void visit(ChartView r) {
			fenixResource.setType(ResourceType.CHARTVIEW);
		}
		
		@Override
		public void visit(ChartDesign r) {
			fenixResource.setType(ResourceType.CHARTVIEW);
		}

		@Override
		public void visit(ReportView r) {
			fenixResource.setType(ResourceType.REPORT);
		}
		
		@Override
		public void visit(TinyMCEReport r) {
			fenixResource.setType(ResourceType.REPORT);
		}

		@Override
		public void visit(MapView r) {
			fenixResource.setType(ResourceType.MAPVIEW);
		}

		@Override
		public void visit(InternalWMSLayer r) {
			fenixResource.setType(ResourceType.LAYER);
			fenixResource.setName(r.getLayerName());
			fenixResource.setUrl(r.getGetMapUrl());
		}

		@Override
		public void visit(ExternalWMSLayer r) {
			fenixResource.setType(ResourceType.LAYER);
			fenixResource.setName(r.getLayerName());
			fenixResource.setUrl(r.getGetMapUrl());
		}

		@Override
		public void visit(TextView r) {
			fenixResource.setType(ResourceType.TEXTVIEW);
		}
		
		@Override
		public void visit(TextGroup r) {
			fenixResource.setType(ResourceType.TEXTGROUP);
		}
		
		@Override
		public void visit(ResourceView r) {
			fenixResource.setType(ResourceType.OLAP);
		}

		private void deny(Resource r) {
			throw new FenixException("Can not pack a '" + r.getClass().getSimpleName() + "' into a FenixResource");
		}

//		@Override
//		public void visit(Pattern pattern) {
//			fenixResource.setType(ResourceType.PATTERN);
//		}
		
	});

	return fenixResource;
}
}
