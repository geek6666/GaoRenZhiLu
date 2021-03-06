package com.itbox.grzl.activity;

import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import cn.jpush.android.api.JPushInterface;

import com.itbox.fx.net.GsonResponseHandler;
import com.itbox.fx.net.Net;
import com.itbox.fx.util.StringUtil;
import com.itbox.fx.util.ToastUtils;
import com.itbox.grzl.Api;
import com.itbox.grzl.AppContext;
import com.itbox.grzl.Config;
import com.zhaoliewang.grzl.R;
import com.itbox.grzl.api.LoginAndRegisterApi;
import com.itbox.grzl.bean.Account;
import com.itbox.grzl.bean.Register;
import com.itbox.grzl.common.util.DialogMessage;
import com.loopj.android.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegistPhoneResultActivity extends BaseActivity {
	@InjectView(R.id.text_left)
	TextView mTVTopCancel;
	@InjectView(R.id.text_medium)
	TextView mTVTopMedium;
	@InjectView(R.id.regist_phone_pass_et)
	EditText mETRegistPhonePass;
	@InjectView(R.id.regist_phone_username_et)
	EditText mETRegistPhoneName;
	@InjectView(R.id.regist_phone_sex_et)
	EditText mETRegistPhoneSex;
	@InjectView(R.id.regist_phone_birthday_et)
	EditText mETRegistPhoneBirthday;
	@InjectView(R.id.regist_phone_area_et)
	EditText mETRegistPhoneArea;
	@InjectView(R.id.regist_phone_type_et)
	EditText mETRegistPhoneType;
	private String registPhone = null;
	private static final int REQUEST_SELECT_SEX = 0;
	private static final int REQUEST_SELECT_BIRTHDAY = 1;
	private static final int REQUEST_SELECT_AREA = 2;
	private static final int REQUEST_SELECT_TYPE = 3;
	private long birthdayMils;
	private String birthday;
	private int sex;
	private int provinceCode = 100000;
	private int cityCode = 110000;
	private int districtCode = 110101;
	private int type;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_regist_phone_finally);
		ButterKnife.inject(mActThis);
		registPhone = getIntent().getStringExtra("registPhone");
		initViews();
	}

	private void initViews() {
		mTVTopCancel.setVisibility(View.VISIBLE);
		mTVTopMedium.setText("手机注册");
	}

	@OnClick(R.id.text_left)
	public void cancelAct() {
		showExitDialog();
	}

	@Override
	protected boolean onBack() {
		showExitDialog();
		return true;
	}

	private void showExitDialog() {
		DialogMessage dialog = DialogMessage.newIntance();
		dialog.show(mActThis.getSupportFragmentManager(), "exitRegist");
	}

	@OnClick({ R.id.regist_phone, R.id.regist_phone_sex_et,
			R.id.regist_phone_birthday_et, R.id.regist_phone_area_et,
			R.id.regist_phone_type_et })
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.regist_phone:
			String string = mETRegistPhonePass.getText().toString();
			if (StringUtil.isBlank(string)) {
				ToastUtils.showToast(mActThis, "密码不为空");
			} else {
				if (string.length() > 6) {
					showToast("密码为六位数");
				} else {
					registPhoneAll();
				}
			}
			break;
		case R.id.regist_phone_sex_et:
			Intent sexIntent = new Intent(this, SelectButton3Activity.class);
			sexIntent.putExtra(SelectButton3Activity.Extra.Button0_Text, "男");
			sexIntent.putExtra(SelectButton3Activity.Extra.Button1_Text, "女");
			startActivityForResult(sexIntent, REQUEST_SELECT_SEX);
			break;
		case R.id.regist_phone_birthday_et:
			Intent birIntent = new Intent(this, SelectDateActivity.class);
			birIntent.putExtra(SelectDateActivity.Extra.DefaultTimeMillis,
					birthdayMils);
			startActivityForResult(birIntent, REQUEST_SELECT_BIRTHDAY);
			break;
		case R.id.regist_phone_area_et:
			Intent intent = new Intent(this, SelectAddrActivity.class);
			startActivityForResult(intent, REQUEST_SELECT_AREA);
			break;
		case R.id.regist_phone_type_et:
			Intent typeIntent = new Intent(this, SelectButton3Activity.class);
			typeIntent.putExtra(SelectButton3Activity.Extra.Button0_Text, "导师");
			typeIntent.putExtra(SelectButton3Activity.Extra.Button1_Text, "学生");
			startActivityForResult(typeIntent, REQUEST_SELECT_TYPE);
			break;
		default:
			break;
		}
	}

	private void registPhoneAll() {
		showProgressDialog("注册中...");
		RequestParams params = new RequestParams();
		String phone = mETRegistPhoneName.getText().toString();
		String pass = mETRegistPhonePass.getText().toString();
		if (StringUtil.isBlank(pass)) {
			showToast("密码不为空");
			return;
		}
		if (pass.length() > 6) {
			showToast("密码为六位数");
			return;
		}
		params.put("username", phone);
		params.put("userpassword", pass);
		params.put("userprovince", provinceCode + "");
		params.put("usercity", cityCode + "");
		params.put("userdistrict", districtCode + "");
		params.put("userphone", registPhone);
		params.put("usersex", sex + "");
		params.put("usertype", type + "");
		Net.request(params, Api.getUrl(Api.User.Register),
				new GsonResponseHandler<Register>(Register.class) {
					@Override
					public void onSuccess(Register object) {
						super.onSuccess(object);
						if (object.getResult() > 0) {
							// 注册成功
							AppContext.getUserPreferences().edit()
									.putBoolean(Config.NEW_REGISTER, true)
									.commit();
							ToastUtils.showToast(mActThis, "注册成功");
							showProgressDialog("登陆中...");
							new LoginAndRegisterApi().login(mETRegistPhoneName
									.getText().toString(), mETRegistPhonePass
									.getText().toString(),
									new GsonResponseHandler<Account>(
											Account.class) {
										@Override
										public void onSuccess(Account account) {
											super.onSuccess(account);
											initJPush(account);
											account.save();
											AppContext.getInstance()
													.setAccount(account);
											Intent intent = new Intent(
													mActThis,
													MainActivity.class);
											startActivity(intent);
										}

										@Override
										public void onFailure(Throwable e,
												int statusCode, String content) {
											super.onFailure(e, statusCode,
													content);
											dismissProgressDialog();
											showToast("用户名或密码错误");
										}

										@Override
										public void onFinish() {
											dismissProgressDialog();
											RegistPhoneResultActivity.this
													.finish();
										}
									});
						} else {
							switch (object.getResult()) {
							case -1:
								ToastUtils.showToast(mActThis, "邮箱重复");
								break;
							case -2:
								ToastUtils.showToast(mActThis, "手机重复");
								break;
							case -3:
								ToastUtils.showToast(mActThis, "昵称重复");
								break;
							}
						}
					}

					@Override
					public void onFinish() {
						super.onFinish();
						dismissProgressDialog();
					}
				});
	}

	private void initJPush(Account account) {
		Set<String> set = new HashSet<String>();
		set.add(account.getUsertype() + "");
		JPushInterface.setAliasAndTags(getApplicationContext(),
				account.getConnectkey(), set, null);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_SELECT_SEX:
			if (RESULT_OK == resultCode && null != data) {
				int intExtra = data.getIntExtra(
						SelectButton3Activity.Extra.SelectedItem,
						SelectButton3Activity.Extra.Selected_cancle);
				if (intExtra != SelectButton3Activity.Extra.Selected_cancle) {
					sex = intExtra + 1;
					mETRegistPhoneSex
							.setText(data
									.getStringExtra(SelectButton3Activity.Extra.SelectedItemStr));
				}
			}
			break;
		case REQUEST_SELECT_BIRTHDAY:
			if (RESULT_OK == resultCode && null != data) {
				birthdayMils = data.getLongExtra(
						SelectDateActivity.Extra.SelectedTime, 0);
				String birStr = data
						.getStringExtra(SelectDateActivity.Extra.SelectedTimeStr);
				birthday = birStr;
				mETRegistPhoneBirthday.setText(birStr.substring(0, 10));
			}
			break;
		case REQUEST_SELECT_AREA:
			if (RESULT_OK == resultCode && null != data) {

				provinceCode = data.getIntExtra(
						SelectAddrActivity.Extra.ProvinceCode, 0);
				cityCode = data.getIntExtra(SelectAddrActivity.Extra.CityCode,
						0);
				districtCode = data.getIntExtra(
						SelectAddrActivity.Extra.DistrictCode, 0);
				String addrName = data
						.getStringExtra(SelectAddrActivity.Extra.ProvinceName)
						+ " "
						+ data.getStringExtra(SelectAddrActivity.Extra.CityName)
						+ " "
						+ data.getStringExtra(SelectAddrActivity.Extra.DistrictName);
				mETRegistPhoneArea.setText(addrName);
			}
			break;
		case REQUEST_SELECT_TYPE:
			if (RESULT_OK == resultCode && null != data) {
				int intExtra = data.getIntExtra(
						SelectButton3Activity.Extra.SelectedItem,
						SelectButton3Activity.Extra.Selected_cancle);
				if (intExtra != SelectButton3Activity.Extra.Selected_cancle) {
					type = intExtra + 1;
					mETRegistPhoneType
							.setText(data
									.getStringExtra(SelectButton3Activity.Extra.SelectedItemStr));
				}
			}
			break;
		default:
			break;
		}
	}
}
