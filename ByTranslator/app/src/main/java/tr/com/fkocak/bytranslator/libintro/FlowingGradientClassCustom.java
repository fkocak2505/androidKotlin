package tr.com.fkocak.bytranslator.libintro;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class FlowingGradientClassCustom {
    int Duration=4000;
    RelativeLayout rl;
    LinearLayout ll;
    ImageView im;
    int alphaint;
    int d;
    AnimationDrawable frameAnimation;

    public FlowingGradientClassCustom setTransitionDuration(int time) {
        this.Duration = time;

        return this;
    }
    public FlowingGradientClassCustom onLinearLayout(LinearLayout ll) {
        this.ll = ll;
        return this;
    }

    public FlowingGradientClassCustom onImageView(ImageView im) {
        this.im = im;
        return this;
    }

    public FlowingGradientClassCustom onRelativeLayout(RelativeLayout rl) {
        this.rl = rl;
        return this;
    }
    public FlowingGradientClassCustom start() {

        if(ll!=null){
            ll.setBackgroundResource(d);
        }
        else if(rl!=null){
            rl.setBackgroundResource(d);
        }
        else if(im!=null){
            im.setBackgroundResource(d);
        }
        if(ll!=null){
            frameAnimation = (AnimationDrawable) ll.getBackground();
        }
        else if(rl!=null){
            frameAnimation = (AnimationDrawable) rl.getBackground();
        }
        else if(im!=null){
            frameAnimation = (AnimationDrawable) im.getBackground();
        }
        frameAnimation.setEnterFadeDuration(Duration);
        frameAnimation.setExitFadeDuration(Duration);
        frameAnimation.start();

        return this;
    }

    public FlowingGradientClassCustom setBackgroundResource(int d) {
        this.d = d;
        return this;
    }
    public FlowingGradientClassCustom setAlpha(int alphaint) {
        this.alphaint = alphaint;
        frameAnimation.setAlpha(alphaint);
        return this;
    }

}
