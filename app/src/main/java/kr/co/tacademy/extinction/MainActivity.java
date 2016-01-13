package kr.co.tacademy.extinction;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private CollapsingSwipeRefreshLayout swiper;
    private RecyclerView mRecycler;
    /*
     * 지그재그식 그리드 레이아웃
     */
    private StaggeredGridLayoutManager mSGLM;
    private LinearLayout customBar;
    private TextView customTitle;

    private ExtinctionCellAdapter mAdapter;

    //param 1번째는  이미지의 resource 값
    private ArrayList<Integer> itemsOfData;
    private Animation inAnim;
    private Animation outAnim;

    // 커스텀 바 setting
    private int columnCount;
    //커스텀 바의 현재 단말기 화면의 높이에 적절히 세팅
    private int customBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////
        //  확장 가능한 TitleBar(~~액션바를 붙이지 않아도)   //
        //////////////////////
        inAnim = AnimationUtils.loadAnimation(this, R.anim.abc_slide_in_top);
        outAnim = AnimationUtils.loadAnimation(this, R.anim.abc_slide_out_top);
        customBar = (LinearLayout) findViewById(R.id.custom_view_bar);

        /*
         * 2 column의 지그재그 만들기 만들기 위함
         */
        columnCount = 2;

        // ActionBar의 추천사이즈로 커스텀을 설정함.(리사이클뷰의 아이템 데코레이션 값)
        TypedValue typeValue = new TypedValue();
        if (getTheme().resolveAttribute(R.attr.actionBarSize, typeValue, true)) {
            //현재 단말기 해상도에 적절한 크기를 자동으로 세팅
            customBarHeight = TypedValue.complexToDimensionPixelSize(typeValue.data,
                    getResources().getDisplayMetrics());
        }

        customTitle = (TextView) findViewById(R.id.custom_title);

       /* customTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addItemAtPosition(0, "새 카드를 동적으로 세팅 "+new Date().toString());
            }
        });*/

        //////////////////////////////
        //  Setup Swipe To Refresh  //
        //////////////////////////////
        swiper = (CollapsingSwipeRefreshLayout) findViewById(R.id.swipe_container);
        /*
          Swipe Icon 크기 및 컬러설정
         */
        swiper.setSize(SwipeRefreshLayout.LARGE);
        swiper.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        );
        //Swipe 발생시
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*
                  발생시(2.5초)
                 */
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (itemsOfData != null && mAdapter != null) {
                            //어댑터의 아이템을 랜덤하게 섞고 다시 뿌린다.
                            Collections.shuffle(itemsOfData);
                            mAdapter.notifyDataSetChanged();
                        }
                        swiper.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        //////////////////////////////////////////////
        //  지그재그 그리드뷰 Grab //
        //////////////////////////////////////////////
        mRecycler = (RecyclerView) findViewById(R.id.zig_zag_recycler_view);

        //지그재그에 들어갈 아이템을 데코레이트한다.(여기선 아이템의 시작 높이를 의미)
        mRecycler.addItemDecoration(new CustomBarDecoration(columnCount, customBarHeight));

        mSGLM = new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL);
        //지그재그 그리드와 리사이클러뷰를 세팅한다.
        mRecycler.setLayoutManager(mSGLM);

        //////////////////////////////
        //  이미지가 랜덤하게 셔플되어 넘어옴 //
        //////////////////////////////
        itemsOfData = RandomArrayList.getSuffleArrayList();
        //리사이클뷰에 보일 어댑터
        mAdapter = new ExtinctionCellAdapter(this, itemsOfData);

        /////////////////////////////////////////////
        //  스크롤 리스터 등록 //
        /////////////////////////////////////////////
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            //스크롤의 수평/수직의 갯수--여기선 Vertical이므로 dy의 값은 수직의 값이 됨(+-값)
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 3) {
                    if (customBar.getVisibility() == View.VISIBLE)
                        hideCustomBar();

                } else if (dy < -3) {

                    if (customBar.getVisibility() == View.GONE)
                        showCustomBar();
                }
            }
        });

        mRecycler.setAdapter(mAdapter);

        //스크롤될 뷰(여기서는 리사이클러뷰)를 스위프에 등록
        swiper.setTargetScrollableView(mRecycler);

    }

    private void hideCustomBar() {
        customBar.startAnimation(outAnim);
        customBar.setVisibility(View.GONE);
    }

    private void showCustomBar() {
        customBar.startAnimation(inAnim);
        customBar.setVisibility(View.VISIBLE);
    }

    /*
     * 아이템 새로 추가
     */
  /*  public void addItemAtPosition(int position, String item) {
        itemsOfData.add(position, item);
        mAdapter.notifyItemInserted(position);
        mSGLM.scrollToPosition(position);
    }*/
}