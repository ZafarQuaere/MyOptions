package com.optiontown.app.view.fragment.fpo.redeem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optiontown.R;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.BFragment;

import java.util.Random;

/**
 * Created by amit on 24-10-2016.
 */
public class TestFragment extends BFragment
{

    private TreeView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.fragment_test, container, false);

        view = new TreeView(getActivity());

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());



        return view;
    }



    @Override
    public void onBackEventPre() {

    }

    @Override
    public void onBackEventPost() {

    }

    @Override
    public void onFocusEvent() {

    }

    private class TreeView extends View {
        Paint paint = new Paint();

        public TreeView(Context context) {
            super(context);
            paint.setColor(Color.BLACK);
        }

        @Override
        public void onDraw(Canvas canvas) {
            Utils.DEBUG("onDraw() called");


            drawTree(canvas, true, 100, 350, 700);

        }


        private void drawTree(Canvas canvas, boolean first, int bLength, int x, int y)
        {
            Utils.DEBUG("drawTree() >> " + first + "/" + bLength + "/" + x + "/" + y);
            int deviationInBranchLength = 20;
            if(bLength > 10 && x > 0 && y > 0)
            {
                if(first)
                {
                    canvas.drawLine(x, y, x, y - bLength, paint);
                    int x11 = x;
                    int y11 = y - bLength;
                    int x12 = x - getDeviationInBranchAngle();
                    int y12 = y - bLength - bLength;
                    canvas.drawLine(x11, y11, x12, y12, paint);
                    drawTree(canvas, false, bLength - deviationInBranchLength, x12, y12);
                    int x21 = x;
                    int y21 = y - bLength;
                    int x22 = x + getDeviationInBranchAngle();
                    int y22 = y - bLength - bLength;

                    canvas.drawLine(x21, y21, x22, y22, paint);
                    drawTree(canvas, false, bLength - deviationInBranchLength, x22, y22);

                    return;
                }
                int x11 = x;
                int y11 = y;
                int x12 = x - getDeviationInBranchAngle();
                int y12 = (int) (y - (bLength*0.75));
                canvas.drawLine(x11, y11, x12, y12, paint);
                drawTree(canvas, false, bLength - deviationInBranchLength, x12, y12);

                int x21 = x;
                int y21 = y;
                int x22 = x + getDeviationInBranchAngle();
                int y22 = (int) (y - (bLength*0.60));

                canvas.drawLine(x21, y21, x22, y22, paint);
                drawTree(canvas, false, bLength - deviationInBranchLength, x22, y22);

            }
            else
            {
                System.out.println("done");
            }
        }



        private int getDeviationInBranchAngle() {
            int val = (50 /*- (new Random().nextInt(40))*/);
            Utils.DEBUG("getDeviationInRightBranchAngle() >> " + val);
            return val;
        }
    }
}
