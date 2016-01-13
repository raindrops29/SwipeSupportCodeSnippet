package kr.co.tacademy.extinction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by samsung on 2015-12-08.
 */
public class RandomArrayList {

    private static ArrayList<Integer> imageResources;

    static {
        imageResources = new ArrayList<Integer>();
        imageResources.add(R.drawable.girls_eneration_all);
        imageResources.add(R.drawable.girls_eneration_hyoyeon);
        imageResources.add(R.drawable.girls_eneration_jesica);
        imageResources.add(R.drawable.girls_eneration_seohyun);
        imageResources.add(R.drawable.girls_eneration_sunny);
        imageResources.add(R.drawable.girls_eneration_suyoung);
        imageResources.add(R.drawable.girls_eneration_taeyeon);
        imageResources.add(R.drawable.girls_eneration_tifany);
        imageResources.add(R.drawable.girls_eneration_yuri);

        imageResources.add(R.drawable.girls_generation_hyoyeon);
        imageResources.add(R.drawable.girls_generation_hyoyeon);
        imageResources.add(R.drawable.girls_generation_jesica);
        imageResources.add(R.drawable.girls_generation_seohyun);
        imageResources.add(R.drawable.girls_generation_sunny);
        imageResources.add(R.drawable.girls_generation_suyoung);
        imageResources.add(R.drawable.girls_generation_taeyeon);
        imageResources.add(R.drawable.girls_generation_tifany);
        imageResources.add(R.drawable.girls_generation_yuri);
        imageResources.add(R.drawable.t_ara_icon_eunjung);
        imageResources.add(R.drawable.t_ara_icon_jiyeon);
    }

    private static HashMap<Integer, String> nameMaps;

    static {
        nameMaps = new HashMap<Integer, String>();
        nameMaps.put(R.drawable.girls_eneration_all, "소녀시대멤버1");
        nameMaps.put(R.drawable.girls_eneration_hyoyeon, "효연1");
        nameMaps.put(R.drawable.girls_generation_seohyun, "서현2");
        nameMaps.put(R.drawable.girls_eneration_seohyun, "서현1");
        nameMaps.put(R.drawable.girls_eneration_sunny, "써니1");
        nameMaps.put(R.drawable.girls_eneration_suyoung, "수영1");
        nameMaps.put(R.drawable.girls_eneration_taeyeon, "태연1");
        nameMaps.put(R.drawable.girls_eneration_tifany, "티파니1");
        nameMaps.put(R.drawable.girls_eneration_yuri, "유리1");
        nameMaps.put(R.drawable.girls_generation_sunny, "써니2");
        nameMaps.put(R.drawable.girls_generation_jesica, "제시카2");
        nameMaps.put(R.drawable.girls_generation_hyoyeon, "효연2");
        nameMaps.put(R.drawable.girls_generation_seohyun, "서현2");
        nameMaps.put(R.drawable.girls_generation_suyoung, "수영2");
        nameMaps.put(R.drawable.girls_generation_taeyeon, "태연2");
        nameMaps.put(R.drawable.girls_generation_tifany, "티파니2");
        nameMaps.put(R.drawable.girls_generation_yuri, "유리2");

        nameMaps.put(R.drawable.t_ara_icon_eunjung, "은정");
        nameMaps.put(R.drawable.t_ara_icon_jiyeon, "지연");
    }

    private static Random random = new Random(System.currentTimeMillis());

    public static ArrayList<Integer> getSuffleArrayList() {
        Collections.shuffle(imageResources, random);
        return imageResources;
    }
    public static String  getGirlGroupName(Integer key) {
        return nameMaps.get(key);
    }
}
