import java.util.List;

public class Edit {
    public void edit(List<Info> infoList, String name, int newMoney) {
        for (Info info : infoList) {
            if (info.getName().equalsIgnoreCase(name)) {
                info.setMoney(newMoney);
            }
        }
    }
}
