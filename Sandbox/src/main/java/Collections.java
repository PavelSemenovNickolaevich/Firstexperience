import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = new String[4];
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Python";
        langs[3] = "PHP";

        for (int i = 0; i < langs.length; i++) {
            System.out.println("I will be a super QA: " + langs[i]);
        }

        for (String l : langs) {
            System.out.println("I will be a super QA: " + l);
        }

        List<String> languages = new ArrayList<>();
        List<String> languages2 = Arrays.asList(langs);
        languages.add("Test");
        for (String str : languages2) {
            System.out.println(str);
        }

        for (int i = 0; i < languages2.size(); i++) {
            System.out.println(languages2.get(i));
        }
    }
}
