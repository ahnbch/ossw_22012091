import java.util.List;
import java.util.Iterator;

public class Delete {
    public void delete(List<Info> infoList, String name) {
        Iterator<Info> iterator = infoList.iterator();
        while (iterator.hasNext()) {
            Info info = iterator.next();
            if (info.getName().equalsIgnoreCase(name)) {
                iterator.remove();
            }
        }
    }
}
