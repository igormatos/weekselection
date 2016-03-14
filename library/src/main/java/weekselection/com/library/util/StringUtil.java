package weekselection.com.library.util;

/**
 * Created by lhz on 2016/3/11.
 */
public class StringUtil {
	public static int getIndex(String str,String []strs){
		int len=strs.length;
		for(int i=0;i<len;i++){
			if(str.equals(strs[i]))
				return i;
		}
		return -1;
	}
	/**
	 * return if str is empty
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}
}
