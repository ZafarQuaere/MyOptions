package com.enums;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * Although a single class could potentially be sufficient, the purpose of explicit
 * event classes is to provide a precise breakdown of the exact events required by the Axis
 * backend so there is no room for programmer error.
 * <p>
 * This class can be extended to provide custom events, or be used directly if necessary.
 */
public abstract class AnalyticsEvent {
    // this is Backend provided event type converted to a String
    private String type;
    // custom event type created within the app to clearly distinguish
    // backend events
    private EventType customEventType = provideCustomEventType();


    public enum EventType {
        APP_EVENT("Application"),
        BROWSE_EVENT("Browse"),
        SERVICE_ERROR_EVENT("Service Error"),
        RUNTIME_ERROR_EVENT("Runtime Error"),
        ITEM_EVENT("Item"),
        PLAYBACK_EVENT("Playback"),
        USER_EVENT("User"),
        SUBSCRIPTION_EVENT("Ecommerce"),
        ADJUST_EVENT("Tracking");

        private String category;

        EventType(final String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    protected AnalyticsEvent(@NonNull String type){
        this.type = type;
    }


    @NonNull
    protected abstract EventType provideCustomEventType();



    public String getType() {
        return type;
    }

    public EventType getCustomEventType() {
        return customEventType;
    }

    protected void setCustomEventType(EventType customEventType) {
        this.customEventType = customEventType;
    }
}
