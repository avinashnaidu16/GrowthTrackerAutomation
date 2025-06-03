package com.GrowthTrackerTests.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GrowthTrackerTests.Base.CommonToAllTests;
public class AddPitchLocal_POM extends CommonToAllTests
{
	

		WebDriver driver;
		
		
		private By btnCountry = By.xpath("//div[contains(@placeholder,'Select Country')]//button");
		private By inputCountry = By.xpath("//input[contains(@placeholder,'Select Country')]");
		private By actualTextcountryfield = By.xpath("//div[contains(@placeholder,'Select Country')]//button//span[starts-with(@class,'ng-bind')]");
		

		
		private By btnCity = By.xpath("//div[contains(@placeholder,'Select City')]//button");
		private By inputCity = By.xpath("//input[contains(@placeholder,'Select City')]");
		
		private By btnAgency = By.xpath("//div[contains(@placeholder,'Select Agency')]//button");
		private By inputAgency = By.xpath("//input[contains(@placeholder,'Select Agency')]");
		
		private By btnLevel = By.xpath("//div[contains(@placeholder,'Select Level')]//button");
		private By inputLevel = By.xpath("//input[contains(@placeholder,'Select Level')]");
		
		private By btnRelationship = By.xpath("//div[contains(@placeholder,'Select Relationship')]//button");
		private By inputRelationship = By.xpath("//input[contains(@placeholder,'Select Relationship')]");
		
		private By btnIndustry = By.xpath("//div[contains(@placeholder,'Select Industry')]//button");
		private By inputIndustry = By.xpath("//input[contains(@placeholder,'Select Industry')]");
		
		private By btnSubIndustry = By.xpath("//div[contains(@placeholder,'Select Sub-Industry')]//button");
		private By inputSubIndustry = By.xpath("//input[contains(@placeholder,'Select Sub-Industry')]");
		
		private By inputDateInvited = By.xpath("//label[contains(text(),'Date Invited')]//following-sibling::div//input");
		
		
		
		private By inputDateFinalPresentation = By.xpath("//label[contains(text(),'Final Presentation')]//following-sibling::div//input");
		
		private By dateFinalPresentation = By.xpath("//label[contains(text(),'Final Presentation')]//following-sibling::div//table//tbody//span[contains(text(),'February')]");
		
		private By inputDateContractStarts = By.xpath("//label[contains(text(),'Contract Starts')]//following-sibling::div//input");
		
		private By dateContractStarts = By.xpath("//label[contains(text(),'Contract Starts')]//following-sibling::div//table//tbody//span[contains(text(),'February')]");
		
		
		
		private By dateDateInvited = By.xpath("//label[contains(text(),'Date Invited')]//following-sibling::div//table//tbody//span[contains(text(),'February')]");
		
	
		private By btnStatus = By.xpath("//div[contains(@placeholder,'Select Status')]//button");
		private By inputStatus = By.xpath("//input[contains(@placeholder,'Select Status')]");
		
		
		private By inputGHC = By.xpath("//input[contains(@placeholder,'Select GHC')]");
		
		private By selectGHC = By.xpath("//div[starts-with(text(),'Mars') and not(contains(text(),'PLC'))]");
		private By addButtonModalBox = By.xpath("//button[starts-with(@id,'add-newclient-add') and starts-with(text(),'Add')]");
		
		private By labelScope = By.xpath("//label[contains(text(),'Select Scope')]");
		private By labelScopeCount = By.xpath("//label[contains(text(),'Select Scope Count')]");
		private By labelScopedropdownoptions = By.xpath("//ul[contains(@class,'dropdown-menu pitch-dropdown')]");
		
		private By labelWPPAgency = By.xpath("//label[contains(text(),'Select WPP Agency')]");		
		private By labelWPPDropdownoptions = By.xpath("//label[contains(text(),'WPP Agencies')]//following-sibling::div//ul[contains(@class,'dropdown-menu pitch-dropdown')]");
		//following-sibling::div
		
		private By radioBtnWPPOpenYes = By.xpath("//label[contains(text(),'Yes')]");
		
		
		private By inputAdvertiser = By.xpath("//input[starts-with(@name,'advertiserProductField')]");

		private By inputEstimatedAnnualBillingLocal = By.xpath("//input[contains(@ng-model,'EstimatedAnnualBillingLocal')]");

