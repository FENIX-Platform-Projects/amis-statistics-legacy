package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxColors;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

public class ADAMQuestionFactory {

	private static List<ADAMQueryVO> questions = new ArrayList<ADAMQueryVO>();
	
	/** TODO: USE JUST FILTERS **/
	public static List<ADAMQueryVO> buildCountryQuestions(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		questions = new ArrayList<ADAMQueryVO>();
		questions.add(questionsViewMajorDonors(code, label, dates));
		questions.add(questionsViewBudgetPerSector(code, label, dates));
		questions.add(questionsViewMajorDonorsInAgriculture(code, label, dates));
//		questions.add(favouritePurposesQuestionsView(code, label, dates));
		questions.add(questionsViewFavouriteDeliveryChannel(code, label, dates));
		questions.add(questionsViewFAOProjectTypes(code, label, dates));
		questions.add(subAgriculturalSectorDonorBudget(label, filters, dates));
		return questions;
	}
	
	public static List<ADAMQueryVO> buildDonorQuestions(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		questions = new ArrayList<ADAMQueryVO>();
		System.out.println("building donor questions");
		questions.add(questionsViewMostFinancedCountriesByDonor(code, label, filters, dates));	
		questions.add(questionsViewMostFinancedSectorsByDonor(code, label, filters, dates));
		
		questions.add(questionsViewSubAgriculturalByDonor(code, label, filters, dates));
		questions.add(questionsViewFavoriteChannelsByDonor(code, label, filters, dates));
		questions.add(questionsViewFAOProjectTypesByDonor(code, label, filters, dates));
		questions.add(questionsViewTopCountriesByDonor(code, label, filters, dates));
		return questions;
	}
	
