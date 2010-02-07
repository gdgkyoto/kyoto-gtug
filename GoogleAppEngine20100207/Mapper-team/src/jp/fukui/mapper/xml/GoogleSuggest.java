package jp.fukui.mapper.xml;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * Google�T�W�F�X�gAPI���Ă�
 * @author tanayama
 *
 */
public class GoogleSuggest {

	/**
	 * �R���X�g���N�^
	 * @param keyword	Google�T�W�F�X�g���s���L�[���[�h
	 */
	public GoogleSuggest(String keyword){
		targetKeyword = keyword;		
	}

	/**
	 * �L�[���[�h
	 */
	private String targetKeyword = null;

	private static final Logger log = Logger.getLogger(GoogleSuggest.class.getName());

	/**
	 * GoogleSuggestAPI���R�[��
	 */
	public ArrayList<String> searchGoogleSuggest(){
		
		String encodedWord;
		ArrayList<String> resultList = null;
		try {
			
			// �L�[���[�h���G���R�[�h
			encodedWord = URLEncoder.encode(targetKeyword, "sjis");
						
			// �ǂݍ���
			InputStream in = new URL("http://google.com/complete/search?output=toolbar&q=" + encodedWord).openStream();
		

			// Google�T�W�F�X�g�L�[���[�h���X�g�擾
			resultList= GetGoogleSuggest(in);
	
			
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
		return resultList;
	}
	
	
	/**
	 * InputStream(XML)����GoogleSuggest�L�[���[�h�̈ꗗ���擾����B
	 * @param inputStream
	 * @return
	 */
	private ArrayList<String> GetGoogleSuggest(InputStream inputStream) {
		
		ArrayList<String> resultList = null;
		
		try {
		      // SAX�p�[�T�[�t�@�N�g���𐶐�
		      SAXParserFactory spfactory = SAXParserFactory.newInstance();
		      // SAX�p�[�T�[�𐶐�
		      SAXParser parser = spfactory.newSAXParser();
		      
		      GoogleSuggestHandler handler = new GoogleSuggestHandler();
		      
		      // XML�t�@�C�����w�肳�ꂽ�f�t�H���g�n���h���[�ŏ������܂�
		      parser.parse(inputStream, handler);
		      
		      resultList = handler.getSuggestWordList();
		      
	    } catch (Exception e) {
			log.warning(e.getMessage());
	    }

	    return resultList;
	}
}
