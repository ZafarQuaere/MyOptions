package com.optiontown.app.model.selectproduct;

import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.login.LoginDataUpdateProfile;
import com.optiontown.app.model.login.SelectedUserData;
import com.optiontown.app.model.redeem.AddPaxIdentityData;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.utosearchresult.BoostMypriority;
import com.optiontown.app.model.segproduct.SelectedDateData;

/**
 * Created by amit on 07-06-2016.
 */
public class FragmentCommunicationData
{
    public BoostMyPrioritySelectedData getBoostMyPrioritySelectedData() {
        return boostMyPrioritySelectedData;
    }

    public void setBoostMyPrioritySelectedData(BoostMyPrioritySelectedData boostMyPrioritySelectedData) {
        this.boostMyPrioritySelectedData = boostMyPrioritySelectedData;
    }

    private BoostMyPrioritySelectedData boostMyPrioritySelectedData;
    private boolean isFilterApply;
    private boolean isShowAll;
    private String fragmentName;
    private boolean showHelp;
    private String help;
    private String selectedAirline;
    private String selectedTravelZone;
    private String selectedCabin;
    private String selectedPassenger;
    private String selectedCredit;
    private String selectedTravelPeriod;
    private String selectedAdvanceBooking;
    private String selectedFlexibility;
    private String selectedFMMatrixTag;
    private int selectedFMMatrixId;
    private FlightPassDealData flightPassDealData;
    private LoginData loginData;
    private LoginDataUpdateProfile loginDataUpdateProfile;
    private boolean isAirlineChanged;
    private SelectedUserData selectedUserData;
    private int AddMeAsFirstUserId;
    private boolean callBuy;
    private boolean isCallReviewAPI = false;
    private Itinerarry itinerarry;
    private String errorMessage;

    private int passIndex;
    private int cmmIndex;
    private String searchErrorMsg;
    private Boolean isModifyPassData = false;
    private AddPaxIdentityData addPaxCompleteData;
    private boolean flagRollbackAddPaxUI = false;
    private boolean isFromReview = false;

    public boolean isFromReview() {
        return isFromReview;
    }

    public void setFromReview(boolean fromReview) {
        isFromReview = fromReview;
    }

    public boolean isFlagRollbackAddPaxUI() {
        return flagRollbackAddPaxUI;
    }

    public void setFlagRollbackAddPaxUI(boolean flagRollbackAddPaxUI) {
        this.flagRollbackAddPaxUI = flagRollbackAddPaxUI;
    }

    public AddPaxIdentityData getAddPaxCompleteData() {
        return addPaxCompleteData;
    }

