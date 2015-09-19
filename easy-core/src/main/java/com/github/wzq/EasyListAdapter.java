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
		public View itemView;
		public ImageView[] imageViews;
		public TextView[] textViews;
		public Button[] buttons;
		public View[] views;

		public ViewHolder(View root) {
			itemView = root;
			load(root);
		}
		private void load(View itemView) {
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
	
	public interface CallBack{
		void updateItem(ViewHolder holder, Object obj, int position);
	}
}
