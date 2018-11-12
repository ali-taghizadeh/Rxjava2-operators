package ir.taghizadeh.rxjava2_operators.ui.activity.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.R;

public class TypingIndicatorActivity extends AppCompatActivity {

/*

    This sample is very useful when you're building a chat application.
    Here we show the typing indicator if there are at least two characters and user keeps typing
    each character within three seconds

*/

    Unbinder unbinder;
    @BindView(R.id.button_samples)
    Button button_samples;
    @BindView(R.id.text_typing_indicator)
    TextView text_typing_indicator;
    @BindView(R.id.et_chat)
    EditText et_chat;
    private Disposable typingIndicatorDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing_indicator);
        unbinder = ButterKnife.bind(this);
        button_samples.setVisibility(View.GONE);
    }

    private Disposable getTypingIndicatorDisposable() {
        return RxTextView.textChanges(et_chat)
                .filter(charSequence -> charSequence.length() > 1)
                .buffer(3, TimeUnit.SECONDS, 1)
                .map(charSequences -> charSequences.size() != 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hasData -> {
                    if (hasData)
                        text_typing_indicator.setText(getString(R.string.user_is_typing));
                    else
                        text_typing_indicator.setText("");
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        typingIndicatorDisposable.dispose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        typingIndicatorDisposable = getTypingIndicatorDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
