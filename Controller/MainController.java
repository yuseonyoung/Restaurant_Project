package Controller;

import JDBCUtil.TotalView;

public class MainController {

	public static void main(String[] args) {

		TotalView lv = TotalView.getInstance();
			lv.init();
	}
}
