package tech.insight;

import java.util.ArrayList;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/12 21:44:20
 */
public class District implements PopulationNode {

    private final String name;
    private int population;

    public District(String name, int population) {
        this.name = name;
        this.population = population;
    }

    @Override
    public int computePopulation() {
        return population;
    }
}
