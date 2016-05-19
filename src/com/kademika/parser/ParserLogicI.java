package com.kademika.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.kademika.domain.Tag;

public interface ParserLogicI {

	ArrayList<Tag> getTags(String tag) throws UnsupportedEncodingException, IOException;
}
