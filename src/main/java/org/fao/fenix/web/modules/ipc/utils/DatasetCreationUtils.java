package org.fao.fenix.web.modules.ipc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.jdom.Element;

public class DatasetCreationUtils
{
	public enum DatasetType { PHASE_TYPE, RISK_TYPE, TREND_TYPE }

	private static byte[] encodeMetadata(WorkflowInfoVO workflow, Element metadataTemplate, String date, DatasetType type) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Element metadata    = (Element)metadataTemplate.clone();
		String period = "";
		if(workflow.getPeriod()!=null)
			period = "("+workflow.getPeriod()+")";	
		
		String datasetType = null;
		String title       = workflow.getWorkflowName() + period;
		String abstractAbstract  = null;
		switch (type)
		{
		case PHASE_TYPE:
			datasetType = "IPCPhaseDataset";
			title       += " (IPC Phases)";
			abstractAbstract = "Phases for "+title;
			break;
		case RISK_TYPE:
			datasetType = "IPCRiskDataset";
			title       += " (IPC Risks)";
			abstractAbstract = "Risk levels for "+title;
			break;
		case TREND_TYPE:
			datasetType = "IPCTrendDataset";
			title       += " (IPC Trends)";
			abstractAbstract = "Projected trends for "+title;
			break;
		}
		metadata.getChild("type").setText(datasetType);
		metadata.getChild("title").setText(title);
		metadata.getChild("abstractAbstract").setText(abstractAbstract);
		metadata.getChild("startDate").setText(date);
		metadata.getChild("endDate").setText(date);
		metadata.getChild("dateLastUpdate").setText(date);
		metadata.getChild("region").setText(workflow.getGeographicArea().getCode());
		metadata.getChild("refGeoUnit").setText(workflow.getReferenceLayer().getLabel());
		metadata.getChild("featureCodeSet").setText(workflow.getReferenceLayer().getCode());
		metadata.getChild("source").getAttribute("country").setValue(workflow.getGeographicArea().getCode()); 

		Element secondDescriptor    = (Element)metadata.getChild("descriptors").getChildren("descriptor").get(1);
		Element secondOption        = (Element)secondDescriptor.getChild("options").getChildren("option").get(1);
		Element featureCodeSetValue = secondOption.getChild("value");
		featureCodeSetValue.setText(workflow.getReferenceLayer().getCode());

		XmlUtils.write(metadata, baos);
		return baos.toByteArray();
	}
	
	/*public static String createZipFile(String localResourceId, List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow, Element metadataTemplate, DatasetType type) throws IOException
	{
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String outFileName      = localResourceId + ".zip";
		String datasetFileName  = localResourceId + ".csv";
		String metadataFileName = localResourceId + "_metadata.xml";

		byte[] buf = new byte[1024];
		String tmpDir = System.getProperty("java.io.tmpdir");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tmpDir + "/" + outFileName));

		System.out.println("createZipFile: tmpDir: " + tmpDir); // DEBUG

		ByteArrayInputStream in = new ByteArrayInputStream(encodeMetadata(workflow, metadataTemplate, date, type));
		out.putNextEntry(new ZipEntry(metadataFileName));
		for (int len; (len = in.read(buf)) > 0; ) out.write(buf, 0, len);

		in = new ByteArrayInputStream(encodeDataset(userSpecificProvinceVO, workflow, date, type));
		out.putNextEntry(new ZipEntry(datasetFileName));
		for (int len; (len = in.read(buf)) > 0; ) out.write(buf, 0, len);
		out.closeEntry();

		in.close();
		out.close();
		
		System.out.println("createZipFile; outFileName: " + outFileName); // DEBUG
		return outFileName;
	}*/
	
	public static void createDatasetFiles(String localResourceId, List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow, Element metadataTemplate, DatasetType type) throws IOException
	{
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String datasetFileName  = localResourceId + ".csv";
		String metadataFileName = localResourceId + "_metadata.xml";
		
		String tmpDir = System.getProperty("java.io.tmpdir");
			
		ByteArrayInputStream in = new ByteArrayInputStream(encodeMetadata(workflow, metadataTemplate, date, type));
		byte[] buf = new byte[1024];
		
		File newFile=new File(tmpDir + File.separator+ metadataFileName);
		FileOutputStream out = new FileOutputStream(newFile);
		for (int len; (len = in.read(buf)) > 0; ) out.write(buf, 0, len);

		newFile=new File(tmpDir + File.separator+ datasetFileName);
		out = new FileOutputStream(newFile);
		in = new ByteArrayInputStream(encodeDataset(userSpecificProvinceVO, workflow, date, type));
		for (int len; (len = in.read(buf)) > 0; ) out.write(buf, 0, len);
		
		in.close();
		out.flush();
		out.close();
		
		
	}
	
	private static byte[] encodeDataset(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow, String date, DatasetType type)
	{
		StringBuilder sb = new StringBuilder();
		switch (type)
		{
		case PHASE_TYPE:
			sb.append("Date,Area,Phase,Workflow ID,User ID\r\n");
			break;
		case RISK_TYPE:
			sb.append("Date,Area,Risk,Workflow ID,User ID\r\n");
			break;
		case TREND_TYPE:
			sb.append("Date,Area,Trend,Workflow ID,User ID\r\n");
			break;
		}
			
				
		for (int i = 0; i < userSpecificProvinceVO.size(); i++)
		{
			ProvinceVO area = userSpecificProvinceVO.get(i);
			
			sb.append(date);
			sb.append(",");
			sb.append(area.getProvinceCode());
			sb.append(",");
			switch (type)
			{
			case PHASE_TYPE:
				if(area.getPhaseClassification()==null)
					sb.append("");
				else sb.append(area.getPhaseClassification());
				break;
			case RISK_TYPE:
				if(area.getRiskLevel()==null)
					sb.append("");
				else sb.append(area.getRiskLevel());
				break;
			case TREND_TYPE:
				if(area.getProjectedTrend()==null)
					sb.append("");
				else sb.append(area.getProjectedTrend());
				break;
			}
			sb.append(",");
			sb.append(workflow.getWorkflowId());
			sb.append(",");
			sb.append(userSpecificProvinceVO.get(0).getContributor_id().toString());
			sb.append("\r\n");

		}
		
		return sb.toString().getBytes();
	}
	
	public static String createResourceId(String workflowId, String userId, DatasetType type)
	{
		String resourceId = null;
		switch (type)
		{
		case PHASE_TYPE:
			resourceId = "IPC_PHASE_";
			break;
		case RISK_TYPE:
			resourceId = "IPC_RISK_";
			break;
		case TREND_TYPE:
			resourceId = "IPC_TREND_";
			break;
		}
		//resourceId += workflowId + "_" + userId;
		resourceId += workflowId;
		return resourceId;
	}
}