		private By inputAuditor = By.xpath("//input[contains(@ng-model,'Auditor')]");
		
		private By inputGrowthLead = By.xpath("//input[contains(@ng-model,'BusinessLead')]");
		
		private By inputAccountLead = By.xpath("//input[contains(@ng-model,'AccountDirector')]");
		
		
		
		private By labelB2B = By.xpath("//label[contains(text(),'This advertiser/product is a B2B client')]");
		private By labelB2BAll = By.xpath("//label[starts-with(text(),'All')]");
		private By labelB2BSome = By.xpath("//label[starts-with(text(),'Some')]");
		
		private By inputtextDescription = By.xpath("//textarea[starts-with(@name,'description')]");
		
		private By inputtextNextSteps = By.xpath("//textarea[contains(@ng-model,'Comments')]");
		
		private By btnSourceBilling = By.xpath("//div[contains(@placeholder,'Select Source')]//button");
		private By inputSourceBilling = By.xpath("//input[contains(@placeholder,'Select Source')]");
		
		
		private By btnSourceOpportunity = By.xpath("//div[contains(@placeholder,'Select Source Of Opportunity')]//button");
		private By inputSourceOpportunity = By.xpath("//input[contains(@placeholder,'Select Source Of Opportunity')]");
		
		
		
		private By labelGHCInfoIcon = By.xpath("//label[contains(text(),'Global Holding Company')]//span[contains(@class,'info-sign')]");
		private By labelLevelOfOpportunityInfoIcon = By.xpath("//label[contains(text(),'Level Of Opportunity')]//span[contains(@class,'info-sign')]");
		private By labelDescriptionInfoIcon = By.xpath("//label[contains(text(),'Description')]//span[contains(@class,'info-sign')]");
		private By labelStatusInfoIcon = By.xpath("//label[contains(text(),'Status')]//span[contains(@class,'info-sign')]");
		private By labelEstimatedAnnBillLocal = By.xpath("//label[contains(text(),'Estimated Annual Billings') and not(contains(text(),'US$'))]//span[contains(@class,'info-sign')]");
		private By labelEstimatedAnnBillUS = By.xpath("//label[contains(text(),'Estimated Annual Billings(US$')]//span[contains(@class,'info-sign')]");
		
		
		private By labelEstimatedAnnRevLocal = By.xpath("//label[contains(text(),'Estimated Annual Revenue') and not(contains(text(),'US$'))]//span[contains(@class,'info-sign')]");		
		private By labelEstimatedAnnRevUS = By.xpath("//label[contains(text(),'Estimated Annual Revenue(US$')]//span[contains(@class,'info-sign')]");
		
		private By labelSourceOfOpportunity = By.xpath("//label[contains(text(),'Source Of Opportunity')]//span[contains(@class,'info-sign')]");
		private By labelGrowthLead = By.xpath("//label[contains(text(),'Growth Lead')]//span[contains(@class,'info-sign')]");
		
		
		
		private By lblGHCInfo = By.xpath("//label[contains(text(),'Global Holding Company')]");
		private By lblLevelOfOpportunityInfoIcon = By.xpath("//label[contains(text(),'Level Of Opportunity')]");
		private By lblDescriptionInfoIcon = By.xpath("//label[contains(text(),'Description')]");
		private By lblStatusInfoIcon = By.xpath("//label[contains(text(),'Status')]");
		private By lblEstimatedAnnBillLocal = By.xpath("//label[contains(text(),'Estimated Annual Billings') and not(contains(text(),'US$'))]");
		private By lblEstimatedAnnBillUS = By.xpath("//label[contains(text(),'Estimated Annual Billings(US$')]");
		
		
		private By lblEstimatedAnnRevLocal = By.xpath("//label[contains(text(),'Estimated Annual Revenue') and not(contains(text(),'US$'))]");		
		private By lblEstimatedAnnRevUS = By.xpath("//label[contains(text(),'Estimated Annual Revenue(US$')]");
		
		private By lblSourceOfOpportunity = By.xpath("//label[contains(text(),'Source Of Opportunity')]");
		private By lblGrowthLead = By.xpath("//label[contains(text(),'Growth Lead')]");
		
		
		
		
		
