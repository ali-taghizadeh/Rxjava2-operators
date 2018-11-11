package ir.taghizadeh.rxjava2_operators.utils;

import android.app.Activity;

import ir.taghizadeh.rxjava2_operators.ui.activity.samples.EventCounterActivity;
import ir.taghizadeh.rxjava2_operators.ui.activity.samples.SmartFormActivity;

public enum EnumActivities {

    EVENT_COUNTER {
        @Override
        public Activity getActivity() {
            return new EventCounterActivity();
        }
    },
    SMART_FORM {
        @Override
        public Activity getActivity(){
            return new SmartFormActivity();
        }
    };
    public abstract Activity getActivity();
}