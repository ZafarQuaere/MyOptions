package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 07-09-2016.
 */
public class RedeemSearchResultData implements Serializable
{
    private String FFpnumberHelpMessage;

    public String getFFpnumberHelpMessage() { return this.FFpnumberHelpMessage; }

    public void setFFpnumberHelpMessage(String FFpnumberHelpMessage) { this.FFpnumberHelpMessage = FFpnumberHelpMessage; }

    private String FFpnumberErrorMessage;

    public String getFFpnumberErrorMessage() { return this.FFpnumberErrorMessage; }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) { this.FFpnumberErrorMessage = FFpnumberErrorMessage; }

    private int FFPNumberMandatory;

    public int getFFPNumberMandatory() { return this.FFPNumberMandatory; }

    public void setFFPNumberMandatory(int FFPNumberMandatory) { this.FFPNumberMandatory = FFPNumberMandatory; }

    private int isDisplayFFPNumber;

    public int getIsDisplayFFPNumber() { return this.isDisplayFFPNumber; }

    public void setIsDisplayFFPNumber(int isDisplayFFPNumber) { this.isDisplayFFPNumber = isDisplayFFPNumber; }

    private String iata_Tour_Code;
    private String iata_Registration_Code;

    public String getIata_Tour_Code() {
        return iata_Tour_Code;
    }

    public void setIata_Tour_Code(String iata_Tour_Code) {
        this.iata_Tour_Code = iata_Tour_Code;
    }

    public String getIata_Registration_Code() {
        return iata_Registration_Code;
    }

    public void setIata_Registration_Code(String iata_Registration_Code) {
        this.iata_Registration_Code = iata_Registration_Code;
    }

    private String iata_Tour_Code_Label;
    private String iata_Link_Label;
    private String iata_registration;
    private String iata_Hide_Label;

    public String getIata_Tour_Code_Label() {
        return iata_Tour_Code_Label;
    }

    public void setIata_Tour_Code_Label(String iata_Tour_Code_Label) {
        this.iata_Tour_Code_Label = iata_Tour_Code_Label;
    }

    public String getIata_Link_Label() {
        return iata_Link_Label;
    }

    public void setIata_Link_Label(String iata_Link_Label) {
        this.iata_Link_Label = iata_Link_Label;
    }

    public String getIata_registration() {
        return iata_registration;
    }

    public void setIata_registration(String iata_registration) {
        this.iata_registration = iata_registration;
    }

    public String getIata_Hide_Label() {
        return iata_Hide_Label;
    }

    public void setIata_Hide_Label(String iata_Hide_Label) {
        this.iata_Hide_Label = iata_Hide_Label;
    }

    private int iata_Display;

    public int getIata_Display() {
        return iata_Display;
    }

    public void setIata_Display(int iata_Display) {
        this.iata_Display = iata_Display;
    }


    private int FlexibilityRangeHours;

    public int getFlexibilityRangeHours() {
        return FlexibilityRangeHours;
    }

    public void setFlexibilityRangeHours(int flexibilityRangeHours) {
        FlexibilityRangeHours = flexibilityRangeHours;
    }

    private String Per_Person;

    public String getPer_Person() {
        return Per_Person;
    }

    public void setPer_Person(String per_Person) {
        Per_Person = per_Person;
    }

    private boolean isBAairline;

    public boolean isBAairline() {
        return isBAairline;
    }

    public void setBAairline(boolean BAairline) {
        isBAairline = BAairline;
    }



    private int Max_Passengers;

    public int getMax_Passengers() {
        return Max_Passengers;
    }

    public void setMax_Passengers(int max_Passengers) {
        Max_Passengers = max_Passengers;
    }

    private String Proceed_To_Payment_Label;

    public String getProceed_To_Payment_Label() {
        return Proceed_To_Payment_Label;
    }

    public void setProceed_To_Payment_Label(String proceed_To_Payment_Label) {
        Proceed_To_Payment_Label = proceed_To_Payment_Label;
    }

    private String Currency_Code;

    public String getCurrency_Code() {
        return Currency_Code;
    }

    public void setCurrency_Code(String currency_Code) {
        Currency_Code = currency_Code;
    }

    private boolean EMI_Flag;

    public boolean isEMI_Flag() {
        return EMI_Flag;
    }

    public void setEMI_Flag(boolean EMI_Flag) {
        this.EMI_Flag = EMI_Flag;
    }

    private int Agreed_Price;
    private int Book_Amount_Paid;
    private int BookFlight_TaxToPay;

    private String Payment_Installment_Label;
    private String Pass_Total_Price_Label;
    private String Total_Paid_Disc_Label;
    private String Installment_To_Pay_Label;
    private String Installment_To_Pay_Disc_Label;

    public int getAgreed_Price() {
        return Agreed_Price;
    }

    public void setAgreed_Price(int agreed_Price) {
        Agreed_Price = agreed_Price;
    }

    public int getBook_Amount_Paid() {
        return Book_Amount_Paid;
    }

    public void setBook_Amount_Paid(int book_Amount_Paid) {
        Book_Amount_Paid = book_Amount_Paid;
    }

    public int getBookFlight_TaxToPay() {
        return BookFlight_TaxToPay;
    }

    public void setBookFlight_TaxToPay(int bookFlight_TaxToPay) {
        BookFlight_TaxToPay = bookFlight_TaxToPay;
    }

    public String getPayment_Installment_Label() {
        return Payment_Installment_Label;
    }

    public void setPayment_Installment_Label(String payment_Installment_Label) {
        Payment_Installment_Label = payment_Installment_Label;
    }

    public String getPass_Total_Price_Label() {
        return Pass_Total_Price_Label;
    }

    public void setPass_Total_Price_Label(String pass_Total_Price_Label) {
        Pass_Total_Price_Label = pass_Total_Price_Label;
    }

    public String getTotal_Paid_Disc_Label() {
        return Total_Paid_Disc_Label;
    }

    public void setTotal_Paid_Disc_Label(String total_Paid_Disc_Label) {
        Total_Paid_Disc_Label = total_Paid_Disc_Label;
    }

    public String getInstallment_To_Pay_Label() {
        return Installment_To_Pay_Label;
    }

    public void setInstallment_To_Pay_Label(String installment_To_Pay_Label) {
        Installment_To_Pay_Label = installment_To_Pay_Label;
    }

    public String getInstallment_To_Pay_Disc_Label() {
        return Installment_To_Pay_Disc_Label;
    }

    public void setInstallment_To_Pay_Disc_Label(String installment_To_Pay_Disc_Label) {
        Installment_To_Pay_Disc_Label = installment_To_Pay_Disc_Label;
    }

    private String Transaction_History_Heading_Label;

    public String getTransaction_History_Heading_Label() {
        return Transaction_History_Heading_Label;
    }

    public void setTransaction_History_Heading_Label(String transaction_History_Heading_Label) {
        Transaction_History_Heading_Label = transaction_History_Heading_Label;
    }

    private String Transaction_Date_Label;
    private String Transaction_History_Event_Description_Label;
    private String Transaction_Event_Description_Label;
    private String Transaction_History_Total_Amount_FPO_Label;
    private String Tansaction_History_Account_Heading_Label;

    public String getTransaction_Date_Label() {
        return Transaction_Date_Label;
    }

    public void setTransaction_Date_Label(String transaction_Date_Label) {
        Transaction_Date_Label = transaction_Date_Label;
    }

    public String getTransaction_History_Event_Description_Label() {
        return Transaction_History_Event_Description_Label;
    }

    public void setTransaction_History_Event_Description_Label(String transaction_History_Event_Description_Label) {
        Transaction_History_Event_Description_Label = transaction_History_Event_Description_Label;
    }

    public String getTransaction_Event_Description_Label() {
        return Transaction_Event_Description_Label;
    }

    public void setTransaction_Event_Description_Label(String transaction_Event_Description_Label) {
        Transaction_Event_Description_Label = transaction_Event_Description_Label;
    }

    public String getTransaction_History_Total_Amount_FPO_Label() {
        return Transaction_History_Total_Amount_FPO_Label;
    }

    public void setTransaction_History_Total_Amount_FPO_Label(String transaction_History_Total_Amount_FPO_Label) {
        Transaction_History_Total_Amount_FPO_Label = transaction_History_Total_Amount_FPO_Label;
    }

    public String getTansaction_History_Account_Heading_Label() {
        return Tansaction_History_Account_Heading_Label;
    }

    public void setTansaction_History_Account_Heading_Label(String tansaction_History_Account_Heading_Label) {
        Tansaction_History_Account_Heading_Label = tansaction_History_Account_Heading_Label;
    }

    private ArrayList<TransactionHistoryDetail> Transaction_History_Detail;

    public ArrayList<TransactionHistoryDetail> getTransaction_History_Detail() {
        return Transaction_History_Detail;
    }

    public void setTransaction_History_Detail(ArrayList<TransactionHistoryDetail> transaction_History_Detail) {
        Transaction_History_Detail = transaction_History_Detail;
    }

    private String Confirmation_number;
    private String Optiontown_Flight_Pass_ID;
    private String Airline_Name;
    private String PNR_Airline_Num;

    public String getConfirmation_number() {
        return Confirmation_number;
    }

    public void setConfirmation_number(String confirmation_number) {
        Confirmation_number = confirmation_number;
    }

    public String getOptiontown_Flight_Pass_ID() {
        return Optiontown_Flight_Pass_ID;
    }

    public void setOptiontown_Flight_Pass_ID(String optiontown_Flight_Pass_ID) {
        Optiontown_Flight_Pass_ID = optiontown_Flight_Pass_ID;
    }

    public String getAirline_Name() {
        return Airline_Name;
    }

    public void setAirline_Name(String airline_Name) {
        Airline_Name = airline_Name;
    }

    public String getPNR_Airline_Num() {
        return PNR_Airline_Num;
    }

    public void setPNR_Airline_Num(String PNR_Airline_Num) {
        this.PNR_Airline_Num = PNR_Airline_Num;
    }

    private String Confirmation_Number_Label;
    private String Optiontown_Flight_Pass_Label;
    private String Airline_Label;
    private String PNR_Airline_Label;

    public String getConfirmation_Number_Label() {
        return Confirmation_Number_Label;
    }

    public void setConfirmation_Number_Label(String confirmation_Number_Label) {
        Confirmation_Number_Label = confirmation_Number_Label;
    }

    public String getOptiontown_Flight_Pass_Label() {
        return Optiontown_Flight_Pass_Label;
    }

    public void setOptiontown_Flight_Pass_Label(String optiontown_Flight_Pass_Label) {
        Optiontown_Flight_Pass_Label = optiontown_Flight_Pass_Label;
    }

    public String getAirline_Label() {
        return Airline_Label;
    }

    public void setAirline_Label(String airline_Label) {
        Airline_Label = airline_Label;
    }

    public String getPNR_Airline_Label() {
        return PNR_Airline_Label;
    }

    public void setPNR_Airline_Label(String PNR_Airline_Label) {
        this.PNR_Airline_Label = PNR_Airline_Label;
    }

    private String Booking_Confirm_Page;
    private String Booking_Purchase_Page;

    public String getBooking_Confirm_Page() {
        return Booking_Confirm_Page;
    }

    public void setBooking_Confirm_Page(String booking_Confirm_Page) {
        Booking_Confirm_Page = booking_Confirm_Page;
    }

    public String getBooking_Purchase_Page() {
        return Booking_Purchase_Page;
    }

    public void setBooking_Purchase_Page(String booking_Purchase_Page) {
        Booking_Purchase_Page = booking_Purchase_Page;
    }

    private String Booking_Process_Message;
    private String Booking_Process_Error;
    private String Booking_Process_Label;
    private String Book_More_Label;
    private String Book_Confirmation_Label;

    public String getBook_Confirmation_Label() {
        return Book_Confirmation_Label;
    }

    public void setBook_Confirmation_Label(String book_Confirmation_Label) {
        Book_Confirmation_Label = book_Confirmation_Label;
    }

    public String getBooking_Process_Message() {
        return Booking_Process_Message;
    }

    public void setBooking_Process_Message(String booking_Process_Message) {
        Booking_Process_Message = booking_Process_Message;
    }

    public String getBooking_Process_Error() {
        return Booking_Process_Error;
    }

    public void setBooking_Process_Error(String booking_Process_Error) {
        Booking_Process_Error = booking_Process_Error;
    }

    public String getBooking_Process_Label() {
        return Booking_Process_Label;
    }

    public void setBooking_Process_Label(String booking_Process_Label) {
        Booking_Process_Label = booking_Process_Label;
    }

    public String getBook_More_Label() {
        return Book_More_Label;
    }

    public void setBook_More_Label(String book_More_Label) {
        Book_More_Label = book_More_Label;
    }



    private String Book_Label;
    private String Conform_Label;

    public String getBook_Label() {
        return Book_Label;
    }

    public void setBook_Label(String book_Label) {
        Book_Label = book_Label;
    }

    public String getConform_Label() {
        return Conform_Label;
    }

    public void setConform_Label(String conform_Label) {
        Conform_Label = conform_Label;
    }

    public String getAgree_Check() {
        return Agree_Check;
    }

    public void setAgree_Check(String agree_Check) {
        Agree_Check = agree_Check;
    }

    private String Agree_Check;
    private String Select_Passengers_Label;
    private String Existing_Passengers_Label;
    private String Add_Passenger_Label;
    private String Add_New_Passenger_Label;

    public String getSelect_Passengers_Label() {
        return Select_Passengers_Label;
    }

    public void setSelect_Passengers_Label(String select_Passengers_Label) {
        Select_Passengers_Label = select_Passengers_Label;
    }

    public String getExisting_Passengers_Label() {
        return Existing_Passengers_Label;
    }

    public void setExisting_Passengers_Label(String existing_Passengers_Label) {
        Existing_Passengers_Label = existing_Passengers_Label;
    }

    public String getAdd_Passenger_Label() {
        return Add_Passenger_Label;
    }

    public void setAdd_Passenger_Label(String add_Passenger_Label) {
        Add_Passenger_Label = add_Passenger_Label;
    }

    public String getAdd_New_Passenger_Label() {
        return Add_New_Passenger_Label;
    }

    public void setAdd_New_Passenger_Label(String add_New_Passenger_Label) {
        Add_New_Passenger_Label = add_New_Passenger_Label;
    }

    private String passengers_Count;
    private String passengers_Label;

    public String getPassengers_Count() {
        return passengers_Count;
    }

    public void setPassengers_Count(String passengers_Count) {
        this.passengers_Count = passengers_Count;
    }

    public String getPassengers_Label() {
        return passengers_Label;
    }

    public void setPassengers_Label(String passengers_Label) {
        this.passengers_Label = passengers_Label;
    }

    private ArrayList<PrefixArray> PrefixArray;

    public ArrayList<PrefixArray> getPrefixArray() { return this.PrefixArray; }

    public void setPrefixArray(ArrayList<PrefixArray> PrefixArray) { this.PrefixArray = PrefixArray; }

    private ArrayList<CountryListArray> CountryListArray;

    public ArrayList<CountryListArray> getCountryListArray() { return this.CountryListArray; }

    public void setCountryListArray(ArrayList<CountryListArray> CountryListArray) { this.CountryListArray = CountryListArray; }

    private ArrayList<PhoneExtArray> PhoneExtArray;

    public ArrayList<PhoneExtArray> getPhoneExtArray() { return this.PhoneExtArray; }

    public void setPhoneExtArray(ArrayList<PhoneExtArray> PhoneExtArray) { this.PhoneExtArray = PhoneExtArray; }

    private boolean Is_Error;
    private String Error_Message;

    public boolean is_Error() {
        return Is_Error;
    }

    public void setIs_Error(boolean is_Error) {
        Is_Error = is_Error;
    }

    public String getError_Message() {
        return Error_Message;
    }

    public void setError_Message(String error_Message) {
        Error_Message = error_Message;
    }

    private ArrayList<UsersDetail> UsersDetail;

    public ArrayList<UsersDetail> getUsersDetail() { return this.UsersDetail; }

    public void setUsersDetail(ArrayList<UsersDetail> UsersDetail) { this.UsersDetail = UsersDetail; }

    private AddPassFormLabels Add_Pass_Form_Labels;

    public AddPassFormLabels getAddPassFormLabels() { return this.Add_Pass_Form_Labels; }

    public void setAddPassFormLabels(AddPassFormLabels Add_Pass_Form_Labels) { this.Add_Pass_Form_Labels = Add_Pass_Form_Labels; }

    private ArrayList<CardTypeArray> CardTypeArray;

    public ArrayList<CardTypeArray> getCardTypeArray() { return this.CardTypeArray; }

    public void setCardTypeArray(ArrayList<CardTypeArray> CardTypeArray) { this.CardTypeArray = CardTypeArray; }

    private ArrayList<String> UsersDisplayNameList;

    public ArrayList<String> getUsersDisplayNameList() { return this.UsersDisplayNameList; }

    public void setUsersDisplayNameList(ArrayList<String> UsersDisplayNameList) { this.UsersDisplayNameList = UsersDisplayNameList; }

    private String CompleteTrip_Label;
    private String FlightByFlight_Label;
    private String Continue_Label;

    public String getCompleteTrip_Label() {
        return CompleteTrip_Label;
    }

    public void setCompleteTrip_Label(String completeTrip_Label) {
        CompleteTrip_Label = completeTrip_Label;
    }

    public String getFlightByFlight_Label() {
        return FlightByFlight_Label;
    }

    public void setFlightByFlight_Label(String flightByFlight_Label) {
        FlightByFlight_Label = flightByFlight_Label;
    }

    public String getContinue_Label() {
        return Continue_Label;
    }

    public void setContinue_Label(String continue_Label) {
        Continue_Label = continue_Label;
    }

    private int JourneyTypeID;

    private String DepartArr;

    public String getDepartArr() {
        return DepartArr;
    }

    public void setDepartArr(String departArr) {
        DepartArr = departArr;
    }

    private String TravelDate;


    public int getJourneyTypeID() {
        return JourneyTypeID;
    }

    public void setJourneyTypeID(int journeyTypeID) {
        JourneyTypeID = journeyTypeID;
    }



    public String getTravelDate() {
        return TravelDate;
    }

    public void setTravelDate(String travelDate) {
        TravelDate = travelDate;
    }

    private String passengers;

    public String getPassengers() { return this.passengers; }

    public void setPassengers(String passengers) { this.passengers = passengers; }

    private String JourneyType;

    public String getJourneyType() { return this.JourneyType; }

    public void setJourneyType(String JourneyType) { this.JourneyType = JourneyType; }

    private String Top_Message;

    public String getTopMessage() { return this.Top_Message; }

    public void setTopMessage(String Top_Message) { this.Top_Message = Top_Message; }

    private ArrayList<Itinerarry> Itinerarry;

    public ArrayList<Itinerarry> getItinerarry() { return this.Itinerarry; }

    public void setItinerarry(ArrayList<Itinerarry> Itinerarry) { this.Itinerarry = Itinerarry; }

    private String CabinName;

    public String getCabinName() { return this.CabinName; }

    public void setCabinName(String CabinName) { this.CabinName = CabinName; }
}
