package org.fao.fenix.web.modules.adam.common.model;


import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;


public class DonorMatrixModel extends BaseModel implements IsSerializable {

	String donor;

	String year; // change back to Date

	String KeySOs;

	String webPage;

	Double totalOde;
	Double productionPercentage;

	Double totalOdeSum;


public DonorMatrixModel() {
 }


public DonorMatrixModel(String donor, String keyOs, String year, Double totalODE, Double percent, String url,  Double totalODE2, Double percent2, String url2, Double sum) {
    set("name", donor);
    //deal with nulls
    set("totalOde", totalODE);
    set("webPage", url);
    set("totalOde2", totalODE2);
    set("webPage2", url2);
    set("totalOdeSum", sum);

    setYear(year);
   // setProductionPercentage(percent);
  }


  public DonorMatrixModel(String donor, String keyOs, String year, List<String> values, Double sum) {
    set("name", donor);
    set("year", year);
    //deal with nulls
    set("totalOde", Double.valueOf(values.get(0)));
    set("productionPercentage",  Double.valueOf(values.get(1)));
    set("webPage", values.get(2));
    set("totalOdeSum", sum);
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

    public Double getTotalODE() {
      return get("totalOde");
    }

     public void setTotalODE(Double totalOde) {
      if(totalOde==null || totalOde.equals(""))
	      	set("totalOde", 0d);
	      else
       set("totalOde", totalOde);
    }
    
  
   public void setWebPage(String url) {
	    if(url==null || url.equals(""))
	        	set("webPage", "");
	        else
       set("webPage", url);
	}

	public String getWebPage() {
		return get("webPage");
	}

	 public Double getTotalODESum() {
	      return get("totalOdeSum");
	    }

	 public void setTotalODESum(Double totalOdeSum) {
	      if(totalOdeSum==null || totalOdeSum.equals(""))
		      	set("totalOdeSum", 0d);
		      else
	       set("totalOdeSum", totalOdeSum);
	    }

	 public void setCPFReferenceDate(String refCPFDate) {
	        set("refCPFDate", refCPFDate);
	       }
	 
	    public String getCPFReferenceDate() {
	        return get("refCPFDate");
	      }
	    
	    public void setRecipientKeySOs(String keySOs) {
	        set("keySOs", keySOs);
	       }
	    
	    public String getRecipientKeySOs() {
	        return get("keySOs");
	      }
	    
	    public void setRecipientKeyOrs(String keyORs) {
	        set("keyORs", keyORs);
	       }
	    
	    public String getRecipientKeyOrs() {
	        return get("keyORs");
	      }
	    
	    public void setKeyComment(String KeySOs) {
	        set("keyComment", KeySOs);
	       }
	 
	    public String getKeyComment() {
	        return get("keyComment");
	      }
	 
	    public void setRecipientKeyORsDescription(String keyORsDesc) {
	        set("keyORsDesc", keyORsDesc);
	       }
	 
	    public String getRecipientKeyORsDescription() {
	        return get("keyORsDesc");
	      }
	    
	    public void setRecipientKeySOsDescription(String keySOsDesc) {
	        set("keySOsDesc", keySOsDesc);
	       }
	 
	    public String getRecipientKeySOsDescription() {
	        return get("keySOsDesc");
	      }
	    
}