		private By tooltipGHC=By.xpath("//label[contains(text(),'Global Holding Company')]//div[contains(@class,'content-tooltip')]//p");
		private By tooltipLevelOfOpportunity=By.xpath("//label[contains(text(),'Level Of Opportunity')]//div[contains(@class,'content-tooltip')]//p");
		private By tooltipDescription=By.xpath("//label[contains(text(),'Description')]//div[contains(@class,'content-tooltip')]//p");
		private By tooltipStatus=By.xpath("//label[contains(text(),'Status')]//div[contains(@class,'content-tooltip')]//p");

		private By tooltipEstimatedAnnualBilling=By.xpath("//label[contains(text(),'Estimated Annual Billings') and not(contains(text(),'US$'))]//div[contains(@class,'content-tooltip')]//p");
		private By tooltipEstimatedAnnualBillingUS=By.xpath("//label[contains(text(),'Estimated Annual Billings(US$')]//div[contains(@class,'content-tooltip')]//p");
		
		private By tooltipEstimatedAnnualRevenue=By.xpath("//label[contains(text(),'Estimated Annual Revenue') and not(contains(text(),'US$'))]//div[contains(@class,'content-tooltip')]//p");
		private By tooltipEstimatedAnnualRevenueUS=By.xpath("//label[contains(text(),'Estimated Annual Revenue(US$')]//div[contains(@class,'content-tooltip')]//p");
		
		private By tooltipSourceOfOpportunity=By.xpath("//label[contains(text(),'Source Of Opportunity')]//div[contains(@class,'content-tooltip')]//p");
		private By tooltipGrowthLead=By.xpath("//label[contains(text(),'Growth Lead')]//div[contains(@class,'content-tooltip')]//p");
		
		
		private By textWonWithoutPitch=By.xpath("//input[contains(@value,'Won without a Pitch')]//following-sibling::p");
		private By textPitch=By.xpath("//input[contains(@value,'Pitch')]//following-sibling::p");
		private By textProspect=By.xpath("//input[contains(@value,'Prospect')]//following-sibling::p");
		private By cancelButton = By.xpath("//button[contains(text(),'Cancel')]");
		private By submitButton = By.xpath("//button[contains(text(),'Submit')]");
		private By submitAndAddButton = By.xpath("//button[contains(text(),'Submit & Add New')]");
		
		private By pitchGrid= By.xpath("//div[contains(@class,'container-grid-ui rebrand-pitch-grid')]");
		private By pitchGridColumn= By.xpath("//div[contains(@title,'GHC')]");
		private By loaderImage= By.xpath("//img[contains(@ng-src,'GM_Growth_Tracker_logo_CIRCULAR.svg')]");
		private By labelProspect = By.xpath("//label[contains(text(),'Prospect')]");
		private By inputTypeProspect=By.xpath("//input[starts-with(@value,'Prospect')]//following-sibling::label");
		private By inputTypeWonWithout=By.xpath("//input[starts-with(@value,'Won without a Pitch')]//following-sibling::label");
		private By inputTypePitch=By.xpath("//input[starts-with(@value,'Pitch')]//following-sibling::label");
		
		private By labelProspectLocal=By.xpath("//label[starts-with(text(),'Local') and contains(@for,'radio111')]");
		private By labelProspectCentral=By.xpath("//label[starts-with(text(),'Multi Market - Centrally Led') and contains(@for,'radio212')]");
		
		
		private By labelPitchLocal=By.xpath("//label[starts-with(text(),'Local / Multi Market - Locally Led') and contains(@for,'radio11')]");
		private By labelPitchCentral=By.xpath("//label[starts-with(text(),'Multi Market - Centrally Led') and contains(@for,'radio22')]");
		
		
		private By containerTypeOfOpportunity = By.xpath("//h4[starts-with(text(),' What type of opportunity would you like to add? ')]//following-sibling::div");
		private By input = By.xpath("//input[contains(@type,'radio')]");
		private By label = By.xpath("//label");
		private By labelLevelOfOpportunity = By.xpath("//h4[starts-with(text(),' What is the level of the opportunity? ')]");
		
		
		private By nextButton = By.xpath("//button[contains(text(),'Next')]");
		private By modalHeaderTitle = By.xpath("//div[contains(@class,'modal-header modal-new-pitch ')]/h4");
		private By modalCancelButton = By.xpath("//i[contains(@class,'bi-x')]");
		
