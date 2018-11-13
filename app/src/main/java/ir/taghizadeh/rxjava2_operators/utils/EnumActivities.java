package ir.taghizadeh.rxjava2_operators.utils;

import android.app.Activity;

import ir.taghizadeh.rxjava2_operators.ui.activity.samples.EventCounterActivity;
import ir.taghizadeh.rxjava2_operators.ui.activity.samples.SearchViewActivity;
import ir.taghizadeh.rxjava2_operators.ui.activity.samples.SmartFormActivity;
import ir.taghizadeh.rxjava2_operators.ui.activity.samples.TypingIndicatorActivity;

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
    },
    TYPING_INDICATOR {
        @Override
        public Activity getActivity(){
            return new TypingIndicatorActivity();
        }
    },
    SEARCH_VIEW {
        @Override
        public Activity getActivity(){
            return new SearchViewActivity();
        }
    };
    public abstract Activity getActivity();
}
