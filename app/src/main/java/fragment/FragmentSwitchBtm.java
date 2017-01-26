package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Uses the view inflated in {@link FragmentSwitch#onCreateView(LayoutInflater, ViewGroup, Bundle)}
 * and sets the Fragment name to {@link FragmentSwitch#mTextView}.
 * This fragment extends {@link FragmentSwitch}
 */
public class FragmentSwitchBtm extends FragmentSwitch {
    /**
     * Instantiates this Fragment for reusability. Fragments must have an empty constructor
     * for when a Fragment's Saved State is restored.
     * @return A new instance of this Fragment, along with arguments
     * @see android.support.v4.app.Fragment
     */
    public static FragmentSwitchBtm newInstance() {
        Bundle args = new Bundle();

        FragmentSwitchBtm fragment = new FragmentSwitchBtm();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Sets the name of this Fragment to the TextView widget {@link FragmentSwitch#mTextView}
     * @param view View inflated in {@link FragmentSwitch#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * @param savedInstanceState The saved state of this fragment. Ignored for this application
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTextView().setText(SWITCH_BTM);
    }

    /**
     * Retrieves the name of the target Fragment when Button widget in this Fragment is clicked.
     * This is an abstract declared method in {@link FragmentSwitch}
     * @return String variable of target Fragment type
     */
    @Override
    protected String getTargetFragmentType() {return SWITCH_TOP;}
}
