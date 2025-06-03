package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class GrowthTrackerFormPageObjects_POM extends CommonToAllTests
{

	WebDriver driver;


	private By toastMessage= By.xpath("//div[contains(@class,'p-toast-top-right')]");
	private By AddNewPRFButton = By.xpath("//button[contains(text(),'Add New PRF')]");
	private By ExitButton = By.xpath("//button/span[contains(text(),'Exit')]");
	private By Step1ClientParentCompany = By.xpath("//input[contains(@placeholder,'Select client parent')]");
	private By Step1ClientParentCompanySelect = By.xpath("//ul[@role='listbox']/li[1]");
	private By Step1ClientDisplayName = By.xpath("//input[contains(@placeholder,'enter client display')]");
	private By Step1TermPeriodCalendar = By.xpath("//label[contains(text(),'Calender')]");
	private By Step1TermPeriodCalendarRadioBtn = By.xpath("//p-radiobutton[contains(@inputid,'radio-1')]//div//div[2]");
	private By Step1TermPeriodFiscalRadioBtn = By.xpath("//p-radiobutton[contains(@inputid,'radio-2')]//div//div[2]");
	private By Step1TermPeriodFiscal = By.xpath("//label[contains(text(),'Fiscal')]");
	private By Step1ClientDisplayPopupAddNew = By.xpath("//button[contains(text(),'Add a new name')]");
	private By Step1TermStartDate = By.xpath("//input[contains(@name,'termStartDate')]");
	private By Step1TermEndDate = By.xpath("//input[contains(@name,'termEndDate')]");
	private By Step1TermFinalizeDate = By.xpath("//input[contains(@name,'expectedFinalizeDate')]");
	private By Step1expectedFinalizeComment = By.xpath("//textarea[contains(@name,'expectedFinalizeComment')]");
	private By Step1revenueRecognitionDate = By.xpath("//input[contains(@name,'revenueRecognitionDate')]");
	private By Step1revenueRecognitionComment = By.xpath("//textarea[contains(@name,'revenueRecognitionComment')]");
	private By Step1revenuebookingtype = By.xpath("//span[contains(text(),'Select revenue booking type')]");
	private By Step1revenuebookingtypeforHexCode = By.xpath("//div[contains(@class,'p-component') and contains(@class,'p-dropdown' )]");
	
	//
	private By Step1revenuebookingtypeList = By.xpath("//ul[contains(@role,'listbox')]");
	private By Step1SaveAndNextBtn = By.xpath("//button[contains(text(),'Save & Next')]");
	private By TopLineLogoImage= By.xpath("//img[contains(@class,'logo-img')]");
	private By TopLineLogoTextPerformanceRelated= By.xpath("//span[contains(@class,'desktop-logo-text')]");
	private By TopLineLogoTextFeesTracker= By.xpath("//span[contains(@class,'display-block')]");
	private By TopLineDashboard= By.linkText("Dashboard");
	private By TopLineUsers= By.linkText("Users");
	private By TopLineClients= By.linkText("Clients");
	private By TopLinePRFs= By.linkText("PRFs");
	private By TopLineContent= By.linkText("Content");
	private By TopLineScenarios= By.linkText("Scenarios");
	private By TopLineNotificationBellIcon= By.xpath("//div[contains(@id,'desktopIcon')]//span//i[contains(@class,'pi-bell')]");
	private By TopLineUserInfoIcon= By.xpath("//div[contains(@id,'desktopIcon')]//div//span[contains(@class,'user-initials')]");
	//ul[contains(@role,'listbox')]
	private By UnsavedChangesPopup= By.xpath("//div[contains(@role,'dialog')]");
	private By UnsavedChangesPopupCancelBtn= By.xpath("//div[contains(@role,'dialog')]//button[contains(text(),'Cancel')]");
	private By UnsavedChangesPopupYesBtn= By.xpath("//div[contains(@role,'dialog')]//button[contains(text(),'Yes')]");
	private By UnsavedChangesPopupNoBtn= By.xpath("//div[contains(@role,'dialog')]//button[contains(text(),'No')]");
	private By RequiredFieldLabel= By.xpath("//div[contains(text(),'Required Field')]");
	
	
	private By LeftNavigationStep1= By.xpath("//span[contains(@class,'timeline')]/span[contains(text(),'01')]");
	private By LeftNavigationStep2= By.xpath("//span[contains(@class,'timeline')]/span[contains(text(),'02')]");
	private By LeftNavigationStep3= By.xpath("//span[contains(@class,'timeline')]/span[contains(text(),'03')]");
	private By LeftNavigationStep4= By.xpath("//span[contains(@class,'timeline')]/span[contains(text(),'04')]");
	
	private By BreadCrumbDashboard= By.xpath("//div[contains(@class,'breadcrumb')]/a[contains(text(),'Dashboard')]");
	private By BreadCrumbPRFs= By.xpath("//div[contains(@class,'breadcrumb')]/a[contains(text(),'PRFs')]");
	
	
	//
	
	private By PRFGrid= By.xpath("//div[contains(@class,'custom-grid prf-grid')]");
	
	
	public By getStep1revenuebookingtypeList() 
	{
		return Step1revenuebookingtypeList;
	}



	public void setStep1revenuebookingtypeList(By step1revenuebookingtypeList) {
		Step1revenuebookingtypeList = step1revenuebookingtypeList;
	}



	private By Step1revenuebookingtypeGlobal = By.xpath("//li//span[contains(text(),'Global')]");
	private By Step1revenuebookingtypeRegional = By.xpath("//li//span[contains(text(),'Regional')]");
	private By Step1revenuebookingtypeLocal = By.xpath("//li//span[contains(text(),'Local')]");
	public By getStep1revenuebookingtypeGlobal() {
		return Step1revenuebookingtypeGlobal;
	}



	public void setStep1revenuebookingtypeGlobal(By step1revenuebookingtypeGlobal) {
		Step1revenuebookingtypeGlobal = step1revenuebookingtypeGlobal;
	}



	public By getStep1revenuebookingtypeRegional() {
		return Step1revenuebookingtypeRegional;
	}



	public void setStep1revenuebookingtypeRegional(By step1revenuebookingtypeRegional) {
		Step1revenuebookingtypeRegional = step1revenuebookingtypeRegional;
	}



	public By getStep1revenuebookingtypeLocal() {
		return Step1revenuebookingtypeLocal;
	}



	public void setStep1revenuebookingtypeLocal(By step1revenuebookingtypeLocal) {
		Step1revenuebookingtypeLocal = step1revenuebookingtypeLocal;
	}



	public WebDriver getDriver() {
		return driver;
	}



	private By Step1bookingComment = By.xpath("//textarea[contains(@name,'bookingComment')]");
	
	public By getStep1ClientParentCompanySelect() {
		return Step1ClientParentCompanySelect;
	}



	public By getStep1TermStartDate() {
		return Step1TermStartDate;
	}



	public By getStep1TermEndDate() {
		return Step1TermEndDate;
	}



	public By getStep1TermFinalizeDate() {
		return Step1TermFinalizeDate;
	}



	public By getStep1expectedFinalizeComment() {
		return Step1expectedFinalizeComment;
	}



	public By getStep1revenueRecognitionDate() {
		return Step1revenueRecognitionDate;
	}



	public By getStep1revenueRecognitionComment() {
		return Step1revenueRecognitionComment;
	}



	public By getStep1revenuebookingtype() {
		return Step1revenuebookingtype;
	}



	public By getStep1bookingComment() {
		return Step1bookingComment;
	}



	public void setStep1ClientParentCompanySelect(By step1ClientParentCompanySelect) {
		Step1ClientParentCompanySelect = step1ClientParentCompanySelect;
	}	
    

	public By getStep1TermPeriodCalendar() {
		return Step1TermPeriodCalendar;
	}



	public void setStep1TermPeriodCalendar(By step1TermPeriodCalendar) {
		Step1TermPeriodCalendar = step1TermPeriodCalendar;
	}



	public By getStep1TermPeriodFiscal() {
		return Step1TermPeriodFiscal;
	}



	public void setStep1TermPeriodFiscal(By step1TermPeriodFiscal) {
		Step1TermPeriodFiscal = step1TermPeriodFiscal;
	}



	public By getStep1ClientDisplayPopupAddNew() {
		return Step1ClientDisplayPopupAddNew;
	}



	public void setStep1ClientDisplayPopupAddNew(By step1ClientDisplayPopupAddNew) {
		Step1ClientDisplayPopupAddNew = step1ClientDisplayPopupAddNew;
	}



	public By getStep1ClientParentCompany() {
		return Step1ClientParentCompany;
	}



	public void setStep1ClientParentCompany(By step1ClientParentCompany) {
		Step1ClientParentCompany = step1ClientParentCompany;
	}



	public By getStep1ClientDisplayName() {
		return Step1ClientDisplayName;
	}



	public void setStep1ClientDisplayName(By step1ClientDisplayName) {
		Step1ClientDisplayName = step1ClientDisplayName;
	}



	public GrowthTrackerFormPageObjects_POM(WebDriver driver) {
		

		this.driver = driver;
		super.driver=driver;

	}



	public By getAddNewPRFButton() {
		return AddNewPRFButton;
	}



	public By getStep1SaveAndNextBtn() {
		return Step1SaveAndNextBtn;
	}



	public void setStep1SaveAndNextBtn(By step1SaveAndNextBtn) {
		Step1SaveAndNextBtn = step1SaveAndNextBtn;
	}



	public By getStep1TermPeriodCalendarRadioBtn() {
		return Step1TermPeriodCalendarRadioBtn;
	}



	public void setStep1TermPeriodCalendarRadioBtn(By step1TermPeriodCalendarRadioBtn) {
		Step1TermPeriodCalendarRadioBtn = step1TermPeriodCalendarRadioBtn;
	}



	public By getStep1TermPeriodFiscalRadioBtn() {
		return Step1TermPeriodFiscalRadioBtn;
	}



	public void setStep1TermPeriodFiscalRadioBtn(By step1TermPeriodFiscalRadioBtn) {
		Step1TermPeriodFiscalRadioBtn = step1TermPeriodFiscalRadioBtn;
	}



	public By getTopLineLogoImage() {
		return TopLineLogoImage;
	}



	public void setTopLineLogoImage(By topLineLogoImage) {
		TopLineLogoImage = topLineLogoImage;
	}



	public By getTopLineLogoTextPerformanceRelated() {
		return TopLineLogoTextPerformanceRelated;
	}



	public void setTopLineLogoTextPerformanceRelated(By topLineLogoTextPerformanceRelated) {
		TopLineLogoTextPerformanceRelated = topLineLogoTextPerformanceRelated;
	}



	public By getTopLineLogoTextFeesTracker() {
		return TopLineLogoTextFeesTracker;
	}



	public void setTopLineLogoTextFeesTracker(By topLineLogoTextFeesTracker) {
		TopLineLogoTextFeesTracker = topLineLogoTextFeesTracker;
	}



	public By getTopLineDashboard() {
		return TopLineDashboard;
	}



	public void setTopLineDashboard(By topLineDashboard) {
		TopLineDashboard = topLineDashboard;
	}



	public By getTopLineUsers() {
		return TopLineUsers;
	}



	public void setTopLineUsers(By topLineUsers) {
		TopLineUsers = topLineUsers;
	}



	public By getTopLineClients() {
		return TopLineClients;
	}



	public void setTopLineClients(By topLineClients) {
		TopLineClients = topLineClients;
	}



	public By getTopLinePRFs() {
		return TopLinePRFs;
	}



	public void setTopLinePRFs(By topLinePRFs) {
		TopLinePRFs = topLinePRFs;
	}



	public By getTopLineContent() {
		return TopLineContent;
	}



	public void setTopLineContent(By topLineContent) {
		TopLineContent = topLineContent;
	}



	public By getTopLineScenarios() {
		return TopLineScenarios;
	}



	public void setTopLineScenarios(By topLineScenarios) {
		TopLineScenarios = topLineScenarios;
	}



	public By getTopLineNotificationBellIcon() {
		return TopLineNotificationBellIcon;
	}



	public void setTopLineNotificationBellIcon(By topLineNotificationBellIcon) {
		TopLineNotificationBellIcon = topLineNotificationBellIcon;
	}



	public By getTopLineUserInfoIcon() {
		return TopLineUserInfoIcon;
	}



	public void setTopLineUserInfoIcon(By topLineNotificationUserIcon) {
		TopLineUserInfoIcon = topLineNotificationUserIcon;
	}



	public By getUnsavedChangesPopup() {
		return UnsavedChangesPopup;
	}



	public void setUnsavedChangesPopup(By unsavedChangesPopup) {
		UnsavedChangesPopup = unsavedChangesPopup;
	}



	public By getUnsavedChangesPopupCancelBtn() {
		return UnsavedChangesPopupCancelBtn;
	}



	public void setUnsavedChangesPopupCancelBtn(By unsavedChangesPopupCancelBtn) {
		UnsavedChangesPopupCancelBtn = unsavedChangesPopupCancelBtn;
	}



	public By getUnsavedChangesPopupYesBtn() {
		return UnsavedChangesPopupYesBtn;
	}



	public void setUnsavedChangesPopupYesBtn(By unsavedChangesPopupYesBtn) {
		UnsavedChangesPopupYesBtn = unsavedChangesPopupYesBtn;
	}



	public By getUnsavedChangesPopupNoBtn() {
		return UnsavedChangesPopupNoBtn;
	}



	public void setUnsavedChangesPopupNoBtn(By unsavedChangesPopupNoBtn) {
		UnsavedChangesPopupNoBtn = unsavedChangesPopupNoBtn;
	}



	public By getStep1revenuebookingtypeforHexCode() {
		return Step1revenuebookingtypeforHexCode;
	}



	public void setStep1revenuebookingtypeforHexCode(By step1revenuebookingtypeforHexCode) {
		Step1revenuebookingtypeforHexCode = step1revenuebookingtypeforHexCode;
	}



	public By getRequiredFieldLabel() {
		return RequiredFieldLabel;
	}



	public void setRequiredFieldLabel(By requiredFieldLabel) {
		RequiredFieldLabel = requiredFieldLabel;
	}



	public By getLeftNavigationStep1() {
		return LeftNavigationStep1;
	}



	public void setLeftNavigationStep1(By leftNavigationStep1) {
		LeftNavigationStep1 = leftNavigationStep1;
	}



	public By getLeftNavigationStep2() {
		return LeftNavigationStep2;
	}



	public By getLeftNavigationStep4() {
		return LeftNavigationStep4;
	}



	public By getLeftNavigationStep3() {
		return LeftNavigationStep3;
	}



	public By getBreadCrumbDashboard() {
		return BreadCrumbDashboard;
	}



	public By getBreadCrumbPRFs() {
		return BreadCrumbPRFs;
	}



	public By getToastMessage() {
		return toastMessage;
	}



	public By getExitButton() {
		return ExitButton;
	}



	public By getPRFGrid() {
		return PRFGrid;
	}


	
}
