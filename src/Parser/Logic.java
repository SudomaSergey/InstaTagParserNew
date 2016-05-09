package Parser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

class Logic{
	

	public String getTags(String tag){
		String initialPage = null;
		try{
			initialPage = getResultPageByTag(tag);
		}
		catch(Exception e){
			
		}			
		String top9ImagesPage = getTop9ImagesPage(initialPage);
		String[] splitedImages = getSplitBySeparateImagePage(top9ImagesPage); 
		String result = parseTags(splitedImages);
		return result;
	}
	    	 
	private String parseTags(String[] splitedImages) {
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile("#[^ [а-я][А-Я]]+\\s"); 
        for(int i = 1; i < splitedImages.length; i++){     
        	Matcher m = p.matcher(splitedImages[i]);
        	result.append("Picture " + i + " tags: ");
        	while(m.find()) {
        		result.append(splitedImages[i].substring(m.start(), m.end()) + ", ");
        	}
        	result.append("\n");
        	result.append("***");
        	result.append("\n");
        }
		return result.toString();
	}

	private String[] getSplitBySeparateImagePage(String top9ImagesPage) {
		String temp = unescapeJava(top9ImagesPage);
		System.out.println(temp);
		return temp.split("caption");
	}

	private String getTop9ImagesPage(String initialPage) {
        int topTagsLineStart = initialPage.indexOf("top_posts");        
        String tempPage1 = initialPage.substring(topTagsLineStart, initialPage.length());        
        int topTagsLineEnd = tempPage1.indexOf("</script>");        
        return tempPage1.substring(0, topTagsLineEnd);
	}

	private String getResultPageByTag(String tag) throws UnsupportedEncodingException, IOException {
        URL url = new URL("https://www.instagram.com/explore/tags/" + tag);   	 
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        
        String str = null;
        StringBuilder page = new StringBuilder();     
        while ((str = in.readLine()) != null) {
            page.append(str);
        }        
		return page.toString();
	}
	
	private String unescapeJava(String escaped) {
	    if(escaped.indexOf("\\u")==-1)
	        return escaped;

	    String processed="";

	    int position=escaped.indexOf("\\u");
	    while(position!=-1) {
	        if(position!=0)
	            processed+=escaped.substring(0,position);
	        String token=escaped.substring(position+2,position+6);
	        escaped=escaped.substring(position+6);
	        processed+=(char)Integer.parseInt(token,16);
	        position=escaped.indexOf("\\u");
	    }
	    processed+=escaped;

	    return processed;
	}
}
  

