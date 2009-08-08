package com.kyotogtug.amidakuji;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Key;
import com.kyotogtug.amidakuji.dao.IAmidakujiDao;
import com.kyotogtug.amidakuji.dao.ILineDao;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;
import com.kyotogtug.amidakuji.jdo.entity.Line;

/**
 * ダミー
 */
@SuppressWarnings("serial")
public class TestAmidakujiServlet extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/* Amidakujiを登録 */
		List<String> mailList = new ArrayList<String>();
		mailList.add( "hirohiro.tornade@gmail.com" );
		mailList.add( "hirohiro.tornade2@gmail.com" );
		mailList.add( "hirohiro.tornade3@gmail.com" );
		
		List<String> urlList = new ArrayList<String>();
		urlList.add( "http://tbn2.google.com/images?q=tbn:yWyWojyPlyH1YM:http://katourosagazou1.up.seesaa.net/image/B2C3C6A3A5EDA1BCA5B5A1A1B2E8C1FCA1A1CAC9BBE6A1A1A5ADA5EAA5F3A5D3A5D0A5ECA5C3A5B8.jpg" );
		urlList.add( "http://tbn2.google.com/images?q=tbn:lst6tn8Vfpjs8M:http://www.namisaru.net/photo/20080730.jpg" );
		urlList.add( "http://tbn0.google.com/images?q=tbn:PwTIquqhOIQkiM:http://kengo.preston-net.com/archives/firefox_wallpaper.jpg" );
		
		Amidakuji amidakuji = new Amidakuji();
		amidakuji.setImageUrlList(urlList);
		amidakuji.setMailAddressList(mailList);
		amidakuji.setLength(100);
		amidakuji.setEndTime(toDate("2009/08/08 17:30:00"));
		amidakuji.setTitle( "壁紙選手権！" );
		
		IAmidakujiDao amidakujiDao = new com.kyotogtug.amidakuji.dao.memoryimpl.AmidakujiDaoImpl();
		amidakujiDao.insertAmidakuji(amidakuji);
		
		/* あみだくじの取得 */
		Amidakuji gettedAmidakuji = amidakujiDao.getAmidakujiById(amidakuji.getId().getId());
		
		/* あみだくじの線を引く */
		Line line = new Line();
		line.setXPoint( 3 );
		line.setYPoint( 20 );
		line.setCreateTime(new Date());
//		line.setCreateUser(createUser);
//		line.setAmidakuji(gettedAmidakuji);

		ILineDao lineDao = new com.kyotogtug.amidakuji.dao.memoryimpl.LineDaoImpl();
		lineDao.insertLine( amidakuji.getId().getId() , line );
		
		// 念のため確認
		Amidakuji gettedAmidakuji2 = amidakujiDao.getAmidakujiById(gettedAmidakuji.getId().getId());
		List<Line> lineList = gettedAmidakuji2.getLineList();
		
//		Amidakuji amidakuji3 = amidakujiDao.getAmidakujiById(3);
		
		resp.setContentType("text/html");
		resp.getOutputStream().println("gettedAmidakuji2:" + gettedAmidakuji2.getId().getId());
		resp.getOutputStream().println("gettedAmidakuji2.line:" + lineList.size());
	}

	private Date toDate(String string) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
