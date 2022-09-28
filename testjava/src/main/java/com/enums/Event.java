package com.enums;

import io.reactivex.annotations.NonNull;

public class Event extends AnalyticsEvent{

    protected Event(@NonNull String type) {
        super(type);
    }

    public enum Type {
        USER_IDENTIFIED("bein_user_identified"),
        USER_PROFILE_SELECTED("bein_user_profile_selected"),
        USER_PROFILE_CREATED("bein_create_profile"),
        USER_SIGNED_OUT("bein_user_signed_out"),
        USER_ACTIONED("bein_user_actioned"),
        USER_REGISTERING("bein_user_registering"),
        USER_REGISTERED("bein_user_registered"),
        USER_SIGN_IN_INITIATE("bein_sign_in_initiate"),
        USER_SIGN_IN_SUCCESSFUL("bein_user_sign_in_successful"),
        USER_SIGN_IN_FAILED("bein_sign_in_failed"),
        USER_CREATE_ACCOUNT_INITIATE("bein_create_account_initiate"),
        USER_CREATE_ACCOUNT_SUCCESSFUL("bein_create_account_successful"),
        USER_CREATE_ACCOUNT_FAILED("bein_create_account_failed");

        private String name;

        Type(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @NonNull
    @Override
    protected EventType provideCustomEventType() {
        return null;
    }


}
