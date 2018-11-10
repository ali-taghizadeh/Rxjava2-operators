package ir.taghizadeh.rxjava2_operators.utils;

import android.app.Activity;

import ir.taghizadeh.rxjava2_operators.ui.activity.samples.EventCounterActivity;

public enum EnumActivities {

    EVENT_COUNTER {
        @Override
        public Activity getActivity() {
            return new EventCounterActivity();
        }
    };
    public abstract Activity getActivity();
}
