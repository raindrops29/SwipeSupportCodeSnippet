package kr.co.tacademy.extinction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Copyright 2014 David Bleicher
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * Initial version created 11/10/2014
 */
public class ExtinctionCellAdapter
        extends RecyclerView.Adapter<ExtinctionCellAdapter.ViewHolder>
        implements View.OnClickListener {

    //랜덤한 백그라운드 컬러들
    private static final int[] bgColors = {
            0xAA000000,
            0xFF800000,
            0xFF008000,
            0xFF000080
    };

    //랜덤 셀(아이템 선택)
    private static Random random = new Random();


    // 확장된 아이템을 위한 Flag 값
    private int expandedPosition = -1;


    private ArrayList<Integer> mDataset; //이미지 리소스
    private Context mContext;

    public ExtinctionCellAdapter(Context context, ArrayList<Integer> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }
    //각 아이템을 표현할 위젯들(홀더)을 생성
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemRoot = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell_layout, parent, false);

        ViewHolder holder = new ViewHolder(itemRoot);

        //아이템의 루트에 이벤트를 걸어준다.
        holder.itemView.setOnClickListener(ExtinctionCellAdapter.this);
        holder.itemView.setTag(holder);


        return holder;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int colorIndex = random.nextInt(bgColors.length);
        String memberName = RandomArrayList.getGirlGroupName(mDataset.get(position));
        holder.extinctionName.setText(memberName);
        holder.extinctionName.setBackgroundColor(bgColors[colorIndex]);
        holder.extincImage.setImageResource(mDataset.get(position).intValue());

        if (position == expandedPosition) {
            holder.itemExpandArea.setVisibility(View.VISIBLE);
        } else {
            holder.itemExpandArea.setVisibility(View.GONE);
        }
        //멤버의 이름을 터치시
        holder.extinctionName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*String name = ((TextView) v).getText().toString();
                Intent intent = new Intent(MyApplication.getMyContext(), ActivityName.class);
                intent.putExtra("name", name);
                startActivity(intent);*/
            }
        });
    }
    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        //멤버이름을 가져온다.
        String memberName = RandomArrayList.getGirlGroupName(mDataset.get(holder.getLayoutPosition()));

        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev); //아이템을 바꾼다.
        }
        expandedPosition = holder.getLayoutPosition();
        notifyItemChanged(expandedPosition);
        Toast.makeText(mContext, "클릭한 아이템-" + memberName, Toast.LENGTH_SHORT).show();
    }
    /*
      아이템을 위한 홀더 객체
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView extincImage;
        TextView extinctionName;
        LinearLayout itemExpandArea;

        public ViewHolder(View itemView) {
            super(itemView);

            extincImage = (ImageView) itemView.findViewById(R.id.extinction_imageView);
            extinctionName = (TextView) itemView.findViewById(R.id.extinction_name);
            itemExpandArea = (LinearLayout) itemView.findViewById(R.id.item_expand_area);
        }
    }
}
