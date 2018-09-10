/**
 * 
 */
package cn.jing.wc.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * function:筛选某个目录下的源程序文件（目前只支持筛选以.c、.java为后缀的源程序文件）
 * 
 * @author liangjing
 *
 */
public class SourceFilenameFilter implements FilenameFilter {

	// 以下后缀的文件将会被筛选出来
	private static final String cFile = ".c";
	private static final String javaFile = ".java";

	@Override
	public boolean accept(File dir, String name) {
		if (name.endsWith(cFile) || name.endsWith(javaFile)) {
			return true;
		}
		return false;
	}
}