    public void setAddPaxCompleteData(AddPaxIdentityData addPaxCompleteData) {
        this.addPaxCompleteData = addPaxCompleteData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSearchError() {
        return isSearchError;
    }

    public void setSearchError(boolean searchError) {
        isSearchError = searchError;
    }

    private boolean isSearchError;

    public String getSearchErrorMsg() {
        return searchErrorMsg;
    }

    public void setSearchErrorMsg(String searchErrorMsg) {
        this.searchErrorMsg = searchErrorMsg;
    }




    public Itinerarry getItinerarry() {
        return itinerarry;
    }

    public void setItinerarry(Itinerarry itinerarry) {
        this.itinerarry = itinerarry;
    }

    public int getPassIndex() {
        return passIndex;
    }

    public void setPassIndex(int passIndex) {
        this.passIndex = passIndex;
    }

    public int getCmmIndex() {
        return cmmIndex;
    }

    public void setCmmIndex(int cmmIndex) {
        this.cmmIndex = cmmIndex;
    }

    public LoginDataUpdateProfile getLoginDataUpdateProfile() {
        return loginDataUpdateProfile;
    }

    public void setLoginDataUpdateProfile(LoginDataUpdateProfile loginDataUpdateProfile) {
        this.loginDataUpdateProfile = loginDataUpdateProfile;
    }

    public boolean isCallReviewAPI() {
        return isCallReviewAPI;
    }

    public void setCallReviewAPI(boolean callReviewAPI) {
        isCallReviewAPI = callReviewAPI;
    }

    public boolean isCallBuy() {
        return callBuy;
    }

    public void setCallBuy(boolean callBuy) {
        this.callBuy = callBuy;
    }

    public int getAddMeAsFirstUserId() {
        return AddMeAsFirstUserId;
    }

    public void setAddMeAsFirstUserId(int addMeAsFirstUserId) {
        AddMeAsFirstUserId = addMeAsFirstUserId;
    }

    public boolean isAirlineChanged() {
        return isAirlineChanged;
    }

    public void setAirlineChanged(boolean airlineChanged) {
        isAirlineChanged = airlineChanged;
    }

    public SelectedUserData getSelectedUserData() {
        return selectedUserData;
    }

    public void setSelectedUserData(SelectedUserData selectedUserData) {
        this.selectedUserData = selectedUserData;
    }

    public boolean isFilterApply() {
        return isFilterApply;
    }

    public void setFilterApply(boolean filterApply) {
        isFilterApply = filterApply;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public FlightPassDealData getFlightPassDealData() {
        return flightPassDealData;
    }

    public void setFlightPassDealData(FlightPassDealData flightPassDealData) {
        this.flightPassDealData = flightPassDealData;
    }

    public String getSelectedCabin() {
        return selectedCabin;
    }

    public void setSelectedCabin(String selectedCabin) {
        this.selectedCabin = selectedCabin;
    }

    public String getSelectedCredit() {
        return selectedCredit;
    }

    public void setSelectedCredit(String selectedCredit) {
        this.selectedCredit = selectedCredit;
    }

    public String getSelectedPassenger() {
        return selectedPassenger;
    }

    public void setSelectedPassenger(String selectedPassenger) {
        this.selectedPassenger = selectedPassenger;
    }

    public String getSelectedAirline() {
        return selectedAirline;
    }

    public void setSelectedAirline(String selectedAirline) {
        this.selectedAirline = selectedAirline;
    }

    public String getSelectedFlexibility() {
        return selectedFlexibility;
    }

    public void setSelectedFlexibility(String selectedFlexibility) {
        this.selectedFlexibility = selectedFlexibility;
    }

    public String getSelectedFMMatrixTag() {
        return selectedFMMatrixTag;
    }

    public void setSelectedFMMatrixTag(String selectedFMMatrixTag) {
        this.selectedFMMatrixTag = selectedFMMatrixTag;
    }

    public int getSelectedFMMatrixId() {
        return selectedFMMatrixId;
    }

    public void setSelectedFMMatrixId(int selectedFMMatrixId) {
        this.selectedFMMatrixId = selectedFMMatrixId;
    }

    public String getSelectedTravelPeriod() {
        return selectedTravelPeriod;
    }

    public void setSelectedTravelPeriod(String selectedTravelPeriod) {
        this.selectedTravelPeriod = selectedTravelPeriod;
    }

    public String getSelectedAdvanceBooking() {
        return selectedAdvanceBooking;
    }

    public void setSelectedAdvanceBooking(String selectedAdvanceBooking) {
        this.selectedAdvanceBooking = selectedAdvanceBooking;
    }

    public String getSelectedTravelZone() {
        return selectedTravelZone;
    }

    public void setSelectedTravelZone(String selectedTravelZone) {
        this.selectedTravelZone = selectedTravelZone;
    }

    public boolean isShowAll() {
        return isShowAll;
    }

    public void setShowAll(boolean showAll) {
        isShowAll = showAll;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public void setShowHelp(boolean showHelp) {
        this.showHelp = showHelp;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public void setRedeemModifyPassDetails(boolean isModifyPassData) {
        this.isModifyPassData = isModifyPassData;
    }

    public boolean getRedeemModifyPassDetails() {
       return isModifyPassData;
    }

    private SelectedDateData selectedDateData;

    public SelectedDateData getSelectedDateData() {
        return selectedDateData;
    }

    public void setSelectedDateData(SelectedDateData selectedDateData) {
        this.selectedDateData = selectedDateData;
    }
}
