package cn.edu.nju.test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Test {
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		/***************************************** 更改开始线 *************************************************************/
		final String userName = "111250250";
		final String password = "113016";
		/***************************************** 更改结束线 *************************************************************/

		final String xianLin = "%E4%BB%99%E6%9E%97%E6%A0%A1%E5%8C%BA";// 仙林
		final String guLou = "%E9%BC%93%E6%A5%BC%E6%A0%A1%E5%8C%BA";// 鼓楼
		final String publicCourse = "publicRenewCourseList";// 公选
		final String discussCourse = "discussRenewCourseList";// 通识

		String cookie = getCookie(userName, password);

		while (true) {
			/***************************************** 更改开始线 *************************************************************/
			chooseCourses(discussCourse, guLou, cookie);
			//chooseCourses(discussCourse, xianLin, cookie);
			/***************************************** 更改结束线 *************************************************************/
			Thread.sleep(100);
		}
	}

	private static String getCookie(String userName, String password){
		try {
			URL url = new URL("http://jwas3.nju.edu.cn:8080/jiaowu/login.do");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "8859_1");
			out.write("userName=" + userName + "&password=" + password);
			out.flush();
			out.close();
			return connection.getHeaderField("Set-Cookie").split(";")[0];
		} catch (Exception e) {
			return null;
		}
	}

	private static void chooseCourses(String type, String campus, String cookie) {
		try {
			InputStream urlStream = connect(
					"http://jwas3.nju.edu.cn:8080/jiaowu/student/elective/courseList.do?method="
							+ type + "&campus=" + campus, cookie);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlStream));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith("<tr class")) {
					line = line.trim();
					break;
				}
			}

			String[] courses = line.split("value");

			for (int i = 1; i < courses.length; i++) {
				System.out.println("Choose:ID_" + courses[i].substring(2, 7));
				connect("http://jwas3.nju.edu.cn:8080/jiaowu/student/elective/courseList.do?method=submitPublicRenew&classId="
						+ courses[i].substring(2, 7) + "&campus=" + campus,
						cookie);
			}
		} catch (Exception e) {}
	}

	private static InputStream connect(String url_str, String cookie) {
		try {
			URL url = new URL(url_str);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Cookie", cookie);
			return connection.getInputStream();
		} catch (Exception e) {
			return null;
		}
	}
}
