package com.github.wzq;

import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    private int lastPosition = -1;

    public int getAnimId() {
        return animId;
    }

    public void setAnimId(int animId) {
        this.animId = animId;
    }

    private int animId = 0;

    public EasyAdapter(List<Object> data, int layoutId, int[] viewId, CallBack callBack) {
        this.data = data;
        this.layoutId = layoutId;
        this.viewId = viewId;
        this.callBack = callBack;
    }

    private int loadView = R.layout.load_default;

    public int getLoadView() {
        return loadView;
    }

    public void setLoadView(int loadView) {
        this.loadView = loadView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == 0) {
            return new EasyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false), viewId);
        }else{
            return new LoadHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLoadView(), viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof  EasyHolder) {
            EasyHolder holder = (EasyHolder) viewHolder;
            if (callBack != null) {
                callBack.bindItemView(holder, data.get(position), position);
                setAnimation(holder.itemView, position);
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
        public ImageView[] imageViews;
        public TextView[] textViews;
        public Button[] buttons;
        public View[] views;

        public EasyHolder(View itemView, int[] viewId) {
            super(itemView);
            load(itemView, viewId);
        }

        private void load(View itemView, int[] viewId) {
            List<ImageView> list1 = new ArrayList<>();
            List<Button> list2 = new ArrayList<>();
            List<TextView> list3 = new ArrayList<>();
            List<View> list4 = new ArrayList<>();
            for (int id : viewId) {
                View temp = itemView.findViewById(id);
                if (temp instanceof ImageView) {
                    list1.add((ImageView) temp);
                } else if (temp instanceof TextView) {
                    if (temp instanceof Button) {
                        list2.add((Button) temp);
                    }else{
                        list3.add((TextView) temp);
                    }
                } else {
                    list4.add(temp);
                }
            }
            imageViews = list1.toArray(new ImageView[list1.size()]);
            buttons = list2.toArray(new Button[list2.size()]);
            textViews =  list3.toArray(new TextView[list3.size()]);
            views = list4.toArray(new View[list4.size()]);
        }
    }

    public static class LoadHolder extends RecyclerView.ViewHolder{
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public interface CallBack{
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

     private void setAnimation(View viewToAnimate, int position) {
        if (animId > 0 && position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), animId);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

}
