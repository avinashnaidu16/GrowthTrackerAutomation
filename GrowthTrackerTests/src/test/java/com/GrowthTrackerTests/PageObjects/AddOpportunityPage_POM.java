package com.GrowthTrackerTests.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GrowthTrackerTests.Base.CommonToAllTests;
public class AddOpportunityPage_POM extends CommonToAllTests
{
	

		WebDriver driver;

		private By textWonWithoutPitch=By.xpath("//input[contains(@value,'Won without a Pitch')]//following-sibling::p");
		private By textPitch=By.xpath("//input[contains(@value,'Pitch')]//following-sibling::p");
		private By textProspect=By.xpath("//input[contains(@value,'Prospect')]//following-sibling::p");
		private By cancelButton = By.xpath("//button[contains(text(),'Cancel')]");
		
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
		
		
		private By firstrowGrid = By.xpath("//a[contains(@ng-click,'grid.app')]");
		
		
		public AddOpportunityPage_POM(WebDriver driver) {
			

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
		
		

}
