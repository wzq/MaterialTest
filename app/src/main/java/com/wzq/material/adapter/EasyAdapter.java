package com.wzq.material.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzq.material.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzq on 15/4/13.
 * It's apply to most, but not to  complex situations.
 */
public class EasyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> data;
    private int layoutId;
    private int[] viewId;
    private CallBack callBack;

    public EasyAdapter(List<Object> data, int layoutId, int[] viewId, CallBack callBack) {
        this.data = data;
        this.layoutId = layoutId;
        this.viewId = viewId;
        this.callBack = callBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == 0) {
            return new EasyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false), viewId);
        }else{
            return new LoadHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.load_default, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof  EasyHolder) {
            EasyHolder holder = (EasyHolder) viewHolder;
            if (callBack != null) {
                callBack.bindItemView(holder, data.get(position), position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void removeItem(Object item){
        int realPosition = data.indexOf(item);
        if(realPosition > -1) {
            data.remove(realPosition);
            notifyItemRemoved(realPosition);
        }
    }

    public void insertItem(Object item, int position){
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void showLoad(){
        data.add(getItemCount(), null);
        notifyItemInserted(getItemCount());
    }

    public void loadComplete(){
        removeItem(null);
    }

    public boolean isLoading(){
        return data.indexOf(null)>0?true:false;
    }

    public static class EasyHolder extends RecyclerView.ViewHolder {
        public List<ImageView> imageViews;
        public List<TextView> textViews;
        public List<Button> buttons;
        public List<View> views;

        public EasyHolder(View itemView, int[] viewId) {
            super(itemView);
            imageViews = new ArrayList<>();
            textViews = new ArrayList<>();
            buttons = new ArrayList<>();
            for (int i = 0; i < viewId.length; i++) {
                View temp = itemView.findViewById(viewId[i]);
                if (temp instanceof ImageView) {
                    imageViews.add((ImageView) temp);
                    continue;
                } else if (temp instanceof TextView) {
                    if (temp instanceof Button) {
                        buttons.add((Button) temp);
                    }else{
                        textViews.add((TextView) temp);
                    }
                    continue;
                } else {
                    views.add(temp);
                    continue;
                }
            }
        }
    }

    public static class LoadHolder extends RecyclerView.ViewHolder{
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public interface  CallBack{
        void bindItemView(EasyHolder holder, Object item, int position);
    }

    /**
     * @param position
     * @return Type: 0 normal, 1 load
     */
    @Override
    public int getItemViewType(int position) {
        return (data.get(position) == null)?1:0;
    }
}
