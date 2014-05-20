package com.itbox.grzl.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itbox.grzl.AppContext;
import com.itbox.grzl.R;
import com.itbox.grzl.activity.ConsultationSearchActivity;
import com.itbox.grzl.bean.Job;

/**
 * 
 * @author malinkang 2014年5月19日
 * 
 */
public class ConsultationFragment extends BaseFragment {
	@InjectView(R.id.tv_ask)
	TextView askTextView;
	@InjectView(R.id.gv_jobtype)
	GridView mGridView;
	@InjectView(R.id.tv_professional_teacher)
	TextView professionalTeacherTextView;
	@InjectView(R.id.tv_human_resource_teacher)
	TextView humanResourceTeacherTextView;
	private ArrayList<Job> jobs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_consultation, null);
		ButterKnife.inject(this, view);
		jobs = AppContext.getJobs();
		JobTypeAdapter adapter = new JobTypeAdapter();
		mGridView.setAdapter(adapter);

		return view;
	}

	private class JobTypeAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return jobs.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.biz_news_column_edit_item_layout, null);
			TextView name = (TextView) view.findViewById(R.id.name);
			name.setText(jobs.get(position).getName());
			name.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),
							ConsultationSearchActivity.class);
					intent.putExtra("jobtype", jobs.get(position).getId() + "");
					startActivity(intent);
				}
			});
			return view;
		}
	}
}
