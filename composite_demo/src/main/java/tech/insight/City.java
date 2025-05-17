package tech.insight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/12 21:42:27
 */
public class City implements PopulationNode {

    private final String name;

    List<PopulationNode> districtList = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public void addDistrict(District district) {
        districtList.add(district);
    }

    @Override
    public int computePopulation() {
        return districtList.stream().mapToInt(PopulationNode::computePopulation).sum();
    }
}
