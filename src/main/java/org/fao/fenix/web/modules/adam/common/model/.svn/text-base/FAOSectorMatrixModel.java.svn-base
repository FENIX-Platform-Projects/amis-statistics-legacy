package org.fao.fenix.web.modules.adam.common.model;


import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;


public class FAOSectorMatrixModel extends BaseModel implements IsSerializable {

	String donor;

	String year;

	String sector;

	Double commitmentOda;
	Double disbursementOda;


public FAOSectorMatrixModel() {
 }


public FAOSectorMatrixModel(String country, String donor, String sector, String year, Double commitment, Double disbursement) {
    set("name", country);
    set("donor", donor);
    set("sector", sector);
    set("commitment", commitment);
    set("disbursement", disbursement);
    setYear(year);
  }

  public String getRecipient() {
    return get("name");
  }

  public void setRecipient(String recipient) {
   set("name", recipient);
  }

  public String getRecipientCode() {
	    return get("recipientCode");
	  }

  public void setRecipientCode(String code) {
	   set("recipientCode", code);
   }

   public String getYear() {
      return get("year");
    }

    public void setYear(String year) {
     set("year", year);
  }

    public Double getCommitment() {
      return get("commitmentOda");
    }

     public void setCommitment(Double commitmentOda) {
      if(commitmentOda==null || commitmentOda.equals(""))
	      	set("commitmentOda", 0d);
	      else
       set("commitmentOda", commitmentOda);
    }
    
     public Double getDisbursement() {
         return get("disbursementOda");
       }

        public void setDisburs(Double disbursementOda) {
         if(disbursementOda==null || disbursementOda.equals(""))
   	      	set("disbursementOda", 0d);
   	      else
          set("disbursementOda", disbursementOda);
       }
       
        
   public void setFAOSector(String sector) {
	    if(sector==null || sector.equals(""))
	        	set("faoSector", "");
	        else
       set("faoSector", sector);
	}

	public String getFAOSector() {
		return get("faoSector");
	}
	
	public void setFAOSectorCode(String code) {
	    if(code==null || code.equals(""))
	        	set("faoSectorCode", "");
	        else
       set("faoSectorCode", code);
	}

	public String getFAOSectorCode() {
		return get("faoSectorCode");
	}
	
	public void setMappedORs(String ors) {
		 if(ors==null || ors.equals(""))
	        	set("mappedORs", "Not available");
	     else
             set("mappedORs", ors);
       }
    
    public String getMappedORs() {
        return get("mappedORs");
      }
    
	 public void setMappedORsDescription(String mappedORsDesc) {
	        set("mappedORsDesc", mappedORsDesc);
	}
	 
	public String getMappedORsDescription() {
	        return get("mappedORsDesc");
	}
	

	public void setTotalDelivery(Double totalDelivery) {
		if(totalDelivery==null || totalDelivery.equals(""))
			set("totalDelivery", 0d);
		else
			set("totalDelivery", totalDelivery);
	}

	public Double getTotalDelivery() {
		return get("totalDelivery");
	}
	
	public void setSectorDelivery(Double sectorDelivery) {
		if(sectorDelivery==null || sectorDelivery.equals(""))
			set("sectorDelivery", 0d);
		else
			set("sectorDelivery", sectorDelivery);
	}

	public Double getSectorDelivery() {
		return get("sectorDelivery");
	}

	public void setComparativeAdvantageRatio(Double comparativeAdvantageRatio) {
		if(comparativeAdvantageRatio==null || comparativeAdvantageRatio.equals(""))
			set("comparativeAdvantageRatio", 0d);
		else
			set("comparativeAdvantageRatio",comparativeAdvantageRatio);
	}

	public Double getComparativeAdvantageRatio() {
		return get("comparativeAdvantageRatio");
	}
	
	
	public void setHasComparativeAdvantage(String comparativeAdvantage) {
		set("comparativeAdvantage", comparativeAdvantage);
	}
	
	public String getHasComparativeAdvantage() {
		return get("comparativeAdvantage");
	}

	public void setResourcePartner(String donor) {
		set("donor", donor);
	}
	
	public String getResourcePartner() {
		return get("donor");
	}
	
	public void setResourcePartnerCode(String donorcode) {
		set("donorcode", donorcode);
	}
	
	public String getResourcePartnerCode() {
		return get("donorcode");
	}
      
	public void setSectorRelevance(Double sectorRelevance) {
		if(sectorRelevance==null || sectorRelevance.equals(""))
			set("sectorRelevance", 0d);
		else
			set("sectorRelevance", sectorRelevance);
	}
	
	public Double getSectorRelevance() {
		return get("sectorRelevance");
	}
}
