
package com.optiontown.app.model.legreview;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LegReviewHome {

    @SerializedName("searchResult")
    @Expose
    private SearchResult searchResult;
    @SerializedName("reviewDetails")
    @Expose
    private ReviewDetails reviewDetails;

    /**
     * 
     * @return
     *     The searchResult
     */
    public SearchResult getSearchResult() {
        return searchResult;
    }

    /**
     * 
     * @param searchResult
     *     The searchResult
     */
    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    /**
     * 
     * @return
     *     The reviewDetails
     */
    public ReviewDetails getReviewDetails() {
        return reviewDetails;
    }

    /**
     * 
     * @param reviewDetails
     *     The reviewDetails
     */
    public void setReviewDetails(ReviewDetails reviewDetails) {
        this.reviewDetails = reviewDetails;
    }

}
