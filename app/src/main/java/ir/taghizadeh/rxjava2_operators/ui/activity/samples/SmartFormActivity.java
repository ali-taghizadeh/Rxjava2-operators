package ir.taghizadeh.rxjava2_operators.ui.activity.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.R;

public class SmartFormActivity extends AppCompatActivity {

/*

    CombineLatest operator helps to create a smarter login form

*/

    Unbinder unbinder;
    @BindView(R.id.et_email)
    TextInputEditText et_mail;
    @BindView(R.id.et_password)
    TextInputEditText et_password;
    @BindView(R.id.button_login)
    Button button_login;
    private Disposable validationDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_form);
        unbinder = ButterKnife.bind(this);
    }

    private Disposable getValidationDisposable() {
        Observable<CharSequence> emailObservable = RxTextView.textChanges(et_mail);
        emailObservable.map(charSequence -> isValid(charSequence, getString(R.string.email)))
                .subscribe(isValid -> {
                    if (!isValid) et_mail.setError("Invalid Email");
                });

        Observable<CharSequence> passwordObservable = RxTextView.textChanges(et_password);
        passwordObservable.map(charSequence -> isValid(charSequence, getString(R.string.password)))
                .subscribe(isValid -> {
                    if (!isValid) et_password.setError("Invalid Password");
                });

        return Observable.combineLatest(emailObservable, passwordObservable, (charSequence, charSequence2) -> isValid(charSequence, getString(R.string.email)) && isValid(charSequence2, "password"))
                .subscribe(booleanConsumer -> button_login.setVisibility(booleanConsumer ? View.VISIBLE : View.GONE));
    }

    private boolean isValid(CharSequence charSequence, String source) {
        if (source.equals(getString(R.string.email))) {
            return charSequence.toString().matches("^(.+)@(.+)$");
        } else {
            return charSequence.toString().matches("^(?=.*\\d).{4,8}$");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        validationDisposable.dispose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        validationDisposable = getValidationDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
