package com.kademika.parser;

import com.kademika.domain.Tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public interface TagParser {

	List<Tag> tagList = new ArrayList<>();

	List<Tag> parse(String startTag) throws UnsupportedEncodingException, IOException;

	List<Tag> parse(String startTag, int depth) throws UnsupportedEncodingException, IOException;

}