		private By advertiserLinkGrid = By.xpath("//i[contains(@class,'bi-x')]");
		
		private By firstrowGrid = By.xpath("//a[contains(@ng-click,'grid.app')]");
		
		
		public AddPitchLocal_POM(WebDriver driver) {
			

			this.driver = driver;
			super.driver=driver;

		}

		public By getTextWonWithoutPitch() {
			return textWonWithoutPitch;
		}

		public By getTextProspect() {
			return textProspect;
		}

		public By getTextPitch() {
			return textPitch;
		}

		public By getCancelButton() {
			return cancelButton;
		}

		public By getPitchGrid() {
			return pitchGrid;
		}

		public By getPitchGridColumn() {
			return pitchGridColumn;
		}

		public By getLoaderImage() {
			return loaderImage;
		}

		public By getLabelProspect() {
			return labelProspect;
		}


		public By getContainerTypeOfOpportunity() {
			return containerTypeOfOpportunity;
		}

		public By getInput() {
			return input;
		}

		public By getLabel() {
			return label;
		}

		public By getInputTypeProspect() {
			return inputTypeProspect;
		}

		public By getInputTypePitch() {
			return inputTypePitch;
		}

		public By getInputTypeWonWithout() {
			return inputTypeWonWithout;
		}

		public By getLabelProspectLocal() {
			return labelProspectLocal;
		}

		public By getLabelProspectCentral() {
			return labelProspectCentral;
		}

		public By getLabelPitchLocal() {
			return labelPitchLocal;
		}

		public By getLabelPitchCentral() {
			return labelPitchCentral;
		}

		public By getLabelLevelOfOpportunity() {
			return labelLevelOfOpportunity;
		}

		public By getNextButton() {
			return nextButton;
		}

		public By getModalHeaderTitle() {
			return modalHeaderTitle;
		}

		public By getModalCancelButton() {
			return modalCancelButton;
		}

		public By getFirstrowGrid() {
			return firstrowGrid;
		}

		public By getLabelGHCInfoIcon() {
			return labelGHCInfoIcon;
		}

		public By getLabelLevelOfOpportunityInfoIcon() {
			return labelLevelOfOpportunityInfoIcon;
		}

		public By getTooltipGHC() {
			return tooltipGHC;
		}

		public By getLabelDescriptionInfoIcon() {
			return labelDescriptionInfoIcon;
		}

		public By getLabelStatusInfoIcon() {
			return labelStatusInfoIcon;
		}

		public By getLabelEstimatedAnnBillLocal() {
			return labelEstimatedAnnBillLocal;
		}

		public By getLabelEstimatedAnnBillUS() {
			return labelEstimatedAnnBillUS;
		}

		public By getLabelEstimatedAnnRevLocal() {
			return labelEstimatedAnnRevLocal;
		}

		public By getLabelEstimatedAnnRevUS() {
			return labelEstimatedAnnRevUS;
		}

		public By getLabelSourceOfOpportunity() {
			return labelSourceOfOpportunity;
		}

		public By getLabelGrowthLead() {
			return labelGrowthLead;
		}

		public By getTooltipLevelOfOpportunity() {
			return tooltipLevelOfOpportunity;
		}

		public By getTooltipDescription() {
			return tooltipDescription;
		}

		public By getTooltipStatus() {
			return tooltipStatus;
		}

		public By getTooltipEstimatedAnnualBilling() {
			return tooltipEstimatedAnnualBilling;
		}

		public By getTooltipEstimatedAnnualBillingUS() {
			return tooltipEstimatedAnnualBillingUS;
		}

		public By getTooltipEstimatedAnnualRevenue() {
			return tooltipEstimatedAnnualRevenue;
		}

		public By getTooltipEstimatedAnnualRevenueUS() {
			return tooltipEstimatedAnnualRevenueUS;
		}

		public By getTooltipSourceOfOpportunity() {
			return tooltipSourceOfOpportunity;
		}

		public By getTooltipGrowthLead() {
			return tooltipGrowthLead;
		}

		public By getLblGHCInfo() {
			return lblGHCInfo;
		}

		public By getLblLevelOfOpportunityInfoIcon() {
			return lblLevelOfOpportunityInfoIcon;
		}

