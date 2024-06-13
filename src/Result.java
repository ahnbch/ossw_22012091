import java.util.List;

public class Result {
    public int total(List<Info> infoList) {
        return infoList.stream()
                .mapToInt(Info::getMoney)
                .sum();
    }
}
