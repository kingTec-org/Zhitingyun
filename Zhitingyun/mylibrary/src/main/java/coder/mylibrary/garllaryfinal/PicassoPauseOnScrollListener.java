package coder.mylibrary.garllaryfinal;

import com.squareup.picasso.Picasso;

import cn.finalteam.galleryfinal.PauseOnScrollListener;

/**
 * Created by dasiy on 17/7/24.
 */

public class PicassoPauseOnScrollListener  extends PauseOnScrollListener {
    public PicassoPauseOnScrollListener(boolean pauseOnScroll, boolean pauseOnFling) {
        super(pauseOnScroll, pauseOnFling);
    }

    @Override
    public void resume() {
        Picasso.with(getActivity()).resumeTag(getActivity());

    }

    @Override
    public void pause() {
        Picasso.with(getActivity()).pauseTag(getActivity());

    }
}
