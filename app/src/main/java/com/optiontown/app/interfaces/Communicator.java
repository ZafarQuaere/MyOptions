package com.optiontown.app.interfaces;

import com.optiontown.app.model.selectproduct.FragmentCommunicationData;

/**
 * used by activity to communicate between fragment, to avoid direct inter-fragment communication
 */
public interface Communicator
{
    public void onResponse(FragmentCommunicationData message);
}
