package outsource.cp.cithr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import outsource.cp.cithr.R;
import someothers.CircleImageView;

/**
 * Created by Raymon on 2017/3/2.
 * 用户首页招聘信息的recyclerview adapter
 */
public class User_mainf_job_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //数据
    private List<String> mdata;
    // 设置底部布局
    private static final int TYPE_FOOTER = 0;
    // 设置默认布局
    private static final int TYPE_DEFAULT = 1;

    private Context mcontext;
    //判断是不是最后一个
    private boolean isShowFoot=true;

    public User_mainf_job_adapter(Context mcontext){

        this.mcontext=mcontext;

    }
    // 设置数据并刷新
    public void setData(List<String> data)
    {
        Log.v("mainAdapter","sedata");
        this.mdata = data;
        notifyDataSetChanged();
    }

    // 设置不同的item
    @Override
    public int getItemViewType(int position)
    {
        // 判断当前位置+1是不是等于数据总数（因为数组从0开始计数），是的就加载底部布局刷新，不是就加载默认布局
        if (position + 1 == getItemCount())
        {
            return TYPE_FOOTER;
        } else
        {
            return TYPE_DEFAULT;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_DEFAULT) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usermainf_rec_job, parent, false);
            final DefaultViewHolder holder = new DefaultViewHolder(view);
            holder.clickview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    String data = mdata.get(position);
//                    Intent intent = new Intent(v.getContext(), details_job_Activity.class);
//                    intent.putExtra("JobId", job.getId());
//                    v.getContext().startActivity(intent);
                }
            });
            return holder;
        }else {

            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loadmore,null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // 用来在运行时指出对象是某一个对象
        if (holder instanceof DefaultViewHolder)
        {
            String data = mdata.get(position);

            if (data == null)
            {
                return;
            }

            ((DefaultViewHolder) holder).item_jobname.setText(data);
        }
    }


    @Override
    public int getItemCount() {

        // 判断是不是显示底部，是就返回1，不是返回0
        int begin = isShowFoot? 1 : 0;
        // 没有数据的时候，直接返回begin
        if (mdata == null)
        {
            return begin;
        }
        // 因为底部布局要占一个位置，所以总数目要+1
        return mdata.size() + begin;
    }
    // 设置是否显示底部加载提示（将值传递给全局变量）
    public void isShowFooter(boolean showFooter)
    {
        this.isShowFoot = showFooter;
    }

    // 判断是否显示底部，数据来自全局变量
    public boolean isShowFooter()
    {
        return this.isShowFoot;
    }



    // 底部布局的ViewHolder
    public class FooterViewHolder extends RecyclerView.ViewHolder
    {
        public FooterViewHolder(View view)
        {
            super(view);
        }
    }

    // 默认布局的ViewHolder
    public class DefaultViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.item_jobname) TextView item_jobname;
        @BindView(R.id.item_jobtime) TextView item_jobtime;
        @BindView(R.id.item_salsary) TextView item_salasry;
        @BindView(R.id.item_position) TextView item_position;
        @BindView(R.id.item_exp) TextView item_exp;
        @BindView(R.id.item_edu) TextView item_edu;
        @BindView(R.id.item_image) CircleImageView item_image;
        @BindView(R.id.item_contact) TextView item_contact;
        @BindView(R.id.item_contact_position) TextView item_contact_position;
        @BindView(R.id.item_cp_name) TextView item_cpname;
        View clickview;
        public DefaultViewHolder(View itemView) {
            super(itemView);
            clickview=itemView;
            ButterKnife.bind(this,itemView);

        }
    }


}
