import java.util.List;
import java.util.stream.Collectors;

public class Search {
    public List<Info> search(List<Info> infoList, String name) {
        return infoList.stream()
                .filter(info -> info.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
