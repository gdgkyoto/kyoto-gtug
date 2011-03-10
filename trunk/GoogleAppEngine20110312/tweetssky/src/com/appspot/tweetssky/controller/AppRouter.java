package com.appspot.tweetssky.controller;

import org.slim3.controller.router.RouterImpl;
import org.slim3.util.RequestUtil;

/**
 * URLマッピングを実行するクラス。
 * addRouting("変換前", "返還後"); というように指定する。
 * ここでの定義はControllerのパッケージ名/クラス名によるマッピングより前に適用される。
 */
public class AppRouter extends RouterImpl {
    public AppRouter() {
//        addRouting(
//            "/{app}/json/{url}",
//            "/{app}/json?url={url}");
//        addRouting("/_ah/mail/{address}", "/mail/receive?address={address}");
//        addRouting(
//            "/{app}/edit/{key}/{version}",
//            "/{app}/edit?key={key}&version={version}");
//        addRouting(
//            "/{app}/delete/{key}/{version}",
//            "/{app}/delete?key={key}&version={version}");
//                addRouting(
//            "/{app}/find/*path",
//            "/{app}/find?path={path}");
    }
    
	/* 
	 * URLに拡張子json/xmlを指定された場合にもURLマッピングを行う
	 * 
	 * @see org.slim3.controller.router.RouterImpl#isStatic(java.lang.String)
	 */
	@Override
	public boolean isStatic(String path) throws NullPointerException {
		boolean isStatic = super.isStatic(path);

		if ("json".equals(RequestUtil.getExtension(path))
				|| "xml".equals(RequestUtil.getExtension(path))) {
			return false;
		} else {
			return isStatic;
		}
	}
}