
package com.optiontown.app.model.redeem.mmp;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassUser {

    @SerializedName("Users_Added")
    @Expose
    private List<UsersAdded> usersAdded = new ArrayList<UsersAdded>();
    @SerializedName("Label")
    @Expose
    private String label;
    @SerializedName("Id")
    @Expose
    private String id;

    /**
     * 
     * @return
     *     The usersAdded
     */
    public List<UsersAdded> getUsersAdded() {
        return usersAdded;
    }

    /**
     * 
     * @param usersAdded
     *     The Users_Added
     */
    public void setUsersAdded(List<UsersAdded> usersAdded) {
        this.usersAdded = usersAdded;
    }

    /**
     * 
     * @return
     *     The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The Label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

}