		public By getLblDescriptionInfoIcon() {
			return lblDescriptionInfoIcon;
		}

		public By getLblStatusInfoIcon() {
			return lblStatusInfoIcon;
		}

		public By getLblEstimatedAnnBillLocal() {
			return lblEstimatedAnnBillLocal;
		}

		public By getLblEstimatedAnnBillUS() {
			return lblEstimatedAnnBillUS;
		}

		public By getLblEstimatedAnnRevLocal() {
			return lblEstimatedAnnRevLocal;
		}

		public By getLblEstimatedAnnRevUS() {
			return lblEstimatedAnnRevUS;
		}

		public By getLblSourceOfOpportunity() {
			return lblSourceOfOpportunity;
		}

		public By getLblGrowthLead() {
			return lblGrowthLead;
		}

		public By getInputCountry() {
			return inputCountry;
		}

		public By getBtnCountry() {
			return btnCountry;
		}

		public By getBtnCity() {
			return btnCity;
		}

		public By getInputCity() {
			return inputCity;
		}

		public By getBtnAgency() {
			return btnAgency;
		}

		public By getInputAgency() {
			return inputAgency;
		}

		public By getBtnLevel() {
			return btnLevel;
		}

		public By getInputLevel() {
			return inputLevel;
		}

		public By getBtnRelationship() {
			return btnRelationship;
		}

		public By getInputRelationship() {
			return inputRelationship;
		}

		public By getBtnIndustry() {
			return btnIndustry;
		}

		public By getInputIndustry() {
			return inputIndustry;
		}

		public By getBtnSubIndustry() {
			return btnSubIndustry;
		}

		public By getInputSubIndustry() {
			return inputSubIndustry;
		}

		public By getInputDateInvited() {
			return inputDateInvited;
		}

		public By getBtnStatus() {
			return btnStatus;
		}

		public By getInputStatus() {
			return inputStatus;
		}


		public By getLabelScope() {
			return labelScope;
		}

		public By getlabelScopedropdownoptions() {
			return labelScopedropdownoptions;
		}

		public By getInputDateFinalPresentation() {
			return inputDateFinalPresentation;
		}

		public By getBtnSourceBilling() {
			return btnSourceBilling;
		}

		public By getInputSourceBilling() {
			return inputSourceBilling;
		}

		public By getBtnSourceOpportunity() {
			return btnSourceOpportunity;
		}

		public By getInputSourceOpportunity() {
			return inputSourceOpportunity;
		}

		public By getLabelScopeCount() {
			return labelScopeCount;
		}

		public By getRadioBtnWPPOpenYes() {
			return radioBtnWPPOpenYes;
		}

		public By getDateFinalPresentation() {
			return dateFinalPresentation;
		}

		public By getDateDateInvited() {
			return dateDateInvited;
		}

		public By getInputGHC() {
			return inputGHC;
		}

		public By getSelectGHC() {
			return selectGHC;
		}

		public By getAddButtonModalBox() {
			return addButtonModalBox;
		}

		public By getInputAdvertiser() {
			return inputAdvertiser;
		}

		public By getLabelB2B() {
			return labelB2B;
		}

		public By getLabelB2BAll() {
			return labelB2BAll;
		}

		public By getLabelB2BSome() {
			return labelB2BSome;
		}

		public By getInputtextDescription() {
			return inputtextDescription;
		}

		

		public By getInputEstimatedAnnualBillingLocal() {
			return inputEstimatedAnnualBillingLocal;
		}

		public By getInputAuditor() {
			return inputAuditor;
		}

		public By getInputGrowthLead() {
			return inputGrowthLead;
		}

		public By getInputAccountLead() {
			return inputAccountLead;
		}

		public By getInputDateContractStarts() {
			return inputDateContractStarts;
		}

		public By getDateContractStarts() {
			return dateContractStarts;
		}

		public By getLabelWPPAgency() {
			return labelWPPAgency;
		}

		public By getLabelWPPDropdownoptions() {
			return labelWPPDropdownoptions;
		}

		public By getInputtextNextSteps() {
			return inputtextNextSteps;
		}

		public By getSubmitButton() {
			return submitButton;
		}

		public By getSubmitAndAddButton() {
			return submitAndAddButton;
		}

		public By getActualTextcountryfield() {
			return actualTextcountryfield;
		}

		
		

}
