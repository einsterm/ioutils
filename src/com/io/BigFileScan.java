package com.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class BigFileScan {

	public static void main(String[] args) {
		List<String> bigFiles = getBigFiles("G:/");
		HashMap<Float, String> map = new HashMap<Float, String>();
		for (String s : bigFiles) {
			String[] ss = s.split("\t");
			map.put(Float.valueOf(ss[1]), ss[0]);
		}
		Set<Entry<Float, String>> entrySet = map.entrySet();
		Set<Float> keySet = map.keySet();
		ArrayList keyList = new ArrayList(keySet);

		Collections.sort(keyList);
		Collections.reverse(keyList);
		for (Object key : keyList) {
			System.out.println(map.get(key) + "\t" + key);
		}

	}

	public static List<String> getBigFiles(String path) {
		List<String> ss = new ArrayList<String>();
		File file = new File(path);
		File[] listFiles = file.listFiles();
		if (listFiles != null && listFiles.length > 0) {

			for (File f : listFiles) {
				float len = (float) (f.length() / 1024 / 1024);
				if (f.isFile() && len > 100) {
					ss.add(f.getAbsolutePath() + "\t" + len / 1024);
				} else {
					List<String> cc = getBigFiles(f.getAbsolutePath());
					ss.addAll(cc);
				}
			}
		}
		return ss;

	}

}
