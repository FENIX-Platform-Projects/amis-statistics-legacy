package org.fao.fenix.web.modules.haiti.common.vo;

import org.fao.fenix.web.modules.map.common.vo.ClientBBox;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class LayerVO extends BaseModel implements IsSerializable {

	private String layerTitle;
	
	private String layerName;
	
	private String styleName;
	
	private String layerType;
	
	private String wmsURL;
	
	private Long olID;
	
	private Long layerID;
	
	private float opacity;
	
	private String legendUrl;
	
	private String fenixCode;
	
	private String source;
	
	private String sourceContact;
	
	private String provider;
	
	private String providerContact;
	
	private String abstractAbstract;
	
	private Boolean isHidden = false;
	
	private Boolean isBaseLayer = false;
	
	private ClientBBox clientBBox;
	
	/** this is used for ADAM **/
	
	private Boolean getLegend = false;
	
	private Boolean getFeatureInfo = false;
	
	private Boolean isQuickPrj = false;
	
	public LayerVO() {
		
	}
	
	public LayerVO(String layerTitle, Long olID, float opacity) {
		this.setLayerTitle(layerTitle);
		this.setOlID(olID);
		this.setOpacity(opacity);
	}
	
	public LayerVO(String layerTitle, Long olID, float opacity, String layerType) {
		this.setLayerTitle(layerTitle);
		this.setOlID(olID);
		this.setOpacity(opacity);
		this.setLayerType(layerType);
	}
	
	public LayerVO(String layerTitle, Long olID, Long layerID, float opacity, String layerType, String layerName) {
		this.setLayerTitle(layerTitle);
		this.setOlID(olID);
		this.setOpacity(opacity);
		this.setLayerType(layerType);
		this.setLayerName(layerName);
		this.setLayerID(layerID);
	}
	
	public LayerVO(Long olID, String layerTitle, String layerName, String layerStyle, String wmsURL) {
		this.setOlID(olID);
		this.setLayerTitle(layerTitle);
		this.setLayerName(layerName);
		this.setWmsURL(wmsURL);
		this.setStyleName(layerStyle);
	}
	
	public String getFenixCode() {
		return fenixCode;
	}

	public void setFenixCode(String fenixCode) {
		this.fenixCode = fenixCode;
	}

	public Long getLayerID() {
		return layerID;
	}

	public void setLayerID(Long layerID) {
		this.layerID = layerID;
		set("layerID", this.layerID);
	}

	public String getLayerTitle() {
		return layerTitle;
	}

	public void setLayerTitle(String layerTitle) {
		this.layerTitle = layerTitle;
		set("layerTitle", this.layerTitle);
	}

	public Long getOlID() {
		return olID;
	}

	public void setOlID(Long olID) {
		this.olID = olID;
		set("olID", this.olID);
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
		set("opacity", this.opacity);
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
		set("layerName", this.layerName);
	}

	public String getLayerType() {
		return layerType;
	}

	public void setLayerType(String layerType) {
		this.layerType = layerType;
		set("layerType", this.layerType);
	}

	public String getWmsURL() {
		return wmsURL;
	}

	public void setWmsURL(String wmsURL) {
		this.wmsURL = wmsURL;
		set("wmsURL", this.wmsURL);
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
		set("styleName", this.styleName);
	}

	public String getLegendUrl() {
		return legendUrl;
	}

	public void setLegendUrl(String legendUrl) {
		this.legendUrl = legendUrl;
		set("legendUrl", this.legendUrl);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
		set("source", this.source);
	}

	public String getSourceContact() {
		return sourceContact;
	}

	public void setSourceContact(String sourceContact) {
		this.sourceContact = sourceContact;
		set("sourceContact", this.sourceContact);
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
		set("provider", this.provider);
	}

	public String getProviderContact() {
		return providerContact;
	}

	public void setProviderContact(String providerContact) {
		this.providerContact = providerContact;
		set("providerContact", this.providerContact);
	}

	public String getAbstractAbstract() {
		return abstractAbstract;
	}

	public void setAbstractAbstract(String abstractAbstract) {
		this.abstractAbstract = abstractAbstract;
		set("abstractAbstract", this.abstractAbstract);
	}

	public Boolean isBaseLayer() {
		return isBaseLayer;
	}

	public void setIsBaseLayer(Boolean isBaseLayer) {
		this.isBaseLayer = isBaseLayer;
	}

	public Boolean isHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public ClientBBox getClientBBox() {
		return clientBBox;
	}

	public void setClientBBox(ClientBBox clientBBox) {
		this.clientBBox = clientBBox;
	}

	public Boolean getGetLegend() {
		return getLegend;
	}

	public void setGetLegend(Boolean getLegend) {
		this.getLegend = getLegend;
	}

	public Boolean getGetFeatureInfo() {
		return getFeatureInfo;
	}

	public void setGetFeatureInfo(Boolean getFeatureInfo) {
		this.getFeatureInfo = getFeatureInfo;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public Boolean getIsBaseLayer() {
		return isBaseLayer;
	}

	public Boolean getIsQuickPrj() {
		return isQuickPrj;
	}

	public void setIsQuickPrj(Boolean isQuickPrj) {
		this.isQuickPrj = isQuickPrj;
	}
	
	
	
}