package org.hackathon.map2;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

	private static String MASTERPIECE = "ウホホイウッホ ウホホホホ ウッホホウッホ ウホホホホーイ";
	private static String DEFAULT_CHAR = "ウ";
	private static String SEPARATER = "[,、。！？!?「」（）()\n\t\\s\\.]";
	private String prevString;
	private String[] prevMatrix;
	private String[] nextMatrix;

	/**
	 * @param mReplaceString
	 * @param onClickListener
	 * 
	 */
	public Converter(String str) {
		this.prevString = str;
		makeMalkov();
	}

	public String getConvertedString() {
		if (prevString == null || prevString.equals("")) {
			return "";
		}
		return convert(prevString);
	}

	/**
	 * MASTERPIECE
	 */
	private void makeMalkov() {
		String[] sentence = MASTERPIECE.split(" "); // 半角スペースで区切る
		
		int size = MASTERPIECE.length() - (sentence.length-1) * 2; 
		prevMatrix = new String[size];
		nextMatrix = new String[size];
		int matCnt = 0;
		for (int i = 0; i < sentence.length; i++) {
			String[] chars = sentence[i].split(""); // 
			int charlen = chars.length;
			for (int j = 1; j < charlen; j++) {
				if (((chars[j] != null)) && (!chars[j].equals(""))) {
					if (j != charlen - 1) {
						prevMatrix[matCnt] = chars[j];
						nextMatrix[matCnt] = chars[j + 1];
						matCnt++;
					}
				}
			}
		}
	}

	/**
	 * �S������ɕ������u���������
	 * 
	 * @param str
	 * @return
	 */
	private String convert(String str) {

		Pattern pattern = Pattern.compile(SEPARATER);
		String[] splitStr = pattern.split(str);
		for (int i = 0; i < splitStr.length; i++) {
			Pattern pattern1 = Pattern.compile(splitStr[i]);
			Matcher matcher = pattern1.matcher(str);
			str = matcher.replaceFirst(generate(splitStr[i]));
		}
		return str;
	}

	/**
	 * 
	 * 
	 * @param str
	 * @return
	 */
	private String generate(String str) {

		StringBuffer outStr = new StringBuffer();
		outStr.append(DEFAULT_CHAR);
		int len = str.length();
		String prevChar = DEFAULT_CHAR;

		for (int i = 0; i < len - 1; i++) {
			Random random = new Random();
			int ran = random.nextInt(prevMatrix.length);
			while (!prevChar.equals(prevMatrix[ran])) {
				ran++;
				if (ran > prevMatrix.length - 1) {
					ran = 0;
				}
			}
			prevChar = nextMatrix[ran];
			outStr.append(prevChar);
		}
		return outStr.toString();
	}
}
