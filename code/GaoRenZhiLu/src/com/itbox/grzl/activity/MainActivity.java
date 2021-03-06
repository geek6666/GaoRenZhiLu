package com.itbox.grzl.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itbox.fx.util.ToastUtils;
import com.itbox.grzl.AppContext;
import com.itbox.grzl.Config;
import com.itbox.grzl.fragment.CommentFragment;
import com.itbox.grzl.fragment.ConsultationFragment;
import com.itbox.grzl.fragment.EventFragment;
import com.itbox.grzl.fragment.ExamFragment;
import com.itbox.grzl.fragment.MoreFragment;
import com.zhaoliewang.grzl.R;

public class MainActivity extends BaseActivity {

	public static final int REQUESTCODE_PRIVATE = 1;
	public static final int REQUESTCODE_NOTICE = 2;
	public static final int REQUEST_CODE_CITY = 3;
	private long exitTime;
	@InjectView(android.R.id.tabhost)
	protected FragmentTabHost mTabHost;
	// 定义一个布局
	private LayoutInflater layoutInflater;

	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = { ConsultationFragment.class,
			EventFragment.class, CommentFragment.class, ExamFragment.class,
			MoreFragment.class };

	// 定义数组来存放按钮图片
	private int mImageViewArray[] = { R.drawable.selector_tab_item_consulation,
			R.drawable.selector_tab_item_event,
			R.drawable.selector_tab_item_study,
			R.drawable.selector_tab_item_exam,
			R.drawable.selector_tab_item_more };

	// Tab选项卡的文字
	private String mTextviewArray[] = { "咨询", "活动", "论坛", "测评", "更多" };
	
	public static MainActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
		initView();
		
		activity = this;

		// new ConsultationApi().searchFreeConsultation("1", "1", "1", "1");
		// new ConsultationApi().getPhoneConsultation("14");

		// new ConsultationApi().getPhoneConsultation("14");

		// 检查是否是新用户
		if (AppContext.getUserPreferences().getBoolean(Config.NEW_REGISTER,
				false)) {
			if (AppContext.getInstance().getAccount().isTeacher()) {
				// 提示用户审核身份
				new AlertDialog.Builder(this).setTitle("提示")
						.setMessage("导师需要进行身份审核，是否进入？")
						.setNegativeButton("取消", null)
						.setPositiveButton("进入", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// 进入身份审核
								startActivity(UserIDCardActivity.class);
							}
						}).show();
			}
			AppContext.getUserPreferences().edit()
					.putBoolean(Config.NEW_REGISTER, false).commit();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		activity = null;
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
		}
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			if ((System.currentTimeMillis() - exitTime) > 2000) {
				ToastUtils.showToast(mActThis, "再按一次退出程序");
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
