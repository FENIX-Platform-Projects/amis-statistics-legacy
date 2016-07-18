/*
 */

package org.fao.fenix.web.modules.map.common.vo;


import com.google.gwt.user.client.rpc.IsSerializable;


/**
 *
 * @author etj
 */

public class ClientGeoView extends EditableClientGeoView implements IsSerializable {

	private static Counter clientIdCounter = new Counter(100);
	
	private final long clientId;
	
	private String source;
	
	private String sourceContact;
	
	private String provider;
	
	private String providerContact;
	
	private String abstractAbstract;

	public ClientGeoView() {
		clientId = clientIdCounter.getNext();
	}

    public static enum LayerType implements IsSerializable {
        UNDEF, VECTOR, RASTER, EXTERNAL
    }

    public static enum VectorType implements IsSerializable {
        UNDEF, POINT, LINE, POLY
    }

	private long geoViewId = -1;
		
//	private String SRS;
		
	private String getMapUrl;
	
	private String layerName;

	private boolean queryable;
	
	private ClientBBox bbox;
	
	private boolean joinable;
	
	private long layerId; // needed if joinable

	private boolean joined;
	
	private long relatedDatasetId; // only valid if joined
    
	private LayerType layerType = LayerType.UNDEF;
    
    /** meaningful only if layerType==VECTOR */
	private VectorType vectorType = VectorType.UNDEF;

	/** range 0..100*/
	private int opacity = 100;
	
	private String lagendUrl;
	
	private String fenixCode;
	
	private Boolean isQuickPrj = false;

	public ClientGeoView copy() {
		ClientGeoView cgv = new ClientGeoView();
		cgv.setTitle(getTitle());
		cgv.setStyleName(getStyleName());
		cgv.setHidden(isHidden());
		cgv.getMapUrl = getMapUrl;
		cgv.queryable = queryable;
		cgv.bbox = bbox;
		cgv.joinable = joinable;
		cgv.layerId = layerId;
		cgv.joined = joined; // !!! 2 GV on the same PD?!?
		cgv.relatedDatasetId = relatedDatasetId;
		cgv.opacity = opacity;
		cgv.fenixCode = fenixCode;
		
		cgv.setLayerName(getLayerName());
		return cgv;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceContact() {
		return sourceContact;
	}

	public void setSourceContact(String sourceContact) {
		this.sourceContact = sourceContact;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderContact() {
		return providerContact;
	}

	public void setProviderContact(String providerContact) {
		this.providerContact = providerContact;
	}

	public String getAbstractAbstract() {
		return abstractAbstract;
	}

	public void setAbstractAbstract(String abstractAbstract) {
		this.abstractAbstract = abstractAbstract;
	}

	public String getFenixCode() {
		return fenixCode;
	}

	public void setFenixCode(String fenixCode) {
		this.fenixCode = fenixCode;
	}

	public String getGetMapUrl() {
		return getMapUrl;
	}

	/**
	 * This id is assigned for every CGV, even for the non stored ones.
	 * Use it to reference this CGV inside the client.
	 */
	public long getClientId() {
		return clientId;
	}
	
	public void setGetMapUrl(String getMapUrl) {
		this.getMapUrl = getMapUrl;
	}

	public long getGeoViewId() {
		return geoViewId;
	}

	public void setGeoViewId(long id) {
		this.geoViewId = id;
	}

	/**
	 * @return true if the GeoView is stored on DB and has a valid id.
	 */
	public boolean isStored() {
		return geoViewId != -1;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public boolean isQueryable() {
		return queryable;
	}

	public void setQueryable(boolean queryable) {
		this.queryable = queryable;
	}

	public ClientBBox getBBox() {
		return bbox;
	}

	public void setBBox(ClientBBox bbox) {
		this.bbox = bbox;
	}

	/** 
	 * Tells whether datasets can be projected onto this layer.
	 */
	public boolean isJoinable() {
		return joinable;
	}

	public void setJoinable(boolean isJoinable) {
		this.joinable = isJoinable;
	}
	
	public Long getLayerId() {
		return layerId;
	}

	public void setLayerId(long layerId) {
		this.layerId = layerId;
	}

	public boolean isJoined() {
		return joined;
	}

	public void setJoined(boolean joined) {
		this.joined = joined;
	}

	public long getRelatedDatasetId() {
		return relatedDatasetId;
	}

	public void setRelatedDatasetId(long relatedDatasetId) {
		this.relatedDatasetId = relatedDatasetId;
	}

	/**
	 */
	public LayerType getLayerType() {
		return layerType;
	}

	public void setLayerType(LayerType layerType) {
		this.layerType = layerType;
	}

	/**
	 * Will return one from the values TYPE_VECTOR_*
	 */
    public VectorType getVectorType() {
        return vectorType;
    }

    public void setVectorType(VectorType vectorType) {
        this.vectorType = vectorType;
    }

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(int opacity) {
		opacity = Math.max(opacity, 0);
		opacity = Math.min(opacity, 100);
		this.opacity = opacity;
	}

	public boolean incOpacity() {
		if(opacity < 100) {
			opacity += 10;
			opacity = Math.min(opacity, 100);
			return true;
		}
		return false;
	}

	public boolean decOpacity() {
		if(opacity > 0) {
			opacity -= 10;
			opacity = Math.max(opacity, 0);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ClientGeoView["
				+ "cid:" + clientId
				+ " gvid:" + geoViewId
				+ " lyid:" + layerId
				+ " type:" + layerType
				+"]";
	}

	public String getLagendUrl() {
		return lagendUrl;
	}

	public void setLagendUrl(String lagendUrl) {
		this.lagendUrl = lagendUrl;
	}

	public Boolean getIsQuickPrj() {
		return isQuickPrj;
	}

	public void setIsQuickPrj(Boolean isQuickPrj) {
		this.isQuickPrj = isQuickPrj;
	}
	
	

}
