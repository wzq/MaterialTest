package com.github.wzq;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzq on 15/4/13. for list view
 */
public class EasyListAdapter extends BaseAdapter {
	private Context context;
	private List<Object> data;
	private int layoutId;
	private int[] viewId;
	private CallBack callBack;
	
	public EasyListAdapter(Context context, List<Object> data, int layoutId, int[] viewId, CallBack callBack) {
		this.context = context;
		this.data = data;
		this.layoutId = layoutId;
		this.viewId = viewId;
		this.callBack = callBack;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View root, ViewGroup arg2) {
		ViewHolder holder = null;
		if (root == null) {
			root = LayoutInflater.from(context).inflate(layoutId, null);
			holder = new ViewHolder(root);
			root.setTag(holder);
		} else {
			holder = (ViewHolder) root.getTag();
		}
		if(callBack!=null){
			callBack.updateItem(holder, getItem(position), position);
		}
		return root;
	}

	public class ViewHolder {
		public List<ImageView> imageViews;
		public List<TextView> textViews;
		public List<Button> buttons;
		public List<View> views;
		public View itemView;

		public ViewHolder(View root) {
			itemView = root;
			imageViews = new ArrayList<>();
			textViews = new ArrayList<>();
			buttons = new ArrayList<>();
			views = new ArrayList<>();
			for (int i = 0; i < viewId.length; i++) {
				View temp = root.findViewById(viewId[i]);
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
	
	public interface CallBack{
		void updateItem(ViewHolder holder, Object obj, int position);
	}
}
