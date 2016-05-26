package com.kademika.parser;

import com.kademika.domain.Tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Top9Parser implements TagParser {

	/**
	 *
	 * @param startTag
	 *
	 * @return list of tags from top 9 picts, sorted by {@link Tag#getCount()}
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public ArrayList<Tag> parse(String startTag) throws UnsupportedEncodingException, IOException {
		ParserLogicI parserLogic = new ParserLogic();
		ArrayList<Tag> tagsList = parserLogic.getTags(startTag);
		return tagsList;
	}

	@Override
	public List<Tag> parse(String startTag, int depth) throws UnsupportedEncodingException, IOException {
		ArrayList<Tag> allTagsList = parse(startTag);

		ArrayList<Tag> tempAllTagsList = new ArrayList<>();

		for (int i = 0; i < depth; i++) {
			for (Tag tag : allTagsList) {
				String tagName = tag.getName();
				ArrayList<Tag> tempTagsList;
				try {
					tempTagsList = parse(tagName);
				} catch (Exception e) {
					System.out.println(tagName);
					tempTagsList = new ArrayList<>();
				}
				tempAllTagsList.addAll(tempTagsList);
			}
		}
		
		//allTagsList = joinTagLists(allTagsList, allTagsList);
		allTagsList = joinTagLists(allTagsList, tempAllTagsList);

		Collections.sort(allTagsList);

		return allTagsList;
	}

	private ArrayList<Tag> joinTagLists(ArrayList<Tag> allTagsList, ArrayList<Tag> tempAllTagsList) {

		for (Tag tag : tempAllTagsList) {
			if (allTagsList.contains(tag)) {
				int tagIdx = allTagsList.indexOf(tag);
				allTagsList.get(tagIdx).updateCount();
			} else {
				allTagsList.add(tag);
			}
		}
		return allTagsList;
	}
}
