package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flavorhythm.fragmentexample.R;

/**
 * Inflates fragment_static layout and displays on MainActivity. This custom Fragment extends Fragment
 */
public class FragmentStatic extends Fragment {
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
        return inflater.inflate(R.layout.fragment_static, container, false);
    }
}
