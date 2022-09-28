package com.zafar.kotlin.enums

class Event {

    public enum Type {
        USER_IDENTIFIED("bein_user_identified"),
        USER_PROFILE_SELECTED("bein_user_profile_selected"),
        USER_PROFILE_CREATED("bein_create_profile");

       private lateinit var name;

    }
}