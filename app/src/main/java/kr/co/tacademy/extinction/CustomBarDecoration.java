package kr.co.tacademy.extinction;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomBarDecoration extends RecyclerView.ItemDecoration {
    private int columnCount;
    private int columnHeight;

    public CustomBarDecoration(int columnCount, int columnHeight) {
        this.columnCount = columnCount;
        this.columnHeight = columnHeight;
    }
    //아이템의 시작 높이를 설정
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //첫번째, 두번째 아이템이 놓여질 Inset값
        if (parent.getChildAdapterPosition(view) < columnCount) {
            //top값을 텍스트뷰 아래에 위치 시킴
            outRect.set(0,columnHeight,0,0);
        } else {
            outRect.set(0,0,0,0);
        }
    }
}