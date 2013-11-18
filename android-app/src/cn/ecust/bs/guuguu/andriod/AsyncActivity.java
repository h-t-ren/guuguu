package cn.ecust.bs.guuguu.andriod;


public interface AsyncActivity {

	public void showLoadingProgressDialog();

	public void showProgressDialog(CharSequence message);

	public void dismissProgressDialog();

}
