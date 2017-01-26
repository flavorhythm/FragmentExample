package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.flavorhythm.fragmentexample.R;

/**
 * Inflates fragment_switch layout and displays in SubActivity.
 * This is the Superclass for the two non-static Fragments
 * @see FragmentSwitchTop
 * @see FragmentSwitchBtm
 */
public abstract class FragmentSwitch extends Fragment {
    //String variables used to distinguish between this class's subclass
    public static final String SWITCH_TOP = "Non-static Top Fragment";
    public static final String SWITCH_BTM = "Non-static Bottom Fragment";
    //Callback object that is implemented in parent Activity
    private Callback mCallback;
    //TextView that displays the name of the currently displayed FragmentSwitch
    private TextView mTextView;

    /**
     * Initializes {@link #mCallback} by casting {@param context} to Callback
     * @param context The context this Fragment attaches to (Parent Activity)
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback)context;
    }

    /**
     * Inflates view into this Fragment
     * @param inflater Inflater object provided and used to inflate this Fragment's View
     * @param container The parent ViewGroup of the view that is inflated in this method
     * @param savedInstanceState The saved state of this fragment. Ignored for this application
     * @return inflated view for this Fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_switch, container, false);
    }

    /**
     * Initializes the TextView and Button widgets by {@link View#findViewById(int)} them in the Layout.
     * Sets an onClickListener to the button
     * @param view View inflated in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * @param savedInstanceState The saved state of this fragment. Ignored for this application
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextView = (TextView)view.findViewById(R.id.switch_text);
        Button button = (Button)view.findViewById(R.id.switch_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onSwitchBtnPressed(getTargetFragmentType());
            }
        });
    }

    /**
     * Retrieves a String variable corresponding to the Fragment that the user is wanting to display.
     * @return String variable corresponding to the Fragment to display
     */
    protected abstract String getTargetFragmentType();

    /**
     * Retrieves TextView widget initialized in this Superclass
     * @return TextView containing the name of the Switch Fragment
     */
    protected TextView getTextView() {return mTextView;}

    /**
     * A callback interface object implemented in parent Activity
     */
    public interface Callback {
        /**
         * Fires when the Button widget is clicked.
         */
        void onSwitchBtnPressed(String target);
    }
}
