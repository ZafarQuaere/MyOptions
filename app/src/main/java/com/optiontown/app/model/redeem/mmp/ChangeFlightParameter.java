package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;

/**
 * Created by amit on 05-11-2016.
 */
public class ChangeFlightParameter implements Serializable
{
    private String passchangrtypeid;

    public String getPasschangrtypeid() { return this.passchangrtypeid; }

    public void setPasschangrtypeid(String passchangrtypeid) { this.passchangrtypeid = passchangrtypeid; }

    private String Passlabelname;

    public String getPasslabelname() { return this.Passlabelname; }

    public void setPasslabelname(String Passlabelname) { this.Passlabelname = Passlabelname; }
}
