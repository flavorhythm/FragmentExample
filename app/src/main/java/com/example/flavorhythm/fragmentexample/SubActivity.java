package com.example.flavorhythm.fragmentexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import fragment.FragmentSwitch;
import fragment.FragmentSwitchBtm;
import fragment.FragmentSwitchTop;

/**
 * Displays a non-static fragment by declaring a FrameLayout in XML. FragmentManager then points to
 * the ViewGroup and adds/removes fragments in reaction to user input (through a Button widget)
 * FAButton finished this activity and returns to MainActivity.
 *
 * This is the main Activity to focus on, in regards to my FragmentTransaction question.
 * This is how I use Transactions in the app that I'm currently working on. I've tried to implement
 * FragmentTransaction in a couple of ways and this is the best way I've come up with.
 * When the Activity fires the {@link #onCreate(Bundle)} method, a transaction occurs to place the
 * very first Fragment into an empty ViewGroup (FrameLayout). This transaction prevents itself from
 * saving to the backstack with {@link FragmentTransaction#disallowAddToBackStack()}.
 *
 * Once a Fragment is placed in the ViewGroup, each subsequent Transaction removes the currently
 * displayed Fragment and adds a new instance of the target Fragment. These transactions ad added to
 * the backstack with the {@link #POP_BACK_ALL_TAG} so that when the Activity fires the
 * {@link #onBackPressed()} method in reaction to the user input, the entire backstack is popped
 * before allowing the user to return to the previous Activity.
 *
 * The way I implement FragmentTransaction requires a custom method like
 * {@link #getFragmentInstance(String)}. However, I am aware of the show/hide methods in
 * FragmentTransaction {@link FragmentTransaction#show(Fragment)} {@link FragmentTransaction#hide(Fragment)}
 * and the replace method {@link FragmentTransaction#replace(int, Fragment)}. These methods all seem
 * to reach the same solution but in different ways. Not knowing "which one is right" makes me
 * question the way I'm using FragmentTransactions.
 *
 * In no way am I suggesting this is the right way to do it. Unfortunately this is the best way
 * I found to make transactions work in a way that I want.
 */
public class SubActivity extends AppCompatActivity implements FragmentSwitch.Callback {
    //Integer variable set to Fragment FrameLayout container for ease of use
    private static final int sFragmentContainer = R.id.switch_container;
    //String variable used to pop backstack
    private static final String POP_BACK_ALL_TAG = "pop_all_backstack";
    //String variable stores Snackbar text, retrieved from Resources
    private String snackbarText;

    /**
     * Sets up ActionBar and FAButton when Activity is created, retrieves Snackbar text from Resources
     * and also places the very first Fragment into the FrameLayout to be displayed
     * @param savedInstanceState Saved Activity state. Ignored for this application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //Retrieves Snackbar text from Resources
        snackbarText = getResources().getString(R.string.snackbar_text);
        //Sets toolbar as custom Actionbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Adds the first fragment to FrameLayout when Activity is being created.
        //Transaction is not saved to backstack so when entire backstack is popped,
        //the fragment added in this transaction is restored.
        getSupportFragmentManager()
                .beginTransaction()
                .add(
                        sFragmentContainer,
                        getFragmentInstance(FragmentSwitch.SWITCH_TOP),
                        FragmentSwitch.SWITCH_TOP
                )
                .disallowAddToBackStack()
                .commit();
        //Starts SubActivity when FAButton is clicked
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Creates Options menu within Actionbar
     * @param menu Menu object parameter used for ActionBar menu creation.
     * @return returns false to prevent menu creation.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {return false;}

    /**
     * Pops backstack on one back press. Only after a second press will the Activity finish
     * and return to prior Activity
     */
    @Override
    public void onBackPressed() {
        //If backstack entries exist, clear backstack and notify user with a Snackbar
        if(getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack(
                    POP_BACK_ALL_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE
            );
            Snackbar.make(findViewById(R.id.sub_coordinator), snackbarText, Snackbar.LENGTH_SHORT).show();
        //If backstack has no entries, finish this activity and return to prior Activity
        } else finish();
    }

    /**
     * Replaces current fragment with new target fragment.
     * Method is implemented by {@link FragmentSwitch.Callback}
     * @param target String variable defining next fragment to display
     */
    @Override
    public void onSwitchBtnPressed(String target) {
        Fragment f = getFragmentInstance(target);
        if(f == null) return; //Confirms fragment instance properly exists
        //Removes current fragment from FrameLayout and adds the fragment
        //corresponding to String variable target.
        //This transaction is saved to the backstack with the POP_BACK_ALL_TAG tag
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentById(sFragmentContainer))
                .add(
                        sFragmentContainer,
                        getFragmentInstance(target),
                        target
                )
                .addToBackStack(POP_BACK_ALL_TAG)
                .commit();
    }

    /**
     * Retrieves fragment instance corresponding to String parameter {@param target}
     * @param target String variable corresponding to a Fragment instance
     * @return A fragment instance corresponding to the paramter {@param target}.
     * Returns null if target is invalid.
     */
    @Nullable
    private Fragment getFragmentInstance(String target) {
        switch(target) {
            case FragmentSwitch.SWITCH_TOP:
                return FragmentSwitchTop.newInstance();
            case FragmentSwitch.SWITCH_BTM:
                return FragmentSwitchBtm.newInstance();
            default: return null;
        }
    }
}
