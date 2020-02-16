package study_0217;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_B_7785_회사에있는사람 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine().trim());
		
		HashMap<String, String> gigle = new HashMap<String,String>();
	
		
		for(int i=0; i<n;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			String word = st.nextToken();
			String out = st.nextToken();
			if(out.equals("enter")) {
				gigle.put(word,word);
			}else if(out.equals("leave")){				
				gigle.remove(word);
			}
		}
		ArrayList<String> gigle2 = new ArrayList<String>(gigle.keySet());
		gigle2.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		for(int i=0; i<gigle2.size();i++) {
			System.out.println(gigle2.get(i));
		}
		
		
	}
}