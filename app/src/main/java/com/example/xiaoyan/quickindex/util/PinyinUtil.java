package com.example.xiaoyan.quickindex.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	/**
	 * 根据汉字获取对应的拼音
	 * @param str
	 * @return
	 */
	public static String getPinyin(String str) {
		// 设置输出配置
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		// 设置大写
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		// 设置不需要音调
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();

		// 获取字符数组
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			// 如果是空格, 跳过当前的循环
			if (Character.isWhitespace(c)) {
				continue;
			}

			if (c > 128 || c < -127) {
				// 可能是汉字
				try {

					if(c=='曾'){
						//这样做的原因是为了在按姓氏排序时，返回的是zeng 而不是ceng
						c='增';
					}

					// 根据字符获取对应的拼音. 你 -> NI , 单 -> DAN , SHAN 返回的是数组是因为有多音字
					String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(s);

				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				// *&$^*@654654LHKHJ
				// 不需要转换, 直接添加
				sb.append(c);
			}
		}

		return sb.toString();
	}
}
