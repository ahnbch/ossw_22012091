import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public void sort_name(List<Info> infoList, boolean ascending) {
        infoList.sort(Comparator.comparing(Info::getName));
        if (!ascending) {
            Collections.reverse(infoList);
        }
    }

    public void sort_money(List<Info> infoList, boolean ascending) {
        infoList.sort(Comparator.comparingInt(Info::getMoney));
        if (!ascending) {
            Collections.reverse(infoList);
        }
    }
}