	private final static ADAMQueryVO questionsViewTopCountriesByDonor(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.topCountriesByDonor(label, dates, filters, ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.TABLE), 10);
		q.setQuestion("Which sectors are financed by " + label + " in its top recipient countries?");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT));
		q.setDescription("Chart 1 gives an overview of the geographic coverage of " + label + " aid flows. It displays the ten countries that receive the most ODA from " + label + ". ");
		return q;
	}
	
	private final static ADAMQueryVO questionsViewFAOProjectTypesByDonor(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.questionsViewFAOProjectTypes(label, filters, dates, ADAMColorConstants.color.get(ADAMBoxContent.CHART), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setQuestion("Which FAO interventions is " + label + " supporting?");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.SECTOR));
		q.setDescription("Chart 6 displays the sub-sectoral breakdown of FAO interventions supported by " + label + ", as reported in the DAC database. Interventions are considered at a global level. Where figures do not perfectly match  those reported in FAO internal databases, this is due to the fact that only actions funded by extrabudgetary resources are reported here, and because of differing ways of registering joint programme activities.");
		return q;
	}

	
	private final static ADAMQueryVO questionsViewMostFinancedCountriesByDonor(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.mostFinancedCountriesByDonor(code, label, filters, dates, ADAMColorConstants.color.get(ADAMBoxContent.CHART), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setQuestion("In which countries is " + label + " most active?");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT));
		q.setDescription("Chart 1 gives an overview of the geographic coverage of DONRX aid flows. It displays the ten countries that receive the most ODA from " + label + ".");
		return q;
	}
	
	private final static ADAMQueryVO questionsViewMostFinancedSectorsByDonor(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.mostFinancedSectorsByDonor(code, label, dates,  ADAMColorConstants.color.get(ADAMBoxContent.CHART), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setQuestion("To which sectors does " + label + " allocate aid?");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.SECTOR));
		q.setDescription("A number of individual resource partners extend relatively high proportions of their aid to agricultural projects. Chart 2 displays the sectors to which " + label + " contributes most of its ODA. By looking at the amounts allocated, it is possible to derive the relative importance that " + label + " attaches to specific sectors. Only the ten most financed sectors are shown.");
		return q;
	}
	
	private final static ADAMQueryVO questionsViewSubAgriculturalByDonor(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, dates, "Sub-sectoral breakdown on agricultural aid", ADAMColorConstants.color.get(ADAMBoxContent.CHART), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel(), true, 8);
		q.setQuestion("To which agricultural sub-sectors does " + label + " allocate aid?");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.SECTOR));
		q.setDescription("Chart 4 highlights the proportions of " + label + "'s ODA allocated to different agriculture related sub-sectors.  Forestry and Fishery are included in total aid to agriculture.");
		return q;
	}
	
	private final static ADAMQueryVO questionsViewFavoriteChannelsByDonor(String code, String label, HashMap<String, List<String>> filters, List<String> dates) {
		List<String> codes = new ArrayList<String>();
		codes.add(code);
		ADAMQueryVO q = ADAMQueryVOBuilder.donorViewFavouriteChannels("Channels of Delivery in Agriculture", codes, label, dates, ADAMColorConstants.color.get(ADAMBoxContent.CHART), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel(), true);
		q.setQuestion("Which are the channels of delivery most used by " + label + "?");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL));
		q.setDescription("Knowing the channel of delivery allows for a distinction to be made between aid delivered directly by the resource partner and aid channelled through other implemennting agencies (multilateral organizations, NGOs etc).  Resource partners have slightly different patterns in the use of different channel of deliveries. Chart 5 shows the implementing agencies most used by " + label + ".");
		return q;
	}

	private final static ADAMQueryVO subAgriculturalSectorDonorBudget(String label, HashMap<String, List<String>> filters, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.topAgriculturalCountryView(label, dates,filters, ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.TABLE), 10);
		q.setQuestion("How are agricultural sectors  financed in " + label + "?");
		q.setDescription("This table shows the 10 most active resource partners in agriculture related sub-sectors. It displays the most active resource partner in each sub-sector, as well as the channel of delivery most used.");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.SECTOR));
		return q;
	}
	
	private final static ADAMQueryVO favouritePurposesQuestionsView(String code, String label, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.favouritePurposesQuestionsView(code, label, dates, ADAMColorConstants.color.get(ADAMBoxContent.TABLE));
		q.setQuestion("Which purpose do the Donors follow in " + label + "?");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.SECTOR));
		
		return q;
	}

	private final static ADAMQueryVO questionsViewBudgetPerSector(String code, String label, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.questionsViewBudgetPerSector(code, label, dates, ADAMBoxColors.yellow.name(), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setTitle("Most financed sectors in " + label );
		q.setQuestion("How much is allocated per sector in " + label + "?");
		q.setDescription("Different countries have different priorities in terms of their development goals. Chart 1 displays the sectors in " + label + " that are most financed by external resource partners. By looking at the amount allocated, it is possible to derive the relative importance that " + label + " attaches to specific sectors. (is this right? isn't this table for all donors?) The ten most financed sectors are displayed. If a specific sector is not displayed it means that is not one of the resource partner's priority areas of action.");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.SECTOR));
		return q;
	}
	
	private final static ADAMQueryVO questionsViewMajorDonors(String code, String label, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.questionsViewMajorDonors(code, label, dates, ADAMBoxColors.green.name(), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setQuestion("Which are the major resource partners active in " + label + "?");
		q.setDescription("The chart ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
		q.setDescription("Chart 2 ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
		return q;
	}
	
	private final static ADAMQueryVO questionsViewMajorDonorsInAgriculture(String code, String label, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.questionsViewMajorDonorsInAgriculture(code, label, dates, ADAMBoxColors.red.name(), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setQuestion("Which are the major resource partners active in the agricultural sector in " + label + "?");
		q.setDescription("The chart shows the most active resource partners in the agricultural sub-sectors, listing the top ten most active, based on total disbursement.");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
		return q;
	}
	
	private final static ADAMQueryVO questionsViewFavouriteDeliveryChannel(String code, String label, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.questionsViewFavouriteDeliveryChannel(code, label, dates, ADAMBoxColors.yellow.name(), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setQuestion("Which are the most used channels of delivery in " + label + "?");	
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL));
		q.setDescription("Knowing the channel of delivery allows for  a distinction to be made between aid flows delivered directly to the country, those delivered by the resource partners and those channelled through other organisations. The channel of delivery usually refers to the implementing agencies. The chart shows the most used channels of delivery in " + label + ".");
		return q;
	}
	
	private final static ADAMQueryVO questionsViewFAOProjectTypes(String code, String label, List<String> dates) {
		ADAMQueryVO q = ADAMQueryVOBuilder.questionsViewFAOProjectTypes(code, label, dates, ADAMBoxColors.green.name(), ADAMController.crs_aggregationColumn.getGaulCode(), ADAMController.crs_aggregationColumn.getGaulLabel());
		q.setQuestion("Which kind of projects is FAO implementing in  " + label + "?");
		q.setDescription("the chart reports the sub-sectoral breakdown of FAO actions in " + label + " as reported in the DAC database. Where figures do not perfectly match  those reported in FAO internal databases, this is due to the fact that only actions funded by extrabudgetary resources are reported here, and because of differing ways of registering joint programme activities.");
		q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.SECTOR));
		return q;
	}
	
}