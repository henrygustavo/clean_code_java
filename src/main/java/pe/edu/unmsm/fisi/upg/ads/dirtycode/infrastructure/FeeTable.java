/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.fisi.upg.ads.dirtycode.infrastructure;

/**
 *
 * @author GUSTAVO-ZENBOOK
 */
public class FeeTable {

    private int fee;
    private int minYearExperience;
    private int maxYearExperience;

    public FeeTable(int fee, int minYearExperience, int maxYearExperience) {
        this.fee = fee;
        this.minYearExperience = minYearExperience;
        this.maxYearExperience = maxYearExperience;
    }

    /**
     * @return the MinYearExperience
     */
    public int getMinYearExperience() {
        return minYearExperience;
    }

    /**
     * @param MinYearExperience the MinYearExperience to set
     */
    public void setMinYearExperience(int MinYearExperience) {
        this.minYearExperience = MinYearExperience;
    }

    /**
     * @return the MaxYearExperience
     */
    public int getMaxYearExperience() {
        return maxYearExperience;
    }

    /**
     * @return the Fee
     */
    public int getFee() {
        return fee;
    }

    /**
     * @param Fee the Fee to set
     */
    public void setFee(int Fee) {
        this.fee = Fee;
    }
}
