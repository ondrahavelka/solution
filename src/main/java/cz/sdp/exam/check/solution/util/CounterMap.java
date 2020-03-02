package cz.sdp.exam.check.solution.util;

import cz.sdp.exam.check.solution.model.FileContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class CounterMap<E extends FileContent, V> extends HashMap<String, List<FileContent>> {

    public void put(E key) {
        if (this.containsKey(key.getCompany())){
            this.get(key.getCompany()).add(key);
        } else {
            this.put(key.getCompany(), new ArrayList<>() {{ add(key); }});
        }
    }

    public String getFormattedIndex(E key){
        StringBuilder sb = new StringBuilder(3);
        int size = this.get(key.getCompany()).size();
        if (size < 10){
            sb.append(this.get(key.getCompany()).indexOf(key) + 1);
        } else if (size < 100){
            int indexOf = this.get(key.getCompany()).indexOf(key) + 1;
            if (indexOf < 10){
                sb.append("0");
            }
            sb.append(indexOf);
        } else {
            int indexOf = this.get(key.getCompany()).indexOf(key) + 1;
            if (indexOf < 10){
                sb.append("00");
            } else if (indexOf < 100){
                sb.append("0");
            }
            sb.append(indexOf);
        }
        return sb.toString();
    }
}
