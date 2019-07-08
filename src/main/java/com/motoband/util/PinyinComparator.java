package com.motoband.util;

import java.util.Comparator;

import com.motoband.boxmanage.imggroup;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 汉字按照拼音排序的比较器
 */
public  class PinyinComparator implements Comparator<Object> {
	public int compare(Object o1, Object o2) {
		if(o1 instanceof imggroup) {
			imggroup qiji=(imggroup) o1;
			o1=qiji.getGroup_name();
			qiji=(imggroup) o2;
			o2=qiji.getGroup_name();
		}
		char c1 = ((String) o1).charAt(0);
		char c2 = ((String) o2).charAt(0);
		int comparevalue=concatPinyinStringArray(
				PinyinHelper.toHanyuPinyinStringArray(c1)).compareTo(
				concatPinyinStringArray(PinyinHelper
						.toHanyuPinyinStringArray(c2)));
		//解决treeset 为0时视为一样的缺点
		if(comparevalue==0) {
			return o1.hashCode()-o2.hashCode();
		}
		return comparevalue;
	}
	private String concatPinyinStringArray(String[] pinyinArray) {
		StringBuffer pinyinSbf = new StringBuffer();
		if ((pinyinArray != null) && (pinyinArray.length > 0)) {
			for (int i = 0; i < pinyinArray.length; i++) {
				pinyinSbf.append(pinyinArray[i]);
			}
		}
		return pinyinSbf.toString();
	}
}