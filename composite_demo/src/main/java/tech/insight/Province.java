package tech.insight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description 省
 * @date 2025/5/16 17:53:33
 */
public class Province implements PopulationNode{
    private final String name;

    private List<PopulationNode> cityList = new ArrayList<>();

    public Province(String name) {
        this.name = name;
    }

    public void addCity(City city) {
        cityList.add(city);
    }

    @Override
    public int computePopulation() {
        return cityList.stream().mapToInt(PopulationNode::computePopulation).sum();
    }
}
